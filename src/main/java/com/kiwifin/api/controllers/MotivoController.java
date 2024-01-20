package com.kiwifin.api.controllers;


import com.kiwifin.api.DTO.create.MotivoCreateDTO;
import com.kiwifin.api.DTO.update.MotivoUpdateDTO;
import com.kiwifin.api.DTO.view.MotivoViewDTO;
import com.kiwifin.api.service.MotivoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/motivo")
@CrossOrigin(origins = "*")
public class MotivoController extends ApiController{

    MotivoService service;

    public MotivoController(MotivoService service) {
        this.service = service;
    }


    @ApiOperation(value = "Adiciona motivo", notes = "Adiciona motivo",
            response = MotivoViewDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Motivo adicionado"),
            @ApiResponse(code = 204, message = "Nenhum motivo adicionado"),
            @ApiResponse(code = 400, message = "Chamada incorreta"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @PostMapping("/adicionar")
    public ResponseEntity<Object> adicionar(@RequestBody MotivoCreateDTO dto) {

        try {
            return ResponseEntity.ok(service.incluirMotivo(dto));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Edita motivo", notes = "Edita motivo",
            response = MotivoViewDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Motivo editado"),
            @ApiResponse(code = 204, message = "Nenhum motivo editado"),
            @ApiResponse(code = 400, message = "Chamada incorreta"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @PostMapping("/editar")
    public ResponseEntity<Object> editar(@RequestBody MotivoUpdateDTO dto) {

        try {
            return ResponseEntity.ok(service.atualizarMotivo(dto));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Pesquisar Motivos", notes = "Pesquisar Motivos",
            response = MotivoViewDTO.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Motivo encontrado"),
            @ApiResponse(code = 204, message = "Nenhum Motivo encontrado"),
            @ApiResponse(code = 400, message = "Chamada incorreta"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @GetMapping(value = "/pesquisar/{idMotivo}/{nome}/{status}")
    public ResponseEntity<Object> pesquisar(@RequestParam(value = "idMotivo", required = false) Long idMotivo, @RequestParam(value = "nome", required = false) String nome, @RequestParam(value = "status", required = false) Boolean status) {

        if (idMotivo == null && nome == null && status == null) {
            return respondBadRequest("Informe ao menos um parâmetro de busca!");
        }

        try {
            return respondOk(service.buscarMotivos(idMotivo, nome, status));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Busca todos os motivos", notes = "Busca todos os motivos",
            response = MotivoViewDTO.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Motivos encontrados"),
            @ApiResponse(code = 204, message = "Nenhum motivo encontrado"),
            @ApiResponse(code = 400, message = "Chamada incorreta"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @GetMapping("/buscar/todos")
    public ResponseEntity<Object> buscarTodosMotivos() {
        try {
            return respondOk(service.buscarTodosMotivos());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Deletar motivo", notes = "Deletar motivo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Motivo deletado"),
            @ApiResponse(code = 204, message = "Nenhum Motivo deletado"),
            @ApiResponse(code = 400, message = "Chamada incorreta"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @DeleteMapping("deletar/{idMotivo}")
    public ResponseEntity deletar(@PathVariable("idMotivo") Long idMotivo) {
        return service.findById(idMotivo).map(entity -> {
            service.excluirMotivo(entity);
            return ResponseEntity.ok("Departamento " + idMotivo + " deletado com sucesso");
        }).orElseGet(() -> ResponseEntity.badRequest().body("Erro ao deletar departamento: " + idMotivo));
    }


}
