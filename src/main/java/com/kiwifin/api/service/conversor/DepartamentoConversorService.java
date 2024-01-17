package com.kiwifin.api.service.conversor;

import com.kiwifin.api.DTO.view.DepartamentoViewDTO;
import com.kiwifin.api.entities.Departamento;
import org.springframework.stereotype.Service;

@Service
public class DepartamentoConversorService extends GenericConversor<Departamento, DepartamentoViewDTO>{

    public DepartamentoViewDTO entity2Dto(Departamento departamento) {

        DepartamentoViewDTO dto = new DepartamentoViewDTO();

        dto.setIdDepartamento(departamento.getIdDepartamento());
        dto.setNome(departamento.getNome());
        dto.setStatus(departamento.getStatusDepartamento());

        return dto;
    }
}
