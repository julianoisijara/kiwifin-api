package com.kiwifin.api.service.conversor;

import com.kiwifin.api.DTO.view.MotivoViewDTO;
import com.kiwifin.api.entities.Motivo;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class MotivoConversorService extends GenericConversor<Motivo, MotivoViewDTO>{

    private DepConversorService depConversorService;

    public MotivoConversorService(DepConversorService depConversorService) {
        this.depConversorService = depConversorService;
    }

    @Override
    public MotivoViewDTO entity2Dto(Motivo motivo) {

        MotivoViewDTO dto = new MotivoViewDTO();

        dto.setIdMotivo(motivo.getIdMotivo());
        dto.setNome(motivo.getNome());
        dto.setStatus(motivo.getStatus());
        dto.setPrazo(motivo.getPrazo());
        dto.setDepartamento(depConversorService.entity2Dto(motivo.getDepartamento()));

        return dto;
    }

}
