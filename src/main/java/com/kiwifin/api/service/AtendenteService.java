package com.kiwifin.api.service;

import com.kiwifin.api.DTO.create.AtendenteCreateDTO;
import com.kiwifin.api.DTO.update.AtendenteUpdateDTO;
import com.kiwifin.api.DTO.view.AtendenteViewDTO;
import com.kiwifin.api.entities.Atendente;
import com.kiwifin.api.repositories.AtendenteRepository;
import com.kiwifin.api.service.conversor.AtendenteConversorService;
import com.kiwifin.api.service.data.GenericDataService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtendenteService extends GenericDataService<Atendente, Long, AtendenteRepository> {

    private static final Logger logger = LogManager.getLogger(AtendenteService.class);

    AtendenteConversorService conversorService;
    DepartamentoService departamentoService;

    public AtendenteService(AtendenteConversorService conversorService, DepartamentoService departamentoService) {
        this.conversorService = conversorService;
        this.departamentoService = departamentoService;
    }

    public AtendenteViewDTO incluirAtendente(AtendenteCreateDTO atendenteCreateDTO) {
        return conversorService.entity2Dto(adicionarAtendente(atendenteCreateDTO));
    }

    public List<AtendenteViewDTO> buscarTodosAtendentes() {
        return conversorService.entityList2DtoList(pesquisarTodosAtendentes());
    }

    public AtendenteViewDTO buscarAtendente(Long id) {
        return conversorService.entity2Dto(pesquisarAtendente(id));
    }

    public AtendenteViewDTO atualizarAtendente(AtendenteUpdateDTO atendenteUpdateDTO) {
        return conversorService.entity2Dto(editarAtendente(atendenteUpdateDTO));
    }



    public Atendente adicionarAtendente(AtendenteCreateDTO atendenteCreateDTO) {
        Atendente novoAtendente = new Atendente();

        novoAtendente.setNome(atendenteCreateDTO.getNome().toUpperCase());
        novoAtendente.setEmail(atendenteCreateDTO.getEmail());
        novoAtendente.setCpf(atendenteCreateDTO.getCpf());
        novoAtendente.setSenha(atendenteCreateDTO.getSenha());
        novoAtendente.setDepartamento(departamentoService.getOne(atendenteCreateDTO.getDepartamento()));
        novoAtendente.setPerfil(atendenteCreateDTO.getPerfil().toUpperCase());

        repository.save(novoAtendente);

        return novoAtendente;
    }


    public List<Atendente> pesquisarTodosAtendentes() {
        try {
            return repository.findAll(Sort.by(Sort.Direction.ASC, "idColaborador"));
        } catch (Exception e) {
            return null;
        }
    }

    public Atendente pesquisarAtendente(Long id) {

        Atendente atendente;

        try {
            atendente = getOne(id);

        } catch (Exception e) {
            logger.error("Erro ao buscar atendente id: " + id);
            return null;
        }

        return atendente;
    }

    public Atendente editarAtendente(AtendenteUpdateDTO updateDTO) {

        try {
            Atendente atualizaAtendente = getOne(updateDTO.getIdColaborador());

            if (updateDTO.getNome() != null) {
                atualizaAtendente.setNome(updateDTO.getNome().toUpperCase());
            }
            if (updateDTO.getEmail() != null) {
                atualizaAtendente.setEmail(updateDTO.getEmail());
            }
            if (updateDTO.getCpf() != null) {
                atualizaAtendente.setCpf(updateDTO.getCpf());
            }
            if (updateDTO.getSenha() != null) {
                atualizaAtendente.setSenha(updateDTO.getSenha());
            }
            if (updateDTO.getDepartamento() != null) {
                atualizaAtendente.setDepartamento(departamentoService.getOne(updateDTO.getDepartamento()));
            }
            if (updateDTO.getPerfil() != null) {
                atualizaAtendente.setPerfil(updateDTO.getPerfil().toUpperCase());
            }

            return repository.save(atualizaAtendente);


        } catch (Exception e) {
            logger.error("Erro ao atualizar atendente id: " + updateDTO.getIdColaborador());
            return null;
        }
    }


    public void excluirAtendente(Atendente atendente) {
        try {
            repository.deleteById(atendente.getIdColaborador());
        } catch (Exception e) {
            logger.error("Erro ao excluir o atendente: " + atendente.getNome());
        }
    }

}
