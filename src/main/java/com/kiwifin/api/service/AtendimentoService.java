package com.kiwifin.api.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.kiwifin.api.DTO.create.AtendimentoCreateDTO;
import com.kiwifin.api.DTO.update.AtendimentoUpdateDTO;
import com.kiwifin.api.DTO.view.AtendimentoViewDTO;
import com.kiwifin.api.dominio.AtendimentoEnum;
import com.kiwifin.api.entities.Atendimento;
import com.kiwifin.api.entities.AtendimentoHistorico;
import com.kiwifin.api.entities.Cliente;
import com.kiwifin.api.repositories.AtendimentoRepository;
import com.kiwifin.api.service.conversor.AtendimentoConversorService;
import com.kiwifin.api.service.data.GenericDataService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AtendimentoService extends GenericDataService<Atendimento, Long, AtendimentoRepository> {


    private AtendimentoConversorService conversorService;
    private AtendimentoHistoricoService historicoService;
    private ClienteService clienteService;
    private ColaboradorService colaboradorService;
    private MotivoService motivoService;

    @Autowired
    public AtendimentoService(AtendimentoConversorService conversorService, AtendimentoHistoricoService historicoService, ClienteService clienteService, ColaboradorService colaboradorService, MotivoService motivoService) {
        this.conversorService = conversorService;
        this.historicoService = historicoService;
        this.clienteService = clienteService;
        this.colaboradorService = colaboradorService;
        this.motivoService = motivoService;
    }


    public AtendimentoViewDTO adicionar(AtendimentoCreateDTO dto) {
        return conversorService.entity2Dto(criar(dto));
    }

    public List<AtendimentoViewDTO> buscarAtendimentosPaginado(int page, int size) {
        return conversorService.entityList2DtoList(pesquisarAtendimentosPaginado(page, size));
    }

    public AtendimentoViewDTO buscarPorId(Long id) {
        return conversorService.entity2Dto(pesquisarPorId(id));
    }

    public List<AtendimentoViewDTO> buscarPorCliente(Long idCliente) {
        return conversorService.entityList2DtoList(consultarPorCliente(idCliente));
    }

    public AtendimentoViewDTO atendentePegarAtendimento(AtendimentoUpdateDTO updateDTO) {
        return conversorService.entity2Dto(assumirAtendimentoAtendente(updateDTO));
    }

    public AtendimentoViewDTO supervisorPegarAtendimento(AtendimentoUpdateDTO updateDTO) {
        return conversorService.entity2Dto(supervisorAcompanharAtendimento(updateDTO));
    }

    public AtendimentoViewDTO fecharAtendimento(AtendimentoUpdateDTO updateDTO) {
        return conversorService.entity2Dto(detalhamentoEmpresaFechamento(updateDTO));
    }

    public AtendimentoViewDTO reabrirAtendimento(Long id, String justificativa) {
        return conversorService.entity2Dto(reabrir(id, justificativa));
    }

    public AtendimentoViewDTO atendimentoEmPendencia(AtendimentoUpdateDTO updateDTO) {
        return conversorService.entity2Dto(questionamentoEmpresa(updateDTO));
    }


    public AtendimentoViewDTO atendimentoRespostaPendencia(AtendimentoUpdateDTO updateDTO) {
        return conversorService.entity2Dto(respostaQuestionamento(updateDTO));
    }

    public AtendimentoViewDTO transferirMotivoPorAtendente(AtendimentoUpdateDTO updateDTO) {
        return conversorService.entity2Dto(transferirMotivoColaborador(updateDTO));
    }

    public AtendimentoViewDTO transferirMotivoPorSupervisor(AtendimentoUpdateDTO updateDTO) {
        return conversorService.entity2Dto(transferirMotivoSupervisor(updateDTO));
    }

    public AtendimentoViewDTO finalizarAtendimento(AtendimentoUpdateDTO updateDTO) {
        return conversorService.entity2Dto(encerrarAtendimento(updateDTO));
    }


    public Atendimento pesquisarPorId(Long id) {
        return getOne(id);
    }

    public List<Atendimento> pesquisarAtendimentosPaginado(int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        return repository.findByAtendimentosPaginado(pageRequest);
    }


    public List<Atendimento> consultarPorCliente(Long idCliente) {
        Cliente cliente = clienteService.pesquisarClientes(idCliente, null, null).get(0);
        return repository.findByClienteEqualsOrderByIdAtendimentoAsc(cliente);
    }


    public Atendimento criar(AtendimentoCreateDTO atendimentoCreateDTO) {

        Atendimento novoAtendimento = new Atendimento();

        Date dataHoraAtual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMddHHmmss");

        novoAtendimento.setStatusAndamento(AtendimentoEnum.ABERTO.getSigla());
        novoAtendimento.setStatusPrazo(motivoService.getOne(atendimentoCreateDTO.getMotivo()).getPrazo());
        novoAtendimento.setDataProtocolo(dataHoraAtual);
        novoAtendimento.setAssunto(atendimentoCreateDTO.getAssunto().toUpperCase());
        novoAtendimento.setDetalhamentoSolicitacao(atendimentoCreateDTO.getDetalhamentoSolicitacao().toUpperCase());
        novoAtendimento.setMotivo(motivoService.getOne(atendimentoCreateDTO.getMotivo()));
        novoAtendimento.setCliente(clienteService.pesquisarClientes(atendimentoCreateDTO.getCliente(), null, null).get(0));
        novoAtendimento.setProtocolo(formato.format(dataHoraAtual) + novoAtendimento.getCliente().getIdCliente());
        repository.save(novoAtendimento);

        AtendimentoHistorico novoHistorico = new AtendimentoHistorico();
        novoHistorico.setAtendimento(novoAtendimento);
        novoHistorico.setDataAlteracao(dataHoraAtual);
        novoHistorico.setTextoObservacao("CRIAÇÃO");
        historicoService.incluir(novoHistorico);
        List<AtendimentoHistorico> historico = new ArrayList<>();
        historico.add(novoHistorico);
        novoAtendimento.setHistorico(historico);

        return novoAtendimento;
    }

    public Atendimento supervisorAcompanharAtendimento(AtendimentoUpdateDTO updateDTO) {
        Atendimento atendimento = getOne(updateDTO.getIdAtendimento());

        atendimento.setSupervisorQualidade(colaboradorService.getOne(updateDTO.getSupervisorQualidade()));
        repository.save(atendimento);

        AtendimentoHistorico novoHistorico = new AtendimentoHistorico();
        novoHistorico.setAtendimento(atendimento);
        novoHistorico.setAtendente(atendimento.getAtendente());
        novoHistorico.setDataAlteracao(new Date());
        novoHistorico.setTextoObservacao("SUPERVIROR " + atendimento.getSupervisorQualidade().getIdColaborador() +  " ACOMPANHA ATENDIMENTO");
        historicoService.incluir(novoHistorico);

        return atendimento;
    }


    public Atendimento assumirAtendimentoAtendente(AtendimentoUpdateDTO updateDTO) {

        Atendimento atendimento = getOne(updateDTO.getIdAtendimento());
        atendimento.setAtendente(colaboradorService.getOne(updateDTO.getAtendente()));
        atendimento.setStatusAndamento(AtendimentoEnum.EM_ATENDIMENTO.getSigla());
        repository.save(atendimento);

        AtendimentoHistorico novoHistorico = new AtendimentoHistorico();
        novoHistorico.setAtendimento(atendimento);
        novoHistorico.setAtendente(atendimento.getAtendente());
        novoHistorico.setDataAlteracao(new Date());
        novoHistorico.setTextoObservacao("Atendente " + atendimento.getAtendente().getIdColaborador() +  " CAPTUROU ATENDIMENTO");
        historicoService.incluir(novoHistorico);

        return atendimento;
    }



    public Atendimento detalhamentoEmpresaFechamento(AtendimentoUpdateDTO updateDTO) {

        Atendimento atendimentoDetalhamentoEmpresa = getOne(updateDTO.getIdAtendimento());
        atendimentoDetalhamentoEmpresa.setDetalhamentoEmpresa(updateDTO.getDetalhamentoEmpresa());
        atendimentoDetalhamentoEmpresa.setStatusAndamento(AtendimentoEnum.FECHADO.getSigla());
        repository.save(atendimentoDetalhamentoEmpresa);

        AtendimentoHistorico novoHistorico = new AtendimentoHistorico();
        novoHistorico.setAtendimento(atendimentoDetalhamentoEmpresa);
        novoHistorico.setAtendente(atendimentoDetalhamentoEmpresa.getAtendente());
        novoHistorico.setDataAlteracao(new Date());
        novoHistorico.setTextoObservacao("FECHADO POR Atendente: " + atendimentoDetalhamentoEmpresa.getAtendente().getIdColaborador() + " DETALHAMENTO: " + atendimentoDetalhamentoEmpresa.getDetalhamentoEmpresa());
        historicoService.incluir(novoHistorico);

        return atendimentoDetalhamentoEmpresa;
    }

    public Atendimento reabrir(Long id, String justificativa) {

        Atendimento atendimento = getOne(id);

        if (atendimento.getStatusAndamento().equals(AtendimentoEnum.FECHADO.getSigla())) {
            atendimento.setStatusAndamento(AtendimentoEnum.REABERTO.getSigla());
            repository.save(atendimento);

            AtendimentoHistorico novoHistorico = new AtendimentoHistorico();
            novoHistorico.setAtendimento(atendimento);
            novoHistorico.setAtendente(atendimento.getAtendente());
            novoHistorico.setDataAlteracao(new Date());
            novoHistorico.setTextoObservacao("REABRIR: " + justificativa);
            historicoService.incluir(novoHistorico);

        }
        return atendimento;
    }

    public Atendimento questionamentoEmpresa(AtendimentoUpdateDTO updateDTO) {

        Atendimento atendimentoQuestionamentoEmpresa = getOne(updateDTO.getIdAtendimento());
        if (!atendimentoQuestionamentoEmpresa.getStatusAndamento().equals(AtendimentoEnum.FECHADO.getSigla()) || !atendimentoQuestionamentoEmpresa.getStatusAndamento().equals(AtendimentoEnum.ENCERRADO.getSigla())) {
            atendimentoQuestionamentoEmpresa.setQuestionamentoEmpresa(updateDTO.getQuestionamentoEmpresa());
            atendimentoQuestionamentoEmpresa.setStatusAndamento(AtendimentoEnum.PENDENTE.getSigla());
            repository.save(atendimentoQuestionamentoEmpresa);

            AtendimentoHistorico novoHistorico = new AtendimentoHistorico();
            novoHistorico.setAtendimento(atendimentoQuestionamentoEmpresa);
            novoHistorico.setAtendente(atendimentoQuestionamentoEmpresa.getAtendente());
            novoHistorico.setDataAlteracao(new Date());
            novoHistorico.setTextoObservacao("QUESTIONAMENTO EMPRESA: " + atendimentoQuestionamentoEmpresa.getQuestionamentoEmpresa());
            historicoService.incluir(novoHistorico);
        }

        return atendimentoQuestionamentoEmpresa;
    }

    public Atendimento respostaQuestionamento(AtendimentoUpdateDTO updateDTO) {

        Atendimento atendimentoRespostaQuestionamento = getOne(updateDTO.getIdAtendimento());

        if (atendimentoRespostaQuestionamento.getStatusAndamento().equals(AtendimentoEnum.PENDENTE.getSigla())) {
            atendimentoRespostaQuestionamento.setRespostaQuestionamento(updateDTO.getRespostaQuestionamento());
            atendimentoRespostaQuestionamento.setStatusAndamento(AtendimentoEnum.EM_ATENDIMENTO.getSigla());
            repository.save(atendimentoRespostaQuestionamento);

            AtendimentoHistorico novoHistorico = new AtendimentoHistorico();
            novoHistorico.setAtendimento(atendimentoRespostaQuestionamento);
            novoHistorico.setAtendente(atendimentoRespostaQuestionamento.getAtendente());
            novoHistorico.setDataAlteracao(new Date());
            novoHistorico.setTextoObservacao("RESPOSTA QUESTIONAMENTO: " + atendimentoRespostaQuestionamento.getRespostaQuestionamento());
            historicoService.incluir(novoHistorico);
        }

        return atendimentoRespostaQuestionamento;
    }

    public Atendimento transferirMotivoColaborador(AtendimentoUpdateDTO updateDTO) {

        Atendimento transferirAtendimento = getOne(updateDTO.getIdAtendimento());
        if (!transferirAtendimento.getStatusAndamento().equals(AtendimentoEnum.FECHADO.getSigla()) ||
                !transferirAtendimento.getStatusAndamento().equals(AtendimentoEnum.ENCERRADO.getSigla()) ||
                !transferirAtendimento.getStatusAndamento().equals(AtendimentoEnum.PENDENTE.getSigla())) {

            transferirAtendimento.setMotivo(motivoService.getOne(updateDTO.getMotivo()));
            transferirAtendimento.setAtendente(null);
            transferirAtendimento.setStatusAndamento(AtendimentoEnum.ABERTO.getSigla());
            repository.save(transferirAtendimento);

            AtendimentoHistorico novoHistorico = new AtendimentoHistorico();
            novoHistorico.setAtendimento(transferirAtendimento);
            novoHistorico.setDataAlteracao(new Date());
            novoHistorico.setTextoObservacao("TRANFERÊNCIA ATENDIMENTO: Colaborador " + updateDTO.getAtendente() +
                    " TRANFERIU PARA MOTIVO " + transferirAtendimento.getMotivo().getIdMotivo() + " E DEPARTAMENTO " + transferirAtendimento.getMotivo().getDepartamento().getIdDepartamento() +
                    " JUSTIFICATIVA: " + updateDTO.getMsgServico());

            historicoService.incluir(novoHistorico);
        }


        return transferirAtendimento;
    }

    public Atendimento transferirMotivoSupervisor(AtendimentoUpdateDTO updateDTO) {

        Atendimento transferirAtendimento = getOne(updateDTO.getIdAtendimento());
        transferirAtendimento.setMotivo(motivoService.getOne(updateDTO.getMotivo()));
        transferirAtendimento.setSupervisorQualidade(colaboradorService.getOne(updateDTO.getSupervisorQualidade()));
        transferirAtendimento.setAtendente(null);
        transferirAtendimento.setStatusAndamento(AtendimentoEnum.ABERTO.getSigla());
        repository.save(transferirAtendimento);

        AtendimentoHistorico novoHistorico = new AtendimentoHistorico();
        novoHistorico.setAtendimento(transferirAtendimento);
        novoHistorico.setDataAlteracao(new Date());
        novoHistorico.setTextoObservacao("TRANFERÊNCIA ATENDIMENTO: SUPERVISOR " + updateDTO.getSupervisorQualidade() +
                " TRANFERIU PARA MOTIVO " + transferirAtendimento.getMotivo().getIdMotivo() + " E DEPARTAMENTO " + transferirAtendimento.getMotivo().getDepartamento().getIdDepartamento() +
                " JUSTIFICATIVA: " + updateDTO.getMsgServico());

        historicoService.incluir(novoHistorico);

        return transferirAtendimento;
    }

    public Atendimento encerrarAtendimento(AtendimentoUpdateDTO updateDTO) {
        Atendimento atendimentoEncerramento = getOne(updateDTO.getIdAtendimento());

        if (atendimentoEncerramento.getStatusAndamento().equals(AtendimentoEnum.REABERTO.getSigla())) {
            atendimentoEncerramento.setStatusAndamento(AtendimentoEnum.ENCERRADO.getSigla());
            repository.save(atendimentoEncerramento);

            AtendimentoHistorico novoHistorico = new AtendimentoHistorico();
            novoHistorico.setAtendimento(atendimentoEncerramento);
            novoHistorico.setAtendente(atendimentoEncerramento.getAtendente());
            novoHistorico.setDataAlteracao(new Date());
            novoHistorico.setTextoObservacao("ENCERRAMENTO: " + updateDTO.getMsgServico());
            historicoService.incluir(novoHistorico);
        }

        return atendimentoEncerramento;
    }

    public byte[] exportarAtendimentosPDF(List<Long> idAtendimentos) throws DocumentException {
        List<Atendimento> atendimentos = repository.findAllById(idAtendimentos);
        Document atendimentosPDF = new Document(PageSize.A4.rotate());
        atendimentosPDF.setMargins(-40, -40, 20, 40);
        atendimentosPDF.addCreationDate();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PdfWriter.getInstance(atendimentosPDF, stream);


        atendimentosPDF.open();
        PdfPTable tabela = new PdfPTable(14);
        PdfPCell header = new PdfPCell(new Phrase("Atendimentos"));
        header.setColspan(0);
        float[] widths = new float[] { 40f, 45f, 45f, 30f, 40f, 55f, 55f, 53f, 50f, 50f, 35f, 35f, 30f, 30f };
        tabela.setWidths(widths);


        tabela.addCell("Número");
        tabela.addCell("Protocolo");
        tabela.addCell("Andamento");
        tabela.addCell("Prazo");
        tabela.addCell("Assunto");
        tabela.addCell("Detalhamento\nSolicitação");
        tabela.addCell("Detalhamento\nEmpresa");
        tabela.addCell("Data\nAbertura");
        tabela.addCell("Questionamento\nEmpresa");
        tabela.addCell("Resposta\nQuestionamento");
        tabela.addCell("Motivo");
        tabela.addCell("Cliente");
        tabela.addCell("Atendente");
        tabela.addCell("Supervisor");

        try {

            List<AtendimentoViewDTO> atendimentoViewDTOList = conversorService.entityList2DtoList(atendimentos);
            for (AtendimentoViewDTO atendimento : atendimentoViewDTOList) {
                tabela.addCell(String.valueOf(atendimento.getIdAtendimento()));
                tabela.addCell(atendimento.getProtocolo());
                tabela.addCell(atendimento.getStatusAndamento());
                tabela.addCell(String.valueOf(atendimento.getStatusPrazo()));
                tabela.addCell(atendimento.getAssunto());
                tabela.addCell(atendimento.getDetalhamentoSolicitacao());
                tabela.addCell(atendimento.getDetalhamentoEmpresa() != null ? atendimento.getDetalhamentoEmpresa() : null);
                tabela.addCell(String.valueOf(atendimento.getDataProtocolo()));
                tabela.addCell(atendimento.getQuestionamentoEmpresa() != null ? atendimento.getQuestionamentoEmpresa() : null);
                tabela.addCell(atendimento.getRespostaQuestionamento() != null ? atendimento.getRespostaQuestionamento() : null);
                tabela.addCell(atendimento.getMotivo().getNome());
                tabela.addCell(atendimento.getCliente().getCpf());
                tabela.addCell(atendimento.getAtendente() != null ? atendimento.getAtendente().getCpf() : null);
                tabela.addCell(atendimento.getSupervisorQualidade() != null ? atendimento.getSupervisorQualidade().getCpf() : null);
            }

            atendimentosPDF.add(tabela);
            atendimentosPDF.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            atendimentosPDF.close();
        }
        return stream.toByteArray();
    }

    public byte[] exportarParaExcel(List<Long> idAtendimentos) throws IOException {
        List<Atendimento> atendimentos = repository.findAllById(idAtendimentos);
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Atendimentos");


            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");

            headerRow.createCell(0).setCellValue("Número");
            headerRow.createCell(1).setCellValue("Protocolo");
            headerRow.createCell(2).setCellValue("Andamento");
            headerRow.createCell(3).setCellValue("Prazo");
            headerRow.createCell(4).setCellValue("Assunto");
            headerRow.createCell(5).setCellValue("Detalhamento Solicitação");
            headerRow.createCell(6).setCellValue("Detalhamento Empresa");
            headerRow.createCell(7).setCellValue("Data Abertura");
            headerRow.createCell(8).setCellValue("Questionamento Empresa");
            headerRow.createCell(9).setCellValue("Resposta Questionamento");
            headerRow.createCell(10).setCellValue("Motivo");
            headerRow.createCell(11).setCellValue("Cliente");
            headerRow.createCell(12).setCellValue("Atendente");
            headerRow.createCell(13).setCellValue("Supervisor");

            List<AtendimentoViewDTO> atendimentoViewDTOList = conversorService.entityList2DtoList(atendimentos);

            int rowNum = 1;
            for(AtendimentoViewDTO atd : atendimentoViewDTOList) {
                Row dataRow = sheet.createRow(rowNum++);
                dataRow.createCell(0).setCellValue(atd.getIdAtendimento());
                dataRow.createCell(1).setCellValue(atd.getProtocolo());
                dataRow.createCell(2).setCellValue(atd.getStatusAndamento());
                dataRow.createCell(3).setCellValue(String.valueOf(atd.getStatusPrazo()));
                dataRow.createCell(4).setCellValue(atd.getAssunto());
                dataRow.createCell(5).setCellValue(atd.getDetalhamentoSolicitacao());
                dataRow.createCell(6).setCellValue(atd.getDetalhamentoEmpresa() != null ? atd.getDetalhamentoEmpresa() : null);
                dataRow.createCell(7).setCellValue(String.valueOf(atd.getDataProtocolo()));
                dataRow.createCell(8).setCellValue(atd.getQuestionamentoEmpresa() != null ? atd.getQuestionamentoEmpresa() : null);
                dataRow.createCell(9).setCellValue(atd.getRespostaQuestionamento() != null ? atd.getRespostaQuestionamento() : null);
                dataRow.createCell(10).setCellValue(atd.getMotivo().getNome());
                dataRow.createCell(11).setCellValue(atd.getCliente().getCpf());
                dataRow.createCell(12).setCellValue(atd.getAtendente() != null ? atd.getAtendente().getCpf() : null);
                dataRow.createCell(13).setCellValue(atd.getSupervisorQualidade() != null ? atd.getSupervisorQualidade().getCpf() : null);
            }

            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                workbook.write(outputStream);
                return outputStream.toByteArray();
            }
        }
    }



}
