package com.kiwifin.api.service.conversor;

import com.kiwifin.api.DTO.view.MotivoViewDTO;
import com.kiwifin.api.entities.Motivo;
import org.springframework.stereotype.Service;

@Service
public class MotivoConversorService extends GenericConversor<Motivo, MotivoViewDTO>{
    @Override
    public MotivoViewDTO entity2Dto(Motivo motivo) {

        MotivoViewDTO dto = new MotivoViewDTO();

        dto.setIdMotivo(motivo.getIdMotivo());
        dto.setNome(motivo.getNome());
        dto.setStatus(motivo.getStatus());
        dto.setPrazo(motivo.getPrazo());
        dto.setDepartamento(motivo.getDepartamento());

        return dto;
    }

}
