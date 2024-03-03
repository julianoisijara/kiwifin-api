package com.kiwifin.api.service;


import com.kiwifin.api.DTO.create.SupervisorQualidadeCreateDTO;
import com.kiwifin.api.DTO.update.SupervisorQualidadeUpdateDTO;
import com.kiwifin.api.DTO.view.SupervisorQualidadeViewDTO;
import com.kiwifin.api.entities.SupervisorQualidade;
import com.kiwifin.api.repositories.SupervisorQualidadeRepository;
import com.kiwifin.api.service.conversor.SupervisorQualidadeConversorService;
import com.kiwifin.api.service.data.GenericDataService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupervisorQualidadeService extends GenericDataService<SupervisorQualidade, Long, SupervisorQualidadeRepository> {

    private SupervisorQualidadeConversorService conversorService;
    private DepartamentoService departamentoService;

    public SupervisorQualidadeService(SupervisorQualidadeConversorService conversorService, DepartamentoService departamentoService) {
        this.conversorService = conversorService;
        this.departamentoService = departamentoService;
    }



    public SupervisorQualidadeViewDTO incluirSupervisorQualidade(SupervisorQualidadeCreateDTO supervisorQualidadeCreateDTO) {
        return conversorService.entity2Dto(adicionarSupervisorQualidade(supervisorQualidadeCreateDTO));
    }

    public List<SupervisorQualidadeViewDTO> buscarTodosSupervisores() {
        return conversorService.entityList2DtoList(pesquisarTodosSupervisores());
    }

    public SupervisorQualidadeViewDTO buscarSupervisorQualidade(Long id) {
        return conversorService.entity2Dto(pesquisarSupervisorQualidade(id));
    }

    public SupervisorQualidadeViewDTO atualizarSupervisorQualidade(SupervisorQualidadeUpdateDTO supervisorQualidadeUpdateDTO) {
        return conversorService.entity2Dto(editarSupervisorQualidade(supervisorQualidadeUpdateDTO));
    }



    public SupervisorQualidade adicionarSupervisorQualidade(SupervisorQualidadeCreateDTO dto) {

        SupervisorQualidade novoSupervisor = new SupervisorQualidade();

        novoSupervisor.setNome(dto.getNome().toUpperCase());
        novoSupervisor.setEmail(dto.getEmail());
        novoSupervisor.setCpf(dto.getCpf());
        novoSupervisor.setSenha(dto.getSenha());
        novoSupervisor.setDepartamento(departamentoService.getOne(dto.getDepartamento()));
        novoSupervisor.setPerfil(dto.getPerfil().toUpperCase());

        repository.save(novoSupervisor);
        return novoSupervisor;
    }

    public List<SupervisorQualidade> pesquisarTodosSupervisores() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "idColaborador"));
    }

    public SupervisorQualidade pesquisarSupervisorQualidade(Long id) {
        return getOne(id);
    }

    public SupervisorQualidade pesquisarSupervisorQualidadeCpf(String cpf) {
        return repository.findByCpfEquals(cpf);
    }

    public SupervisorQualidade editarSupervisorQualidade(SupervisorQualidadeUpdateDTO updateDTO) {

        SupervisorQualidade atualizaSupervisor = getOne(updateDTO.getIdColaborador());

        if (updateDTO.getNome() != null) {
            atualizaSupervisor.setNome(updateDTO.getNome().toUpperCase());
        }
        if (updateDTO.getEmail() != null) {
            atualizaSupervisor.setEmail(updateDTO.getEmail());
        }
        if (updateDTO.getCpf() != null) {
            atualizaSupervisor.setCpf(updateDTO.getCpf());
        }
        if (updateDTO.getSenha() != null) {
            atualizaSupervisor.setSenha(updateDTO.getSenha());
        }
        if (updateDTO.getDepartamento() != null) {
            atualizaSupervisor.setDepartamento(departamentoService.getOne(updateDTO.getDepartamento()));
        }
        if (updateDTO.getPerfil() != null) {
            atualizaSupervisor.setPerfil(updateDTO.getPerfil().toUpperCase());
        }

        return repository.save(atualizaSupervisor);
    }

    public void excluirSupervisorQualidade(SupervisorQualidade supervisorQualidade) {
        repository.deleteById(supervisorQualidade.getIdColaborador());
    }
}
