package com.kiwifin.api.service;

import com.kiwifin.api.DTO.create.DepartamentoCreateDTO;
import com.kiwifin.api.DTO.update.DepartamentoUpdateDTO;
import com.kiwifin.api.DTO.view.DepartamentoViewDTO;
import com.kiwifin.api.entities.Departamento;
import com.kiwifin.api.repositories.DepartamentoRepository;
import com.kiwifin.api.service.conversor.DepartamentoConversorService;
import com.kiwifin.api.service.data.GenericDataService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService extends GenericDataService<Departamento, Long, DepartamentoRepository> {

    private static final Logger logger = LogManager.getLogger(DepartamentoService.class);

    private DepartamentoConversorService conversorService;

    public DepartamentoService(DepartamentoConversorService conversorService) {
        this.conversorService = conversorService;
    }

    public DepartamentoViewDTO incluirDepartamento(DepartamentoCreateDTO departamentoCreateDTO) {
        return conversorService.entity2Dto(adicionarDepartamento(departamentoCreateDTO));
    }

    public DepartamentoViewDTO atualizarDepartamento(DepartamentoUpdateDTO departamentoUpdateDTO) {
        return conversorService.entity2Dto(editarDepartamento(departamentoUpdateDTO));
    }

    public List<DepartamentoViewDTO> buscarDepartamentos(Long idDepartamento, String nome, Boolean status) {
        return conversorService.entityList2DtoList(pesquisarDepartamento(idDepartamento, nome, status));
    }

    public List<DepartamentoViewDTO> buscarTodosDepartamentos() {
        return conversorService.entityList2DtoList(pesquisarTodosDepartamentos());
    }


    public List<Departamento> pesquisarTodosDepartamentos() {
        try {
            return repository.findAll(Sort.by(Sort.Direction.ASC, "idDepartamento"));
        } catch (Exception e) {
            logger.error("Erro ao buscar departamentos " + e.getMessage());
            return null;
        }
    }

    public List<Departamento> pesquisarDepartamento(Long idDepartamento, String nome, Boolean status) {
        List<Departamento> departamentoList = new ArrayList<>();

        try {
            if (idDepartamento != null) {
                Departamento departamento = getOne(idDepartamento);

                departamentoList.add(departamento);
            }
            if (nome != null) {
                departamentoList.addAll(repository.findByNomeContains(nome.toUpperCase()));
            }
            if (status != null) {
                departamentoList.addAll(repository.findByStatusEquals(status));
            }

        } catch (Exception e) {
            logger.error("Erro ao buscar departamentos " + e.getMessage());
            return null;
        }

        return departamentoList;
    }


    public Departamento adicionarDepartamento(DepartamentoCreateDTO departamentoDTO) {

        Departamento novoDepartamento = new Departamento();

        try {
            novoDepartamento.setNome(departamentoDTO.getNome().toUpperCase());
            novoDepartamento.setStatus(departamentoDTO.getStatus());

            repository.save(novoDepartamento);
            return novoDepartamento;

        } catch (Exception e) {
            logger.error("Erro ao criar departamento " + e.getMessage());
            return null;
        }
    }


    public Departamento editarDepartamento(DepartamentoUpdateDTO departamentoUpdateDTO) {
        try {
            Departamento atualizaDepartamento = getOne(departamentoUpdateDTO.getIdDepartamento());

            if (departamentoUpdateDTO.getNome() != null) {
                atualizaDepartamento.setNome(departamentoUpdateDTO.getNome().toUpperCase());
            }
            if (departamentoUpdateDTO.getStatus() != null) {
                atualizaDepartamento.setStatus(departamentoUpdateDTO.getStatus());
            }

            repository.save(atualizaDepartamento);


            return atualizaDepartamento;

        } catch (Exception e) {
            logger.error("Erro ao atualizar o departamento: " + departamentoUpdateDTO.getIdDepartamento() + " Erro -> " + e.getMessage());
            return null;
        }
    }

    public void excluirDepartamento(Departamento departamento) {

        try {
            repository.deleteById(departamento.getIdDepartamento());

        } catch (Exception e) {
            logger.error("Erro ao deletar departamento: " + departamento.getIdDepartamento() + "MSG: " + e.getMessage());
        }
    }

    public void criarDepartamentoAdm() {
        Optional<Departamento> departamento = findById(1L);
        if (!departamento.isPresent()) {
            Departamento depADM = new Departamento();

            depADM.setNome("ADMINISTRAÇÃO");
            depADM.setStatus(true);

            save(depADM);
        }
    }


}
