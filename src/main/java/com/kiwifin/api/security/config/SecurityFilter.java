package com.kiwifin.api.security.config;


import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kiwifin.api.repositories.ColaboradorRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class SecurityFilter extends OncePerRequestFilter {


    TokenService tokenService;
    ColaboradorRepository colaboradorRepository;
    @Value("${security.jwt.token.secret-key:secret}")
    private String secret;

    public SecurityFilter(TokenService tokenService, ColaboradorRepository colaboradorRepository) {
        this.tokenService = tokenService;
        this.colaboradorRepository = colaboradorRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        var token = this.recoverToken(request);
        if (token != null) {
            var cpf = tokenService.validarToken(token);

            if (cpf.equals("")) {
                cpf = validaTokenExpirado(token);
            }

            UserDetails user = colaboradorRepository.findByCpfEquals(cpf);

            var authenticacao = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticacao);
        }
        filterChain.doFilter(request, response);
    }

    private String validaTokenExpirado(String tokenExpirado) {
        String cpf = "";
        DecodedJWT jwt = JWT.decode(tokenExpirado);
        Date expiresAt = jwt.getExpiresAt();
        long diferencaEmMilis = new Date().getTime() - expiresAt.getTime();
        long diferencaEmHoras = TimeUnit.MILLISECONDS.toHours(diferencaEmMilis);
        if (diferencaEmHoras <= 4) {
            cpf = jwt.getSubject();
        }
        return cpf;
    }


    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
