package com.kiwifin.api.service.conversor;

import com.kiwifin.api.DTO.view.SupervisorQualidadeViewDTO;
import com.kiwifin.api.entities.SupervisorQualidade;
import org.springframework.stereotype.Service;

@Service
public class SupervisorQualidadeConversorService extends GenericConversor<SupervisorQualidade, SupervisorQualidadeViewDTO> {

    private final DepartamentoConversorService departamentoConversorService;
    private final PerfilConversorService perfilConversorService;

    public SupervisorQualidadeConversorService(DepartamentoConversorService departamentoConversorService, PerfilConversorService perfilConversorService) {
        this.departamentoConversorService = departamentoConversorService;
        this.perfilConversorService = perfilConversorService;
    }

    @Override
    public SupervisorQualidadeViewDTO entity2Dto(SupervisorQualidade supervisorQualidade) {

        SupervisorQualidadeViewDTO dto = new SupervisorQualidadeViewDTO();

        dto.setIdColaborador(supervisorQualidade.getIdColaborador());
        dto.setNome(supervisorQualidade.getNome());
        dto.setEmail(supervisorQualidade.getEmail());
        dto.setCpf(supervisorQualidade.getCpf());
        dto.setDepartamento(departamentoConversorService.entity2Dto(supervisorQualidade.getDepartamento()));
        dto.setPerfil(perfilConversorService.entity2Dto(supervisorQualidade.getPerfil()));

        return dto;
    }
}
