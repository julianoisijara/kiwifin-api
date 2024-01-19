package com.kiwifin.api.controllers;

import com.kiwifin.api.DTO.create.AtendenteCreateDTO;
import com.kiwifin.api.DTO.update.AtendenteUpdateDTO;
import com.kiwifin.api.DTO.view.AtendenteViewDTO;
import com.kiwifin.api.service.AtendenteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/atendente")
@CrossOrigin(origins = "*")
public class AtendenteController extends ApiController {

    AtendenteService service;

    public AtendenteController(AtendenteService service) {
        this.service = service;
    }


    @ApiOperation(value = "Adiciona atendente", notes = "Adiciona atendente",
            response = AtendenteViewDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atendente adicionado"),
            @ApiResponse(code = 204, message = "Nenhum atendente adicionado"),
            @ApiResponse(code = 400, message = "Chamada incorreta"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @PostMapping("/adicionar")
    public ResponseEntity<Object> adicionar(@RequestBody AtendenteCreateDTO dto) {
        try {
            return ResponseEntity.ok(service.incluirAtendente(dto));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Busca todos os atendentes", notes = "Busca todos os atendentes",
            response = AtendenteViewDTO.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atendentes encontrados"),
            @ApiResponse(code = 204, message = "Nenhum atendente encontrado"),
            @ApiResponse(code = 400, message = "Chamada incorreta"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @GetMapping("/buscar/todos")
    public ResponseEntity<Object> buscarTodosAtendentes() {
        try {
            return respondOk(service.buscarTodosAtendentes());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Busca atendente", notes = "Busca atendente",
            response = AtendenteViewDTO.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atendente encontrado"),
            @ApiResponse(code = 204, message = "Nenhum atendente encontrado"),
            @ApiResponse(code = 400, message = "Chamada incorreta"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @GetMapping("/buscar/{idAtendente}")
    public ResponseEntity<Object> buscarAtendente(@RequestParam(value = "idAtendente") Long idAtendente) {
        try {
            return respondOk(service.buscarAtendente(idAtendente));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Edita atendente", notes = "Edita atendente",
            response = AtendenteViewDTO.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atendente editado"),
            @ApiResponse(code = 204, message = "Nenhum atendente editado"),
            @ApiResponse(code = 400, message = "Chamada incorreta"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @PostMapping("/editar")
    public ResponseEntity<Object> editar(@RequestBody AtendenteUpdateDTO dto) {
        try {
            return ResponseEntity.ok(service.atualizarAtendente(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Deletar atendente", notes = "Deletar atendente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atendente deletado"),
            @ApiResponse(code = 204, message = "Nenhum atendente deletado"),
            @ApiResponse(code = 400, message = "Chamada incorreta"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @DeleteMapping("/deletar/{idAtendente}")
    public ResponseEntity deletar(@PathVariable("idAtendente") Long idAtendente) {
        return service.findById(idAtendente).map(entity -> {
            service.excluirAtendente(entity);
            return ResponseEntity.ok("Atendente " + idAtendente + " excluído com sucesso!");
        }).orElseGet(() -> ResponseEntity.badRequest().body("Erro ao excluir atendente: " + idAtendente));
    }


}
