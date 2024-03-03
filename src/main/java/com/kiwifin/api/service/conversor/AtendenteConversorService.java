package com.kiwifin.api.service.conversor;

import com.kiwifin.api.DTO.view.AtendenteViewDTO;
import com.kiwifin.api.entities.Atendente;
import org.springframework.stereotype.Service;

@Service
public class AtendenteConversorService extends GenericConversor<Atendente, AtendenteViewDTO>{

    private final DepartamentoConversorService departamentoConversorService;
    private final PerfilConversorService perfilConversorService;

    public AtendenteConversorService(DepartamentoConversorService departamentoConversorService, PerfilConversorService perfilConversorService) {
        this.departamentoConversorService = departamentoConversorService;
        this.perfilConversorService = perfilConversorService;
    }

    @Override
    public AtendenteViewDTO entity2Dto(Atendente atendente) {

        AtendenteViewDTO dto = new AtendenteViewDTO();

        dto.setIdColaborador(atendente.getIdColaborador());
        dto.setNome(atendente.getNome());
        dto.setEmail(atendente.getEmail());
        dto.setCpf(atendente.getCpf());
        dto.setDepartamento(departamentoConversorService.entity2Dto(atendente.getDepartamento()));
        dto.setPerfil(perfilConversorService.entity2Dto(atendente.getPerfil()));

        return dto;
    }
}
