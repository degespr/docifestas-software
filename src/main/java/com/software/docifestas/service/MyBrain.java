package com.software.docifestas.service;

import com.software.docifestas.repository.UsuarioRepository;
import com.software.docifestas.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyBrain {
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

        //Validation Senha
        if (usuario.getSenha() == null || usuario.getSenha().length() < 7) {
            throw new IllegalArgumentException("Tipo de senha inválida!");

        }

        // Validação Type Login
        if  (usuario.getTypeLogin() == null ||
                !usuario.getTypeLogin().equalsIgnoreCase("ADMIN") && !usuario.getTypeLogin().equalsIgnoreCase("USER")) {
            throw new IllegalArgumentException("Tipo de login inválido!");

        }
        return usuarioRepository.save(usuario);
    }
}