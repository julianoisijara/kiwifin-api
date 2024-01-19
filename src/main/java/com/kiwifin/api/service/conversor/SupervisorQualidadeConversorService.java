package com.kiwifin.api.service.conversor;

import com.kiwifin.api.DTO.view.SupervisorQualidadeViewDTO;
import com.kiwifin.api.entities.SupervisorQualidade;
import org.springframework.stereotype.Service;

@Service
public class SupervisorQualidadeConversorService extends GenericConversor<SupervisorQualidade, SupervisorQualidadeViewDTO> {
    @Override
    public SupervisorQualidadeViewDTO entity2Dto(SupervisorQualidade supervisorQualidade) {

        SupervisorQualidadeViewDTO dto = new SupervisorQualidadeViewDTO();

        dto.setIdColaborador(supervisorQualidade.getIdColaborador());
        dto.setNome(supervisorQualidade.getNome());
        dto.setEmail(supervisorQualidade.getEmail());
        dto.setCpf(supervisorQualidade.getCpf());
        dto.setSenha(supervisorQualidade.getSenha());
        dto.setDepartamento(supervisorQualidade.getDepartamento());
        dto.setPerfil(supervisorQualidade.getPerfil());

        return dto;
    }
}
