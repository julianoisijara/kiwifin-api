package com.kiwifin.api.controllers;

import com.kiwifin.api.DTO.create.ColaboradorCreateDTO;
import com.kiwifin.api.DTO.update.ColaboradorUpdateDTO;
import com.kiwifin.api.DTO.view.ColaboradorViewDTO;
import com.kiwifin.api.service.ColaboradorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/colaborador")
@CrossOrigin(origins = "*")
public class ColaboradorController extends ApiController {

    ColaboradorService service;

    public ColaboradorController(ColaboradorService service) {
        this.service = service;
    }



    @ApiOperation(value = "Adiciona Colaborador", notes = "Adiciona Colaborador",
            response = ColaboradorViewDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Colaborador adicionado"),
            @ApiResponse(code = 204, message = "Nenhum Colaborador adicionado"),
            @ApiResponse(code = 400, message = "Chamada incorreta"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @PostMapping("/adicionar")
    public ResponseEntity<Object> adicionar(@RequestBody ColaboradorCreateDTO dto) {
        try {
            return ResponseEntity.ok(service.incluirColaborador(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @ApiOperation(value = "Busca todos os Colaboradores", notes = "Busca todos os Colaboradores",
            response = ColaboradorViewDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Colaboradores encontrados"),
            @ApiResponse(code = 204, message = "Nenhum Colaborador encontrado"),
            @ApiResponse(code = 400, message = "Chamada incorreta"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @GetMapping("/buscar/todos")
    public ResponseEntity<Object> buscarTodosColaboradores() {
        try {
            return ResponseEntity.ok(service.buscarTodosColaboradores());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @ApiOperation(value = "Busca Colaborador", notes = "Busca Colaborador",
            response = ColaboradorViewDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Colaborador encontrado"),
            @ApiResponse(code = 204, message = "Nenhum Colaborador encontrado"),
            @ApiResponse(code = 400, message = "Chamada incorreta"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @GetMapping("/buscar/{idColaborador}")
    public ResponseEntity<Object> buscarColaborador(@RequestParam(value = "idColaborador") Long idColaborador) {
        try {
            return ResponseEntity.ok(service.buscarColaborador(idColaborador));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Edita Colaborador", notes = "Edita Colaborador",
            response = ColaboradorViewDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Colaborador editado"),
            @ApiResponse(code = 204, message = "Nenhum Colaborador editado"),
            @ApiResponse(code = 400, message = "Chamada incorreta"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @PutMapping("/editar")
    public ResponseEntity<Object> editar(@RequestBody ColaboradorUpdateDTO dto) {
        try {
            return ResponseEntity.ok(service.atualizarColaborador(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{idColaborador}")
    public ResponseEntity deletar(@PathVariable("idColaborador") Long idColaborador) {
        return service.findById(idColaborador).map(entity -> {
            service.excluirColaborador(entity);
            return ResponseEntity.ok("Colaborador " + idColaborador + " excluído com sucesso!");
        }).orElseGet(() -> ResponseEntity.badRequest().body("Erro ao excluir Colaborador: " + idColaborador));
    }


}
