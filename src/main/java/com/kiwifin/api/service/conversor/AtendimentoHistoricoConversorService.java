package com.kiwifin.api.service.conversor;

import com.kiwifin.api.DTO.view.AtendimentoHistoricoViewDTO;
import com.kiwifin.api.entities.AtendimentoHistorico;
import org.springframework.stereotype.Service;

@Service
public class AtendimentoHistoricoConversorService extends GenericConversor<AtendimentoHistorico, AtendimentoHistoricoViewDTO> {

    private final ColaboradorConversorService colaboradorConversorService;

    public AtendimentoHistoricoConversorService(ColaboradorConversorService colaboradorConversorService) {
        this.colaboradorConversorService = colaboradorConversorService;
    }

    @Override
    public AtendimentoHistoricoViewDTO entity2Dto(AtendimentoHistorico atendimentoHistorico) {

        AtendimentoHistoricoViewDTO dto = new AtendimentoHistoricoViewDTO();
        dto.setIdAtendimentoHistorico(atendimentoHistorico.getIdAtendimentoHistorico());
        dto.setAtendente(atendimentoHistorico.getAtendente() != null ? colaboradorConversorService.entity2Dto(atendimentoHistorico.getAtendente()) : null);
        dto.setDataAlteracao(atendimentoHistorico.getDataAlteracao());
        dto.setTextoObservacao(atendimentoHistorico.getTextoObservacao());

        return dto;
    }
}
