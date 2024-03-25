package com.kiwifin.api.controllers;

import com.kiwifin.api.service.PerfilService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/perfil")
@CrossOrigin(origins = "*")
public class PerfilController extends ApiController {

    PerfilService perfilService;

    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @GetMapping("/buscar/todos")
    public ResponseEntity<Object> buscarPerfis() {
        try {
            return ResponseEntity.ok(perfilService.buscarPerfis());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
