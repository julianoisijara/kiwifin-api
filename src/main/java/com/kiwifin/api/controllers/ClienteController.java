package com.kiwifin.api.controllers;

import com.kiwifin.api.DTO.create.ClienteCreateDTO;
import com.kiwifin.api.DTO.update.ClienteUpdateDTO;
import com.kiwifin.api.DTO.view.ClienteViewDTO;
import com.kiwifin.api.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = "*")
public class ClienteController extends ApiController {

    ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @ApiOperation(value = "Adiciona cliente", notes = "Adiciona cliente",
            response = ClienteViewDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente adicionado"),
            @ApiResponse(code = 204, message = "Nenhum Cliente adicionado"),
            @ApiResponse(code = 400, message = "Chamada incorreta"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @PostMapping("/adicionar")
    public ResponseEntity<Object> adicionar(@RequestBody ClienteCreateDTO dto) {

        try {
            ClienteViewDTO clienteViewDTO;

            clienteViewDTO = service.incluirCliente(dto);
            return ResponseEntity.ok(clienteViewDTO);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @ApiOperation(value = "Edita clientes passando informações de busca", notes = "Edita clientes passando informações de busca",
            response = ClienteViewDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente editado"),
            @ApiResponse(code = 204, message = "Nenhum Clientes encontrado"),
            @ApiResponse(code = 400, message = "Chamada incorreta"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @PutMapping("/editar")
    public ResponseEntity<Object> editar(@RequestBody ClienteUpdateDTO dto) {

        try {
            ClienteViewDTO clienteViewDTO;

            clienteViewDTO = service.atualizarCliente(dto);
            return ResponseEntity.ok(clienteViewDTO);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Pesquisa clientes passando informações de busca", notes = "Pesquisa clientes passando informações de busca",
            response = ClienteViewDTO.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Clientes encontrados"),
            @ApiResponse(code = 204, message = "Nenhum Cliente encontrado"),
            @ApiResponse(code = 400, message = "Chamada incorreta"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @GetMapping(value = "/pesquisar/{idCliente}/{cpf}/{nome}")
    public ResponseEntity<Object> pesquisar(@RequestParam(value = "idCliente", required = false) Long idCliente, @RequestParam(value = "cpf", required = false) String cpf, @RequestParam(value = "nome", required = false) String nome) {

        if (idCliente == null && cpf == null && nome == null) {
           return respondBadRequest("Informe ao menos um parâmetro de busca!");
        }


        try {
            List<ClienteViewDTO> clienteViewDTO;
            clienteViewDTO = service.buscarClientes(idCliente, cpf, nome);

            if (clienteViewDTO.size() != 0) {
                return respondOk(clienteViewDTO);
            } else {
                return respondNoContent("Cliente não encontrado.");
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Busca todos os clientes cadastrados", notes = "Busca todos os clientes cadastrados",
            response = ClienteViewDTO.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Clientes encontrados"),
            @ApiResponse(code = 204, message = "Nenhum Cliente encontrado"),
            @ApiResponse(code = 400, message = "Chamada incorreta"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @PostMapping("/pesquisar/todos")
    public ResponseEntity<Object> pesquisarTodos() {

        try {
            List<ClienteViewDTO> clienteViewDTO;

            clienteViewDTO = service.buscarTodosClientes();
            return ResponseEntity.ok(clienteViewDTO);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}
