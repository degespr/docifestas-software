package com.software.docifestas.controller;

import com.software.docifestas.dto.auth.LoginRequestDTO;
import com.software.docifestas.exception.BusinessException;
import com.software.docifestas.model.Usuario;
import com.software.docifestas.repository.UsuarioRepository;
import com.software.docifestas.service.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    // Atributos By Dege
    private UsuarioRepository usuarioRepository;
    private TokenService tokenService;

    // Construtor
    public AuthController(UsuarioRepository usuarioRepository, TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO request) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(request.getEmail());

        // Code Run
        if (usuarioOptional.isEmpty()) {
            throw new BusinessException("Email ou senha inválidos.");
        }

        Usuario usuario = usuarioOptional.get();

        if (!usuario.getSenha().equals(request.getSenha())) {
            throw new BusinessException("Email ou senha inválidos");
        }

        String token = tokenService.gerarToken(usuario);
        return token;
    }
}

//
