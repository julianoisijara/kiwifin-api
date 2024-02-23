package com.kiwifin.api.service;

import com.kiwifin.api.DTO.create.AdministradorCreateDTO;
import com.kiwifin.api.DTO.update.AdministradorUpdateDTO;
import com.kiwifin.api.DTO.view.AdministradorViewDTO;
import com.kiwifin.api.entities.Administrador;
import com.kiwifin.api.repositories.AdministradorRepository;
import com.kiwifin.api.service.conversor.AdministradorConversorService;
import com.kiwifin.api.service.data.GenericDataService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorService extends GenericDataService<Administrador, Long, AdministradorRepository> {

    private AdministradorConversorService conversorService;
    private DepartamentoService departamentoService;

    public AdministradorService(AdministradorConversorService conversorService, DepartamentoService departamentoService) {
        this.conversorService = conversorService;
        this.departamentoService = departamentoService;
    }



    public AdministradorViewDTO incluirAdministrador(AdministradorCreateDTO administradorCreateDTO) {
        return conversorService.entity2Dto(adicionarAdministrador(administradorCreateDTO));
    }

    public List<AdministradorViewDTO> buscarTodosAdministradores() {
        return conversorService.entityList2DtoList(pesquisarTodosAdministradores());
    }

    public AdministradorViewDTO buscarAdministrador(Long id) {
        return conversorService.entity2Dto(pesquisarAdministrador(id));
    }

    public AdministradorViewDTO atualizarAdministrador(AdministradorUpdateDTO administradorUpdateDTO) {
        return conversorService.entity2Dto(editarAdministrador(administradorUpdateDTO));
    }


    public Administrador adicionarAdministrador(AdministradorCreateDTO dto) {
        Administrador novoAdministrador = new Administrador();

        novoAdministrador.setNome(dto.getNome().toUpperCase());
        novoAdministrador.setEmail(dto.getEmail());
        novoAdministrador.setCpf(dto.getCpf());
        novoAdministrador.setSenha(dto.getSenha());
        novoAdministrador.setDepartamento(departamentoService.getOne(dto.getDepartamento()));
        novoAdministrador.setPerfil(dto.getPerfil().toUpperCase());

        repository.save(novoAdministrador);
        return novoAdministrador;
    }

    public List<Administrador> pesquisarTodosAdministradores() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "idColaborador"));
    }

    public Administrador pesquisarAdministrador(Long id) {
        return getOne(id);
    }

    public Administrador editarAdministrador(AdministradorUpdateDTO updateDTO) {

        Administrador atualizaAdministrador = getOne(updateDTO.getIdColaborador());

        if (updateDTO.getNome() != null) {
            atualizaAdministrador.setNome(updateDTO.getNome().toUpperCase());
        }
        if (updateDTO.getEmail() != null) {
            atualizaAdministrador.setEmail(updateDTO.getEmail());
        }
        if (updateDTO.getCpf() != null) {
            atualizaAdministrador.setCpf(updateDTO.getCpf());
        }
        if (updateDTO.getSenha() != null) {
            atualizaAdministrador.setSenha(updateDTO.getSenha());
        }
        if (updateDTO.getDepartamento() != null) {
            atualizaAdministrador.setDepartamento(departamentoService.getOne(updateDTO.getDepartamento()));
        }
        if (updateDTO.getPerfil() != null) {
            atualizaAdministrador.setPerfil(updateDTO.getPerfil().toUpperCase());
        }

        return repository.save(atualizaAdministrador);
    }

    public void excluirAdministrador(Administrador administrador) {
        repository.deleteById(administrador.getIdColaborador());
    }


}
