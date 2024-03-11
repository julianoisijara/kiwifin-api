package com.kiwifin.api.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.kiwifin.api.entities.Colaborador;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
//    @Value("${security.jwt.token.secret-key:secret}")
    private String secret = "secret";
//    @Value("${security.jwt.token.expire-lenght:3600000}")
//    private long validtyInMilliseconds = 3600000;

    public String gerarToken(Colaborador colaborador) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("autenticacao-api")
                    .withSubject(colaborador.getUsername())
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);

            return token;

        } catch (JWTCreationException exception) {
            throw  new RuntimeException("Erro ao gerar o Token.");
        }
    }

    public String validarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("autenticacao-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return "";
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
