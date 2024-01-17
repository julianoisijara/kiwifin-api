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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService extends GenericDataService<Departamento, Long, DepartamentoRepository> {

    DepartamentoConversorService conversorService;
    private static final Logger logger = LogManager.getLogger(DepartamentoService.class);

    public DepartamentoService(DepartamentoConversorService conversorService) {
        this.conversorService = conversorService;
    }

    public DepartamentoViewDTO incluirDepartamento(DepartamentoCreateDTO departamentoCreateDTO) {
        return conversorService.entity2Dto(adicionarDepartamento(departamentoCreateDTO));
    }

    public DepartamentoViewDTO atualizarDepartamento(DepartamentoUpdateDTO departamentoUpdateDTO) {
        return conversorService.entity2Dto(editarDepartamento(departamentoUpdateDTO));
    }

    public List<DepartamentoViewDTO> buscarDepartamentos(Long idDepartamento, String nome, String status) {
        return conversorService.entityList2DtoList(pesquisarDepartamento(idDepartamento, nome, status));
    }

    public List<DepartamentoViewDTO> buscarTodosDepartamentos() {
        return conversorService.entityList2DtoList(pesquisarTodosDepartamentos());
    }


    public List<Departamento> pesquisarTodosDepartamentos() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            logger.error("Erro ao buscar departamentos " + e.getMessage());
            return null;
        }
    }

    public List<Departamento> pesquisarDepartamento(Long idDepartamento, String nome, String status) {
        List<Departamento> departamentoList = new ArrayList<>();

        try {
            if (idDepartamento != null) {
                Optional<Departamento> departamento = repository.findById(idDepartamento);

                if (departamento.isPresent()) {
                    departamentoList.add(departamento.get());
                }
            }
            if (nome != null) {
                departamentoList.addAll(repository.findByNomeContains(nome.toUpperCase()));
            }
            if (status != null) {
                departamentoList.addAll(repository.findByStatusDepartamentoEquals(status.toUpperCase()));
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
            novoDepartamento.setStatusDepartamento(departamentoDTO.getStatus().toUpperCase());

            repository.save(novoDepartamento);
            return novoDepartamento;

        } catch (Exception e) {
            logger.error("Erro ao criar departamento " + e.getMessage());
            return null;
        }
    }


    public Departamento editarDepartamento(DepartamentoUpdateDTO departamentoUpdateDTO) {
        try {
            Optional<Departamento> atualizaDepartamento = findById(departamentoUpdateDTO.getIdDepartamento());

            if (atualizaDepartamento.isPresent()) {

                if (departamentoUpdateDTO.getNome() != null) {
                    atualizaDepartamento.get().setNome(departamentoUpdateDTO.getNome().toUpperCase());
                }
                if (departamentoUpdateDTO.getStatus() != null) {
                    atualizaDepartamento.get().setStatusDepartamento(departamentoUpdateDTO.getStatus().toUpperCase());
                }
                repository.save(atualizaDepartamento.get());
            }

            return atualizaDepartamento.get();

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


}