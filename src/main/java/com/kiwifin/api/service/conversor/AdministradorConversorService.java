package com.kiwifin.api.service.conversor;

import com.kiwifin.api.DTO.view.AdministradorViewDTO;
import com.kiwifin.api.entities.Administrador;
import org.springframework.stereotype.Service;

@Service
public class AdministradorConversorService extends GenericConversor<Administrador, AdministradorViewDTO>{

    private final DepartamentoConversorService departamentoConversorService;
    private final PerfilConversorService perfilConversorService;

    public AdministradorConversorService(DepartamentoConversorService departamentoConversorService, PerfilConversorService perfilConversorService) {
        this.departamentoConversorService = departamentoConversorService;
        this.perfilConversorService = perfilConversorService;
    }

    @Override
    public AdministradorViewDTO entity2Dto(Administrador administrador) {

        AdministradorViewDTO dto = new AdministradorViewDTO();

        dto.setIdColaborador(administrador.getIdColaborador());
        dto.setNome(administrador.getNome());
        dto.setEmail(administrador.getEmail());
        dto.setCpf(administrador.getCpf());
        dto.setDepartamento(departamentoConversorService.entity2Dto(administrador.getDepartamento()));
        dto.setPerfil(perfilConversorService.entity2Dto(administrador.getPerfil()));

        return dto;
    }
}
