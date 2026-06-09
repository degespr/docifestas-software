package com.software.docifestas.service;

import com.software.docifestas.dto.usuario.UsuarioRequestDTO;
import com.software.docifestas.dto.usuario.UsuarioResponseDTO;
import com.software.docifestas.exception.BusinessException;
import com.software.docifestas.exception.ResourceNotFoundException;
import com.software.docifestas.repository.UsuarioRepository;
import com.software.docifestas.model.Usuario;
import com.software.docifestas.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

   // Atributos By Dege
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VendaRepository vendaRepository;

    public UsuarioResponseDTO salvar(UsuarioRequestDTO request) {
    // Code Run
        // Validation - Email
        validarEmail(request.getEmail());

        // Validation - Senha
        validarSenha(request.getSenha());

        // Validation - Nome
        validarNome(request.getNome());

        // Code Rule
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(request.getEmail());
            if (usuarioExistente.isPresent()) {
                throw new BusinessException("Usuário já cadastrado! Faça login ou crie outra conta.");
            }

            Usuario usuario = new Usuario();
            usuario.setNome(request.getNome());
            usuario.setEmail(request.getEmail());
            usuario.setSenha(request.getSenha());
            usuario.setAdmin(false);

            Usuario usuarioSalvo = usuarioRepository.save(usuario);
            return toDTO(usuarioSalvo);
    }

    // Code Rule - Extras
    public List<UsuarioResponseDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioResponseDTO> listaDto = new ArrayList<>();
        for (Usuario u : usuarios) {
            listaDto.add(toDTO(u));
        }
        return listaDto;
    }

    // Method's
    public UsuarioResponseDTO buscarPorId(Long id) {
    Usuario usuario = buscarEntidadePorId(id);
        return toDTO(usuario);
    }

    public UsuarioResponseDTO atualizarUsuario(Long id, UsuarioRequestDTO request, Usuario usuario) {

        // Validation
        Optional<Usuario> usuarioComMesmoEmail = usuarioRepository.findByEmail(request.getEmail());

        // Code Run
        if (id.equals(usuario.getId())) {
            Usuario usuarioExistente = buscarEntidadePorId(id);

            // Validation's
            validarEmail(request.getEmail());
            if (usuarioComMesmoEmail.isPresent() && !usuarioComMesmoEmail.get().getId().equals(id)) {
                throw new BusinessException("Email já cadastrado por outro usuário.");
            }

            validarSenha(request.getSenha());
            validarNome(request.getNome());

            // Applications
            usuarioExistente.setEmail(request.getEmail());
            usuarioExistente.setSenha(request.getSenha());
            usuarioExistente.setNome(request.getNome());

            // Update
            Usuario usuarioAtualizado = usuarioRepository.save(usuarioExistente);
            return toDTO(usuarioAtualizado);
        }

        throw new BusinessException("Usuário não possui o mesmo ID digitado.");
    }

    public void deletarUsuario(Long id, Usuario usuario) {

        if (!id.equals(usuario.getId())) {
            throw new BusinessException("Usuário não possui o mesmo ID digitado.");
        }

        Usuario usuarioExistente = buscarEntidadePorId(id);

        boolean usuarioComVendas = vendaRepository.existsByUsuarioId(id);
        if (usuarioComVendas) {
            throw new BusinessException("Não é possível excluir um usuário que possui vendas registradas.");
        }

        usuarioRepository.delete(usuarioExistente);
    }

    // Method for Conversion - Não Modificar
    private Usuario buscarEntidadePorId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            throw new ResourceNotFoundException("Usuário não encontrado");

        } return usuario.get();
    }

    // Method To DTO - Não Modificar
    private UsuarioResponseDTO toDTO(Usuario usuario) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();

        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setAdmin(usuario.isAdmin());

        return dto;
    }

    // Method's for Refactoring - Nome
    private void validarNome(String nome) {

        // Code Run
        if (nome == null) {
            throw new BusinessException("Nome inválido! Insira um nome válido.");
        }

        if (nome.trim().isEmpty()) {
            throw new BusinessException("Nome inválido! Insira um nome válido.");

        }
    }

    // Method's for Refactoring - Senha
    private void validarSenha(String senha) {

        // Code Run
        if (senha == null) {
            throw new BusinessException("Tipo de senha inválida!");
        }

        if (senha.trim().isEmpty()) {
            throw new BusinessException("Tipo de senha inválida!");
        }

        if (senha.length() < 7) {
            throw new BusinessException("Tipo de senha inválida!");
        }
    }

    // Method's for Refactoring - Email
    private void validarEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        // Code Run
        if (email == null) {
            throw new BusinessException("Email inválido!");
        }

        if (!email.matches(regex)) {
            throw new BusinessException("Email inválido!");
        }
    }
}