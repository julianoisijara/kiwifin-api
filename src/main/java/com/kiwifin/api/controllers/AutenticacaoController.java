package com.kiwifin.api.controllers;

import com.kiwifin.api.service.AutenticacaoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/autenticacao")
@CrossOrigin(origins = "*")
public class AutenticacaoController extends ApiController {

    AutenticacaoService service;

    public AutenticacaoController(AutenticacaoService service) {
        this.service = service;
    }

    @ApiOperation(value = "Autentica cliente", notes = "Autentica cliente",
            response = Boolean.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente autenticado"),
            @ApiResponse(code = 204, message = "Nenhum cliente autenticado"),
            @ApiResponse(code = 400, message = "Chamada incorreta"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @GetMapping("/cliente/{cpf}/{senha}")
    public ResponseEntity<Object> autenticarCliente(@PathVariable(value = "cpf") String cpf, @PathVariable(value = "senha") String senha) {
        try {
            return ResponseEntity.ok(service.autenticaCliente(cpf,senha));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Autentica Administrador", notes = "Autentica Administrador",
            response = Boolean.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Administrador autenticado"),
            @ApiResponse(code = 204, message = "Nenhum Administrador autenticado"),
            @ApiResponse(code = 400, message = "Chamada incorreta"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @GetMapping("/administrador/{cpf}/{senha}")
    public ResponseEntity<Object> autenticarAdministrador(@PathVariable(value = "cpf") String cpf, @PathVariable(value = "senha") String senha) {
        try {
            return ResponseEntity.ok(service.autenticaAdministrador(cpf,senha));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Autentica Atendente", notes = "Autentica Atendente",
            response = Boolean.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atendente autenticado"),
            @ApiResponse(code = 204, message = "Nenhum Atendente autenticado"),
            @ApiResponse(code = 400, message = "Chamada incorreta"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @GetMapping("/atendente/{cpf}/{senha}")
    public ResponseEntity<Object> autenticarAtendente(@PathVariable(value = "cpf") String cpf, @PathVariable(value = "senha") String senha) {
        try {
            return ResponseEntity.ok(service.autenticaAtendente(cpf,senha));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Autentica Supervisor Qualidade", notes = "Autentica Supervisor Qualidade",
            response = Boolean.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Supervisor Qualidade autenticado"),
            @ApiResponse(code = 204, message = "Nenhum Supervisor Qualidade autenticado"),
            @ApiResponse(code = 400, message = "Chamada incorreta"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @GetMapping("/supervisor/qualidade/{cpf}/{senha}")
    public ResponseEntity<Object> autenticarSupervisorQualidade(@PathVariable(value = "cpf") String cpf, @PathVariable(value = "senha") String senha) {
        try {
            return ResponseEntity.ok(service.autenticaSupervisorQualidade(cpf,senha));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
