package com.software.docifestas.cfg;

import com.software.docifestas.model.Usuario;
import com.software.docifestas.repository.UsuarioRepository;
import com.software.docifestas.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    // Atributos By Dege
    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    // Construtor
    public SecurityFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = recuperarToken(request);

        // Code Run
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        String email = tokenService.validarToken(token);
        if (email == null) {
            filterChain.doFilter(request, response);
            return;
        }

        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
        if (usuarioOptional.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        Usuario usuario = usuarioOptional.get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                usuario,
                null,
                Collections.emptyList());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        // Code Run
        if (authorizationHeader == null) {
            return null;
        }

        if (!authorizationHeader.startsWith("Bearer ")) {
            return null;
        }

        String token = authorizationHeader.replace("Bearer ", "");
        return token;
    }
}