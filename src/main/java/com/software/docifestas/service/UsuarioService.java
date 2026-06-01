package com.software.docifestas.service;

import com.software.docifestas.dto.usuario.UsuarioRequestDTO;
import com.software.docifestas.dto.usuario.UsuarioResponseDTO;
import com.software.docifestas.exception.BusinessException;
import com.software.docifestas.exception.ResourceNotFoundException;
import com.software.docifestas.repository.UsuarioRepository;
import com.software.docifestas.model.Usuario;
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

    public UsuarioResponseDTO salvar(UsuarioRequestDTO request) {
    // Code Run
        // Validations
        if (request.getNome() == null) {
            throw new BusinessException("Nome inválido! Insira um nome válido.");
        }

        if (request.getEmail() == null || !request.getEmail().contains("@")) {
            throw new BusinessException("Email inválido!");
        }

        if (request.getSenha() == null || request.getSenha().length() < 7) {
            throw new BusinessException("Tipo de senha inválida!");
        }

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

    public UsuarioResponseDTO atualizarUsuario(Long id, UsuarioRequestDTO request) {
        Usuario usuarioExistente = buscarEntidadePorId(id);

        // Code Run
        usuarioExistente.setNome(request.getNome());
        usuarioExistente.setEmail(request.getEmail());
        usuarioExistente.setSenha(request.getSenha());

        Usuario usuarioAtualizado = usuarioRepository.save(usuarioExistente);
        return toDTO(usuarioAtualizado);
    }

    public void deletarUsuario(Long id) {

        // Code Run
        buscarEntidadePorId(id);
        usuarioRepository.deleteById(id);
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
}