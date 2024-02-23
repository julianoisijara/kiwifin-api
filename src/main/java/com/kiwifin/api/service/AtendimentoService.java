package com.kiwifin.api.service;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AtendimentoService extends GenericDataService<Atendimento, Long, AtendimentoRepository> {


    private AtendimentoConversorService conversorService;
    private AtendimentoHistoricoService historicoService;
    private ClienteService clienteService;
    private AtendenteService atendenteService;
    private SupervisorQualidadeService supervisorQualidadeService;
    private MotivoService motivoService;

    @Autowired
    public AtendimentoService(AtendimentoConversorService conversorService, AtendimentoHistoricoService historicoService, ClienteService clienteService, AtendenteService atendenteService, SupervisorQualidadeService supervisorQualidadeService, MotivoService motivoService) {
        this.conversorService = conversorService;
        this.historicoService = historicoService;
        this.clienteService = clienteService;
        this.atendenteService = atendenteService;
        this.supervisorQualidadeService = supervisorQualidadeService;
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
        return conversorService.entity2Dto(transferirMotivoAtendente(updateDTO));
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

        return novoAtendimento;
    }

    public Atendimento supervisorAcompanharAtendimento(AtendimentoUpdateDTO updateDTO) {
        Atendimento atendimento = getOne(updateDTO.getIdAtendimento());

        atendimento.setSupervisorQualidade(supervisorQualidadeService.getOne(updateDTO.getSupervisorQualidade()));
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
        atendimento.setAtendente(atendenteService.getOne(updateDTO.getAtendente()));
        atendimento.setStatusAndamento(AtendimentoEnum.EM_ATENDIMENTO.getSigla());
        repository.save(atendimento);

        AtendimentoHistorico novoHistorico = new AtendimentoHistorico();
        novoHistorico.setAtendimento(atendimento);
        novoHistorico.setAtendente(atendimento.getAtendente());
        novoHistorico.setDataAlteracao(new Date());
        novoHistorico.setTextoObservacao("ATENDENTE " + atendimento.getAtendente().getIdColaborador() +  " CAPTUROU ATENDIMENTO");
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
        novoHistorico.setTextoObservacao("FECHADO POR ATENDENTE: " + atendimentoDetalhamentoEmpresa.getAtendente().getIdColaborador() + " DETALHAMENTO: " + atendimentoDetalhamentoEmpresa.getDetalhamentoEmpresa());
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

    public Atendimento transferirMotivoAtendente(AtendimentoUpdateDTO updateDTO) {

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
            novoHistorico.setTextoObservacao("TRANFERÊNCIA ATENDIMENTO: ATENDENTE " + updateDTO.getAtendente() +
                    " TRANFERIU PARA MOTIVO " + transferirAtendimento.getMotivo().getIdMotivo() + " E DEPARTAMENTO " + transferirAtendimento.getMotivo().getDepartamento().getIdDepartamento() +
                    " JUSTIFICATIVA: " + updateDTO.getMsgServico());

            historicoService.incluir(novoHistorico);
        }


        return transferirAtendimento;
    }

    public Atendimento transferirMotivoSupervisor(AtendimentoUpdateDTO updateDTO) {

        Atendimento transferirAtendimento = getOne(updateDTO.getIdAtendimento());
        transferirAtendimento.setMotivo(motivoService.getOne(updateDTO.getMotivo()));
        transferirAtendimento.setSupervisorQualidade(supervisorQualidadeService.getOne(updateDTO.getSupervisorQualidade()));
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


}
