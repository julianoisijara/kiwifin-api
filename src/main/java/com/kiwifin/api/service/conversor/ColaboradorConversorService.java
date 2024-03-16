package com.kiwifin.api.service.conversor;

import com.kiwifin.api.DTO.view.ColaboradorViewDTO;
import com.kiwifin.api.entities.Colaborador;
import org.springframework.stereotype.Service;

@Service
public class ColaboradorConversorService extends GenericConversor<Colaborador, ColaboradorViewDTO>{

    private final DepartamentoConversorService departamentoConversorService;
    private final PerfilConversorService perfilConversorService;

    public ColaboradorConversorService(DepartamentoConversorService departamentoConversorService, PerfilConversorService perfilConversorService) {
        this.departamentoConversorService = departamentoConversorService;
        this.perfilConversorService = perfilConversorService;
    }

    @Override
    public ColaboradorViewDTO entity2Dto(Colaborador colaborador) {

        ColaboradorViewDTO dto = new ColaboradorViewDTO();

        dto.setIdColaborador(colaborador.getIdColaborador());
        dto.setNome(colaborador.getNome());
        dto.setEmail(colaborador.getEmail());
        dto.setCpf(colaborador.getCpf());
        dto.setDepartamento(departamentoConversorService.entity2Dto(colaborador.getDepartamento()));
        dto.setPerfil(perfilConversorService.entityList2DtoList(colaborador.getListaPerfis()));

        return dto;
    }
}
