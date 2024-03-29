package com.kiwifin.api.service.conversor;

import com.kiwifin.api.DTO.view.DepartamentoViewDTO;
import com.kiwifin.api.entities.Departamento;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class DepartamentoConversorService extends GenericConversor<Departamento, DepartamentoViewDTO>{

    private MotivoConversorService motivoConversorService;

    public DepartamentoConversorService(MotivoConversorService motivoConversorService) {
        this.motivoConversorService = motivoConversorService;
    }

    public DepartamentoViewDTO entity2Dto(Departamento departamento) {

        DepartamentoViewDTO dto = new DepartamentoViewDTO();

        dto.setIdDepartamento(departamento.getIdDepartamento());
        dto.setNome(departamento.getNome());
        dto.setStatus(departamento.getStatus());
        dto.setMotivoViewDTO(departamento.getMotivos() != null ? motivoConversorService.entityList2DtoList(departamento.getMotivos()) : null);

        return dto;
    }
}
