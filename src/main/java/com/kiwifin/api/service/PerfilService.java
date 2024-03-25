package com.kiwifin.api.service;

import com.kiwifin.api.DTO.view.PerfilViewDTO;
import com.kiwifin.api.entities.Perfil;
import com.kiwifin.api.repositories.PerfilRepository;
import com.kiwifin.api.service.conversor.PerfilConversorService;
import com.kiwifin.api.service.data.GenericDataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilService extends GenericDataService<Perfil, Long, PerfilRepository> {

    PerfilConversorService perfilConversorService;

    public PerfilService(PerfilConversorService perfilConversorService) {
        this.perfilConversorService = perfilConversorService;
    }

    public Perfil buscarPermissao(Long id) {
        return getOne(id);
    }

    public List<Perfil> getListaPerfil(List<Long> idPerfil) {
        return repository.findAllById(idPerfil);
    }

    public List<PerfilViewDTO> buscarPerfis() {
        return perfilConversorService.entityList2DtoList(repository.findAll());
    }


}
