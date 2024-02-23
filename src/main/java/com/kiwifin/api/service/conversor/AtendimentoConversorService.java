package com.kiwifin.api.service.conversor;

import com.kiwifin.api.DTO.view.AtendimentoViewDTO;
import com.kiwifin.api.dominio.AtendimentoEnum;
import com.kiwifin.api.entities.Atendimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class AtendimentoConversorService extends GenericConversor<Atendimento, AtendimentoViewDTO>{

    private final ClienteConversorService clienteConversorService;
    private final AtendenteConversorService atendenteConversorService;
    private final SupervisorQualidadeConversorService supervisorQualidadeConversorService;
    private final MotivoConversorService motivoConversorService;
    private final AtendimentoHistoricoConversorService historicoConversorService;

    @Autowired
    public AtendimentoConversorService(ClienteConversorService clienteConversorService, AtendenteConversorService atendenteConversorService, SupervisorQualidadeConversorService supervisorQualidadeConversorService, MotivoConversorService motivoConversorService, AtendimentoHistoricoConversorService historicoConversorService) {
        this.clienteConversorService = clienteConversorService;
        this.atendenteConversorService = atendenteConversorService;
        this.supervisorQualidadeConversorService = supervisorQualidadeConversorService;
        this.motivoConversorService = motivoConversorService;
        this.historicoConversorService = historicoConversorService;
    }

    @Override
    public AtendimentoViewDTO entity2Dto(Atendimento atendimento) {

        AtendimentoViewDTO dto = new AtendimentoViewDTO();
        
        dto.setIdAtendimento(atendimento.getIdAtendimento());
        dto.setProtocolo(atendimento.getProtocolo());
        dto.setStatusAndamento(AtendimentoEnum.getSigla(atendimento.getStatusAndamento()));
        dto.setStatusPrazo(atendimento.getStatusPrazo());
        dto.setDataProtocolo(atendimento.getDataProtocolo());
        dto.setAssunto(atendimento.getAssunto());
        dto.setDetalhamentoSolicitacao(atendimento.getDetalhamentoSolicitacao());
        dto.setDetalhamentoEmpresa(atendimento.getDetalhamentoEmpresa());
        dto.setQuestionamentoEmpresa(atendimento.getQuestionamentoEmpresa());
        dto.setRespostaQuestionamento(atendimento.getRespostaQuestionamento());
        dto.setMotivo(motivoConversorService.entity2Dto(atendimento.getMotivo()));
        dto.setCliente(clienteConversorService.entity2Dto(atendimento.getCliente()));
        dto.setAtendente(atendimento.getAtendente() != null ? atendenteConversorService.entity2Dto(atendimento.getAtendente()) : null);
        dto.setSupervisorQualidade(atendimento.getSupervisorQualidade() != null ? supervisorQualidadeConversorService.entity2Dto(atendimento.getSupervisorQualidade()) : null);
        dto.setHistorico(historicoConversorService.entityList2DtoList(atendimento.getHistorico()));
        
        return dto;
    }
}
