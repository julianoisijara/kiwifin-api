package com.kiwifin.api.controllers;


import com.kiwifin.api.DTO.create.SupervisorQualidadeCreateDTO;
import com.kiwifin.api.DTO.update.SupervisorQualidadeUpdateDTO;
import com.kiwifin.api.DTO.view.SupervisorQualidadeViewDTO;
import com.kiwifin.api.service.SupervisorQualidadeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/supervisor/qualidade")
@CrossOrigin(origins = "*")
public class SupervisorQualidadeController extends ApiController{

    SupervisorQualidadeService service;

    public SupervisorQualidadeController(SupervisorQualidadeService service) {
        this.service = service;
    }


    @ApiOperation(value = "Adiciona Supervisor", notes = "Adiciona Supervisor",
            response = SupervisorQualidadeViewDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Supervisor adicionado"),
            @ApiResponse(code = 204, message = "Nenhum Supervisor adicionado"),
            @ApiResponse(code = 400, message = "Chamada incorreta"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @PostMapping("/adicionar")
    public ResponseEntity<Object> adicionar(@RequestBody SupervisorQualidadeCreateDTO dto) {
        try {
            return ResponseEntity.ok(service.incluirSupervisorQualidade(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Busca todos os Supervisores", notes = "Busca todos os Supervisores",
            response = SupervisorQualidadeViewDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Supervisores encontrados"),
            @ApiResponse(code = 204, message = "Nenhum Supervisor encontrado"),
            @ApiResponse(code = 400, message = "Chamada incorreta"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @GetMapping("/buscar/todos")
    public ResponseEntity<Object> buscarTodosSupervisores() {
        try {
            return ResponseEntity.ok(service.buscarTodosSupervisores());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Busca Supervisor", notes = "Busca Supervisor",
            response = SupervisorQualidadeViewDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Supervisor encontrado"),
            @ApiResponse(code = 204, message = "Nenhum Supervisor encontrado"),
            @ApiResponse(code = 400, message = "Chamada incorreta"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @GetMapping("/buscar/{idSupervisor}")
    public ResponseEntity<Object> buscarSupervisor(@RequestParam(value = "idSupervisor") Long idSupervisor) {
        try {
            return ResponseEntity.ok(service.buscarSupervisorQualidade(idSupervisor));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Edita Supervisor", notes = "Edita Supervisor",
            response = SupervisorQualidadeViewDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Supervisor editado"),
            @ApiResponse(code = 204, message = "Nenhum Supervisor editado"),
            @ApiResponse(code = 400, message = "Chamada incorreta"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @PostMapping("/editar")
    public ResponseEntity<Object> editar(@RequestBody SupervisorQualidadeUpdateDTO dto) {
        try {
            return ResponseEntity.ok(service.atualizarSupervisorQualidade(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Exclui Supervisor", notes = "Exclui Supervisor")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Supervisor excluído"),
            @ApiResponse(code = 204, message = "Nenhum Supervisor excluído"),
            @ApiResponse(code = 400, message = "Chamada incorreta"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @DeleteMapping("/deletar/{idSupervisor}")
    public ResponseEntity deletar(@PathVariable("idSupervisor") Long idSupervisor) {
        return service.findById(idSupervisor).map(entity -> {
            service.excluirSupervisorQualidade(entity);
            return ResponseEntity.ok("Administrador " + idSupervisor + " excluído com sucesso!");
        }).orElseGet(() -> ResponseEntity.badRequest().body("Erro ao excluir administrador: " + idSupervisor));
    }


}
