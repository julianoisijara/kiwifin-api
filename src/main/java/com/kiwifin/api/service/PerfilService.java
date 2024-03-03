package com.kiwifin.api.service;

import com.kiwifin.api.entities.Perfil;
import com.kiwifin.api.repositories.PerfilRepository;
import com.kiwifin.api.service.data.GenericDataService;
import org.springframework.stereotype.Service;

@Service
public class PerfilService extends GenericDataService<Perfil, Long, PerfilRepository> {


    public Perfil buscarPermissao(Long id) {
        return getOne(id);
    }


}
