package com.kiwifin.api.controllers;

import com.kiwifin.api.entities.Colaborador;
import com.kiwifin.api.security.config.DTO.LoginResponseDTO;
import com.kiwifin.api.service.ClienteService;
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
    private ClienteService clienteService;

    public AutenticacaoController(AuthenticationManager authenticationManager, TokenService tokenService, ClienteService clienteService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.clienteService = clienteService;
    }

    @PostMapping("/colaborador/login")
    public ResponseEntity loginColaborador(@RequestBody @Valid AutenticacaoDTO data) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.cpf(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);

            var token = tokenService.gerarToken((Colaborador) auth.getPrincipal());


            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/cliente/login")
    public ResponseEntity loginCliente(@RequestBody @Valid AutenticacaoDTO data) {
        try {
            return ResponseEntity.ok(clienteService.autenticaCliente(data.cpf(), data.password()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
