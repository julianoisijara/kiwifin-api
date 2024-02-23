package com.kiwifin.api.service;

import com.kiwifin.api.DTO.view.AtendimentoHistoricoViewDTO;
import com.kiwifin.api.entities.AtendimentoHistorico;
import com.kiwifin.api.repositories.AtendimentoHistoricoRepository;
import com.kiwifin.api.service.conversor.AtendimentoHistoricoConversorService;
import com.kiwifin.api.service.data.GenericDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AtendimentoHistoricoService extends GenericDataService<AtendimentoHistorico, Long, AtendimentoHistoricoRepository> {


    private AtendimentoHistoricoConversorService conversorService;

    @Autowired
    public AtendimentoHistoricoService(AtendimentoHistoricoConversorService conversorService) {
        this.conversorService = conversorService;
    }


    public List<AtendimentoHistoricoViewDTO> buscarHistorico(Long idAtendimento, Long idAtendente) {
        return conversorService.entityList2DtoList(pesquisar(idAtendimento, idAtendente));
    }


    public AtendimentoHistorico incluir(AtendimentoHistorico atendimentoHistorico) {
        AtendimentoHistorico novoAtendimentoHis = new AtendimentoHistorico();

        novoAtendimentoHis.setAtendimento(atendimentoHistorico.getAtendimento());
        novoAtendimentoHis.setAtendente(atendimentoHistorico.getAtendente());
        novoAtendimentoHis.setDataAlteracao(atendimentoHistorico.getDataAlteracao());
        novoAtendimentoHis.setTextoObservacao(atendimentoHistorico.getTextoObservacao());
        repository.save(novoAtendimentoHis);

        return novoAtendimentoHis;
    }

    public List<AtendimentoHistorico> pesquisar(Long idAtendimento, Long idAtendente) {

        List<AtendimentoHistorico>  atendimentoHistoricoList = new ArrayList<>();

        if (idAtendimento != null) {
            atendimentoHistoricoList.addAll(repository.findAtendimentoHistoricoByAtendimentoAndIdAtendimento(idAtendimento));
        }
        if (idAtendente != null) {
            atendimentoHistoricoList.addAll(repository.findAtendimentoHistoricoByAtendenteAndIdAtendimentoHistorico(idAtendente));
        }

        return atendimentoHistoricoList;
    }

}
