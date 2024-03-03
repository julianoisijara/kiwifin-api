package com.kiwifin.api.service.conversor;

import com.kiwifin.api.DTO.view.PerfilViewDTO;
import com.kiwifin.api.entities.Perfil;
import org.springframework.stereotype.Service;

@Service
public class PerfilConversorService extends GenericConversor<Perfil, PerfilViewDTO>{


    @Override
    public PerfilViewDTO entity2Dto(Perfil perfil) {

        PerfilViewDTO dto = new PerfilViewDTO();
        dto.setIdPerfil(perfil.getIdPerfil());
        dto.setDescricao(perfil.getDescricao());

        return dto;
    }
}
