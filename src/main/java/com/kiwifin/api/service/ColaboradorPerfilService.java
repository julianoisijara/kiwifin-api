package com.kiwifin.api.service;

import com.kiwifin.api.entities.ColaboradorPerfil;
import com.kiwifin.api.repositories.ColaboradorPerfilRepository;
import com.kiwifin.api.service.data.GenericDataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColaboradorPerfilService extends GenericDataService<ColaboradorPerfil, Long, ColaboradorPerfilRepository> {


    List<ColaboradorPerfil> buscarPerfilColaborador(Long id) {
        return repository.findByIdColaboradorPerfil(id);
    }


}
