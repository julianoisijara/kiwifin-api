package com.kiwifin.api.service.conversor;

import com.kiwifin.api.DTO.view.DepartamentoViewDTO;
import com.kiwifin.api.entities.Departamento;
import org.springframework.stereotype.Service;

@Service
public class DepConversorService extends GenericConversor<Departamento, DepartamentoViewDTO>{

    public DepartamentoViewDTO entity2Dto(Departamento departamento) {

        DepartamentoViewDTO dto = new DepartamentoViewDTO();

        dto.setIdDepartamento(departamento.getIdDepartamento());
        dto.setNome(departamento.getNome());
        dto.setStatus(departamento.getStatus());

        return dto;
    }
}
