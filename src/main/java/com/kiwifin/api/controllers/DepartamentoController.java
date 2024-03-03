package com.kiwifin.api.controllers;

import com.kiwifin.api.DTO.create.DepartamentoCreateDTO;
import com.kiwifin.api.DTO.update.DepartamentoUpdateDTO;
import com.kiwifin.api.DTO.view.DepartamentoViewDTO;
import com.kiwifin.api.service.DepartamentoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/departamento")
@CrossOrigin(origins = "*")
public class DepartamentoController extends ApiController {

    DepartamentoService service;

    public DepartamentoController(DepartamentoService service) {
        this.service = service;
    }

    @ApiOperation(value = "Adiciona departamento", notes = "Adiciona departamento",
            response = DepartamentoViewDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Departamento adicionado"),
            @ApiResponse(code = 204, message = "Nenhum Departamento adicionado"),
            @ApiResponse(code = 400, message = "Chamada incorreta"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @PostMapping("/adicionar")
    public ResponseEntity<Object> adicionar(@RequestBody DepartamentoCreateDTO dto) {

        try {
            return ResponseEntity.ok(service.incluirDepartamento(dto));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Edita departamento", notes = "Edita departamento",
            response = DepartamentoViewDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Departamento editado"),
            @ApiResponse(code = 204, message = "Nenhum Departamento editado"),
            @ApiResponse(code = 400, message = "Chamada incorreta"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @PutMapping("/editar")
    public ResponseEntity<Object> editar(@RequestBody DepartamentoUpdateDTO dto) {

        try {
            return ResponseEntity.ok(service.atualizarDepartamento(dto));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Pesquisar departamentos", notes = "Pesquisar departamentos",
            response = DepartamentoViewDTO.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Departamento encontrado"),
            @ApiResponse(code = 204, message = "Nenhum Departamento encontrado"),
            @ApiResponse(code = 400, message = "Chamada incorreta"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @GetMapping(value = "/pesquisar/{idDepartamento}/{nome}/{status}")
    public ResponseEntity<Object> pesquisar(@RequestParam(value = "idDepartamento", required = false) Long idDepartamento, @RequestParam(value = "nome", required = false) String nome, @RequestParam(value = "status", required = false) Boolean status) {

        if (idDepartamento == null && nome == null && status == null) {
            return respondBadRequest("Informe ao menos um parâmetro de busca!");
        }

        try {
            return respondOk(service.buscarDepartamentos(idDepartamento, nome, status));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @ApiOperation(value = "Busca todos os departamentos", notes = "Busca todos os departamentos",
            response = DepartamentoViewDTO.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Departamentos encontrados"),
            @ApiResponse(code = 204, message = "Nenhum Departamento encontrado"),
            @ApiResponse(code = 400, message = "Chamada incorreta"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @GetMapping("/buscar/todos")
    public ResponseEntity<Object> buscarTodosDepartamentos() {
        try {
            return respondOk(service.buscarTodosDepartamentos());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Deletar departamento", notes = "Deletar departamento")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Departamento deletado"),
            @ApiResponse(code = 204, message = "Nenhum Departamento deletado"),
            @ApiResponse(code = 400, message = "Chamada incorreta"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @DeleteMapping("deletar/{idDepartamento}")
    public ResponseEntity deletar(@PathVariable("idDepartamento") Long idDepartamento) {
        return service.findById(idDepartamento).map(entity -> {
            service.excluirDepartamento(entity);
            return ResponseEntity.ok("Departamento " + idDepartamento + " deletado com sucesso");
        }).orElseGet(() -> ResponseEntity.badRequest().body("Erro ao deletar departamento: " + idDepartamento));
    }


}
