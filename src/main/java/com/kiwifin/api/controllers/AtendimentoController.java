package com.kiwifin.api.controllers;

import com.itextpdf.text.DocumentException;
import com.kiwifin.api.DTO.create.AtendimentoCreateDTO;
import com.kiwifin.api.DTO.update.AtendimentoUpdateDTO;
import com.kiwifin.api.DTO.view.AtendimentoViewDTO;
import com.kiwifin.api.service.AtendimentoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/atendimento")
@CrossOrigin(origins = "*")
public class AtendimentoController extends ApiController{

    AtendimentoService service;

    public AtendimentoController(AtendimentoService service) {
        this.service = service;
    }

    @ApiOperation(value = "Adiciona atendimento", notes = "Adiciona atendimento",
            response = AtendimentoViewDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atendimento adicionado"),
            @ApiResponse(code = 204, message = "Nenhum atendimento adicionado"),
            @ApiResponse(code = 400, message = "Chamada incorreta"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @PostMapping("/adicionar")
    public ResponseEntity<Object> adicionar(@RequestBody AtendimentoCreateDTO dto) {

        try {
            return ResponseEntity.ok(service.adicionar(dto));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Lista todos atendimentos paginado.", notes = "Lista todos atendimentos paginado.", response = AtendimentoViewDTO.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista de atendimentos encontrada"),
            @ApiResponse(code = 204, message = "Nenhum atendimento encontrado"),
            @ApiResponse(code = 400, message = "Chamada incorreta do serviço"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @GetMapping(value = "/listar/filtrado/{page}/{size}")
    public ResponseEntity<Object> findAtendimentoFiltrado(@PathVariable("page") int page, @PathVariable("size") int size) {
        try {
            return ResponseEntity.ok(service.buscarAtendimentosPaginado(page, size));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Busca atendimento por id.", notes = "Busca atendimentos por id.", response = AtendimentoViewDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atendimento encontrada"),
            @ApiResponse(code = 204, message = "Nenhum atendimento encontrado"),
            @ApiResponse(code = 400, message = "Chamada incorreta do serviço"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @GetMapping(value = "/buscar/{id}")
    public ResponseEntity<Object> findAtendimentoPorId(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Busca atendimento por cliente.", notes = "Busca atendimentos por cliente.", response = AtendimentoViewDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atendimento encontrada"),
            @ApiResponse(code = 204, message = "Nenhum atendimento encontrado"),
            @ApiResponse(code = 400, message = "Chamada incorreta do serviço"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @GetMapping(value = "/buscar/cliente/{id}")
    public ResponseEntity<Object> findAtendimentoPorCliente(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(service.buscarPorCliente(id));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Atendente assumi atendimento.", notes = "Atendente assumi atendimento.", response = AtendimentoViewDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atendimento assumido por atendente"),
            @ApiResponse(code = 204, message = "Nenhum atendimento assumido"),
            @ApiResponse(code = 400, message = "Chamada incorreta do serviço"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @PutMapping(value = "/atendente/assumir")
    public ResponseEntity<Object> atendenteAssumirAtendimento(@RequestBody AtendimentoUpdateDTO dto) {
        try {
            return ResponseEntity.ok(service.atendentePegarAtendimento(dto));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Supervisor acompanha atendimento.", notes = "Supervisor acompanha atendimento.", response = AtendimentoViewDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atendimento supervisionado"),
            @ApiResponse(code = 204, message = "Nenhum atendimento supervisionado"),
            @ApiResponse(code = 400, message = "Chamada incorreta do serviço"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @PutMapping(value = "/supervisor/assumir")
    public ResponseEntity<Object> supervisorAssumirAtendimento(@RequestBody AtendimentoUpdateDTO dto) {
        try {
            return ResponseEntity.ok(service.supervisorPegarAtendimento(dto));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Atendente fecha atendimento.", notes = "Atendente fecha atendimento.", response = AtendimentoViewDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atendimento fechado por atendente"),
            @ApiResponse(code = 204, message = "Nenhum atendimento fechado"),
            @ApiResponse(code = 400, message = "Chamada incorreta do serviço"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @PutMapping(value = "/atendente/fechar")
    public ResponseEntity<Object> atendenteFechaAtendimento(@RequestBody AtendimentoUpdateDTO dto) {
        try {
            return ResponseEntity.ok(service.fecharAtendimento(dto));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Reabrir atendimento.", notes = "Reabrir atendimento.", response = AtendimentoViewDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atendimento reaberto"),
            @ApiResponse(code = 204, message = "Nenhum atendimento reaberto"),
            @ApiResponse(code = 400, message = "Chamada incorreta do serviço"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @PutMapping(value = "/reabrir/{id}/{justificativa}")
    public ResponseEntity<Object> reabrirAtendimento(@PathVariable("id") Long id, @PathVariable("justificativa") String justificativa) {
        try {
            return ResponseEntity.ok(service.reabrirAtendimento(id, justificativa));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Empresa põe uma pendência ao atendimento.", notes = "Empresa põe uma pendência ao atendimento.", response = AtendimentoViewDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atendimento em pendência"),
            @ApiResponse(code = 204, message = "Nenhum atendimento em pendência"),
            @ApiResponse(code = 400, message = "Chamada incorreta do serviço"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @PutMapping(value = "/questionamento/empresa")
    public ResponseEntity<Object> questionamentoEmpresa(@RequestBody AtendimentoUpdateDTO dto) {
        try {
            return ResponseEntity.ok(service.atendimentoEmPendencia(dto));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Cliente responde pendência ao atendimento.", notes = "Cliente responde pendência ao atendimento.", response = AtendimentoViewDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atendimento sem pendência"),
            @ApiResponse(code = 204, message = "Nenhum atendimento sem pendência"),
            @ApiResponse(code = 400, message = "Chamada incorreta do serviço"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @PutMapping(value = "/questionamento/resposta")
    public ResponseEntity<Object> respostaQuestionamentoEmpresa(@RequestBody AtendimentoUpdateDTO dto) {
        try {
            return ResponseEntity.ok(service.atendimentoRespostaPendencia(dto));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Transferir motivo do atendimento pelo atendente.", notes = "Transferir motivo do atendimento pelo atendente.", response = AtendimentoViewDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atendimento transferido"),
            @ApiResponse(code = 204, message = "Nenhum atendimento transferido"),
            @ApiResponse(code = 400, message = "Chamada incorreta do serviço"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @PutMapping(value = "/transferir/motivo/atendente")
    public ResponseEntity<Object> transferirMotivoPorAtendente(@RequestBody AtendimentoUpdateDTO dto) {
        try {
            return ResponseEntity.ok(service.transferirMotivoPorAtendente(dto));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Transferir motivo do atendimento pelo supervisor.", notes = "Transferir motivo do atendimento pelo supervisor.", response = AtendimentoViewDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atendimento transferido"),
            @ApiResponse(code = 204, message = "Nenhum atendimento transferido"),
            @ApiResponse(code = 400, message = "Chamada incorreta do serviço"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @PutMapping(value = "/transferir/motivo/supervisor")
    public ResponseEntity<Object> transferirMotivoPorSupervisor(@RequestBody AtendimentoUpdateDTO dto) {
        try {
            return ResponseEntity.ok(service.transferirMotivoPorSupervisor(dto));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Encerrar atendimento.", notes = "Encerrar atendimento.", response = AtendimentoViewDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atendimento encerrado"),
            @ApiResponse(code = 204, message = "Nenhum atendimento encerrado"),
            @ApiResponse(code = 400, message = "Chamada incorreta do serviço"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @PutMapping(value = "/encerrar")
    public ResponseEntity<Object> encerrarAtendimento(@RequestBody AtendimentoUpdateDTO dto) {
        try {
            return ResponseEntity.ok(service.finalizarAtendimento(dto));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Exporta atendimentos PDF.", notes = "Exporta atendimentos PDF.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atendimento exportado"),
            @ApiResponse(code = 204, message = "Nenhum atendimento exportado"),
            @ApiResponse(code = 400, message = "Chamada incorreta do serviço"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @GetMapping("/exportar/pdf/{id}")
    public ResponseEntity<byte[]> exportarAtendimentosPdf(@PathVariable("id") List<Long> id) throws DocumentException {


        byte[] atdPfd = service.exportarAtendimentosPDF(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        Date dataHoraAtual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMddHHmmss");
        headers.setContentDispositionFormData("attachment", "atendimentos"+ formato.format(dataHoraAtual) +".pdf");

        return new ResponseEntity<>(atdPfd, headers, HttpStatus.OK);
    }

    @ApiOperation(value = "Exporta atendimentos xlsx.", notes = "Exporta atendimentos xlsx.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atendimento exportado"),
            @ApiResponse(code = 204, message = "Nenhum atendimento exportado"),
            @ApiResponse(code = 400, message = "Chamada incorreta do serviço"),
            @ApiResponse(code = 500, message = "Erro interno")
    }
    )
    @GetMapping("/exportar/xlsx/{id}")
    public ResponseEntity<byte[]> exportarAtendimentosExcel(@PathVariable("id") List<Long> id) throws IOException {


        byte[] atdxlsx = service.exportarParaExcel(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        Date dataHoraAtual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMddHHmmss");
        headers.setContentDispositionFormData("attachment", "atendimentos"+ formato.format(dataHoraAtual) +".xlsx");

        return new ResponseEntity<>(atdxlsx, headers, HttpStatus.OK);
    }


}
