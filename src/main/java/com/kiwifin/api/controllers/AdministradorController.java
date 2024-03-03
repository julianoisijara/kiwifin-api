package com.kiwifin.api.controllers;

import com.kiwifin.api.DTO.create.AdministradorCreateDTO;
import com.kiwifin.api.DTO.update.AdministradorUpdateDTO;
import com.kiwifin.api.DTO.view.AdministradorViewDTO;
import com.kiwifin.api.service.AdministradorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/administrador")
@CrossOrigin(origins = "*")
public class AdministradorController extends ApiController {

    AdministradorService service;

    public AdministradorController(AdministradorService service) {
        this.service = service;
    }



    @ApiOperation(value = "Adiciona administrador", notes = "Adiciona administrador",
            response = AdministradorViewDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Administrador adicionado"),
            @ApiResponse(code = 204, message = "Nenhum Administrador adicionado"),
            @ApiResponse(code = 400, message = "Chamada incorreta"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @PostMapping("/adicionar")
    public ResponseEntity<Object> adicionar(@RequestBody AdministradorCreateDTO dto) {
        try {
            return ResponseEntity.ok(service.incluirAdministrador(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @ApiOperation(value = "Busca todos os administradores", notes = "Busca todos os administradores",
            response = AdministradorViewDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Administradores encontrados"),
            @ApiResponse(code = 204, message = "Nenhum Administrador encontrado"),
            @ApiResponse(code = 400, message = "Chamada incorreta"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @GetMapping("/buscar/todos")
    public ResponseEntity<Object> buscarTodosAdministradores() {
        try {
            return ResponseEntity.ok(service.buscarTodosAdministradores());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @ApiOperation(value = "Busca administrador", notes = "Busca administrador",
            response = AdministradorViewDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Administrador encontrado"),
            @ApiResponse(code = 204, message = "Nenhum Administrador encontrado"),
            @ApiResponse(code = 400, message = "Chamada incorreta"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @GetMapping("/buscar/{idAdministrador}")
    public ResponseEntity<Object> buscarAdministrador(@RequestParam(value = "idAdministrador") Long idAdministrador) {
        try {
            return ResponseEntity.ok(service.buscarAdministrador(idAdministrador));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Edita administrador", notes = "Edita administrador",
            response = AdministradorViewDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Administrador editado"),
            @ApiResponse(code = 204, message = "Nenhum Administrador editado"),
            @ApiResponse(code = 400, message = "Chamada incorreta"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @PutMapping("/editar")
    public ResponseEntity<Object> editar(@RequestBody AdministradorUpdateDTO dto) {
        try {
            return ResponseEntity.ok(service.atualizarAdministrador(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{idAdministrador}")
    public ResponseEntity deletar(@PathVariable("idAdministrador") Long idAdministrador) {
        return service.findById(idAdministrador).map(entity -> {
            service.excluirAdministrador(entity);
            return ResponseEntity.ok("Administrador " + idAdministrador + " excluído com sucesso!");
        }).orElseGet(() -> ResponseEntity.badRequest().body("Erro ao excluir administrador: " + idAdministrador));
    }


}
