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
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioRequestDTO salvar(UsuarioRequestDTO request) {
    // Code Run
        // Validation Nome
        if (request.getNome() == null) {
            throw new BusinessException("Nome inválido! Insira um nome válido.");
        }

        // Validation Email
        if (request.getEmail() == null || !request.getEmail().contains("@")) {
            throw new BusinessException("Email inválido!");
        }

        // Validation Senha
        if (request.getSenha() == null || request.getSenha().length() < 7) {
            throw new BusinessException("Tipo de senha inválida!");
        }

        // Code Rule - Account
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(request.getEmail());
            if (usuarioExistente.isPresent()) {
                throw new BusinessException("Usuário já cadastrado! Faça login ou crie outra conta.");
            }

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

    // MTS
    public UsuarioResponseDTO buscarPorId(Long id) {
    Usuario usuario = buscarEntidadePorId(id);
        return toDTO(usuario);
    }

    public UsuarioResponseDTO atualizarUsuario(Long id, Usuario usuario) {
        Usuario usuarioExistente = buscarEntidadePorId(id);

        usuarioExistente.setNome(usuario.getNome());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setSenha(usuario.getSenha());

        Usuario usuarioAtualizado = usuarioRepository.save(usuarioExistente);
        return toDTO(usuarioAtualizado);
    }

    public void deletarUsuario(Long id) {
        buscarEntidadePorId(id);
        usuarioRepository.deleteById(id);
    }

    // Metodo Oculto de Conversão - NÃO MODIFICAR
    private Usuario buscarEntidadePorId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            throw new ResourceNotFoundException("Usuário não encontrado");

        } return usuario.get();
    }

    // Método toDTO - Não mexer
    private UsuarioResponseDTO toDTO(Usuario usuario) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();

        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setAdmin(usuario.isAdmin());

        return dto;
    }
}