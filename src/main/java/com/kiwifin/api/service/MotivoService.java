package com.kiwifin.api.service;

import com.kiwifin.api.DTO.create.MotivoCreateDTO;
import com.kiwifin.api.DTO.update.MotivoUpdateDTO;
import com.kiwifin.api.DTO.view.MotivoViewDTO;
import com.kiwifin.api.entities.Motivo;
import com.kiwifin.api.repositories.MotivoRepository;
import com.kiwifin.api.service.conversor.MotivoConversorService;
import com.kiwifin.api.service.data.GenericDataService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MotivoService extends GenericDataService<Motivo, Long, MotivoRepository> {


    MotivoConversorService conversorService;

    public MotivoService(MotivoConversorService conversorService) {
        this.conversorService = conversorService;
    }


    public MotivoViewDTO incluirMotivo(MotivoCreateDTO motivoCreateDTO) {
        return conversorService.entity2Dto(adicionarMotivo(motivoCreateDTO));
    }

    public MotivoViewDTO atualizarMotivo(MotivoUpdateDTO motivoUpdateDTO) {
        return conversorService.entity2Dto(editarMotivo(motivoUpdateDTO));
    }

    public List<MotivoViewDTO> buscarMotivos(Long id, String nome, Boolean status) {
        return conversorService.entityList2DtoList(pesquisarMotivo(id, nome, status));
    }

    public List<MotivoViewDTO> buscarTodosMotivos() {
        return conversorService.entityList2DtoList(pesquisarTodosMotivos());
    }




    public Motivo adicionarMotivo(MotivoCreateDTO motivoCreateDTO) {

        Motivo novoMotivo = new Motivo();

        novoMotivo.setNome(motivoCreateDTO.getNome());
        novoMotivo.setStatus(motivoCreateDTO.getStatus());
        novoMotivo.setPrazo(motivoCreateDTO.getPrazo());
        novoMotivo.setDepartamento(motivoCreateDTO.getDepartamento());

        repository.save(novoMotivo);
        return novoMotivo;
    }


    public List<Motivo> pesquisarTodosMotivos() {
        return repository.findAll();
    }


    public List<Motivo> pesquisarMotivo(Long id, String nome, Boolean status) {

        List<Motivo> motivoList = new ArrayList<>();

        if (id != null) {
            motivoList.add(getOne(id));
        }
        if (nome != null) {
            motivoList.addAll(repository.findByNomeContains(nome.toUpperCase()));
        }
        if (status != null) {
            motivoList.addAll(repository.findByStatusEquals(status));
        }

        return motivoList;
    }

    public Motivo editarMotivo(MotivoUpdateDTO motivoUpdateDTO) {

        Motivo atualizarMotivo = getOne(motivoUpdateDTO.getIdMotivo());

        if (motivoUpdateDTO.getNome() != null) {
            atualizarMotivo.setNome(motivoUpdateDTO.getNome());
        }
        if (motivoUpdateDTO.getStatus() != null) {
            atualizarMotivo.setStatus(motivoUpdateDTO.getStatus());
        }
        if (motivoUpdateDTO.getPrazo() != null) {
            atualizarMotivo.setPrazo(motivoUpdateDTO.getPrazo());
        }
        if (motivoUpdateDTO.getDepartamento() != null) {
            atualizarMotivo.setDepartamento(motivoUpdateDTO.getDepartamento());
        }

        repository.save(atualizarMotivo);
        return atualizarMotivo;
    }


    public void excluirMotivo(Motivo motivo) {
        repository.deleteById(motivo.getIdMotivo());
    }


}
