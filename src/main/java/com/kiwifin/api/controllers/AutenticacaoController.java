package com.kiwifin.api.controllers;

import com.kiwifin.api.entities.Colaborador;
import com.kiwifin.api.security.config.DTO.LoginResponseDTO;
import com.kiwifin.api.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import com.kiwifin.api.security.config.DTO.AutenticacaoDTO;

@RestController
@RequestMapping("api/autenticacao")
public class AutenticacaoController {


    private AuthenticationManager authenticationManager;
    private TokenService tokenService;

    public AutenticacaoController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AutenticacaoDTO data) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);

            var token = tokenService.gerarToken((Colaborador) auth.getPrincipal());


            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
