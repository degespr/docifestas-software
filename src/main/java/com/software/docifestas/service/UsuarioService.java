package com.software.docifestas.service;

import com.software.docifestas.repository.UsuarioRepository;
import com.software.docifestas.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario) {
    // Code Run
        // Validation Nome
        if (usuario.getNome() == null) {
            throw new IllegalArgumentException("Nome inválido! Insira um nome válido.");

        }

        // Validation Email
        if (usuario.getEmail() == null || !usuario.getEmail().contains("@")) {
            throw new IllegalArgumentException("Email inválido!");

        }

        // Validation Senha
        if (usuario.getSenha() == null || usuario.getSenha().length() < 7) {
            throw new IllegalArgumentException("Tipo de senha inválida!");

        }

        // Code Rule - Account
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
            if (usuarioExistente.isPresent()) {
                throw new RuntimeException("Usuário já cadastrado! Faça login ou crie outra conta.");

            }

        // Code Rule - Type Login
        usuario.setIsAdmin(false);
        return usuarioRepository.save(usuario);
    }

    // Code Rule - Extras
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // MTS
    public Usuario buscarPorId(Long id) {

    Optional<Usuario> buscaCompleta = usuarioRepository.findById(id);
        if (buscaCompleta.isPresent()) {
            return buscaCompleta.get();

        }   throw new IllegalArgumentException("Usuário não encontrado!");
    }

    public Usuario atualizarUsuario(Long id, Usuario usuario) {
        Usuario usuarioExistente = buscarPorId(id);

        usuarioExistente.setNome(usuario.getNome());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setSenha(usuario.getSenha());

        return usuarioRepository.save(usuarioExistente);
    }

    public void deletarUsuario(Long id) {
        buscarPorId(id);
        usuarioRepository.deleteById(id);
    }
}