package com.software.docifestas.service;

import com.software.docifestas.model.Usuario;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    // Constant
    private static final String SECRET = "doci-festas-secret";

    // Functions of System
    public String gerarToken(Usuario usuario) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        // Code Run
        return JWT.create()
                .withSubject(usuario.getEmail())
                .withClaim("id", usuario.getId())
                .withIssuer("doci-festas-api")
                .withExpiresAt(gerarExpiracao())
                .sign(algorithm);
    }

    private Instant gerarExpiracao() {

        // Code Run
        return LocalDateTime.now()
                .plusHours(1)
                .toInstant(ZoneOffset.of("-03:00"));
    }

    public String validarToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        // Code Run
        try {
            return JWT.require(algorithm)
                    .withIssuer("doci-festas-api")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (Exception e){
            return null;
        }
    }
}
