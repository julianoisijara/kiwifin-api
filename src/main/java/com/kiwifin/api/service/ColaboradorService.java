package com.kiwifin.api.service;

import com.kiwifin.api.DTO.create.ColaboradorCreateDTO;
import com.kiwifin.api.DTO.update.ColaboradorUpdateDTO;
import com.kiwifin.api.DTO.view.ColaboradorViewDTO;
import com.kiwifin.api.entities.Colaborador;
import com.kiwifin.api.repositories.ColaboradorRepository;
import com.kiwifin.api.service.conversor.ColaboradorConversorService;
import com.kiwifin.api.service.data.GenericDataService;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorService extends GenericDataService<Colaborador, Long, ColaboradorRepository> implements UserDetailsService {

    private ColaboradorConversorService conversorService;
    private DepartamentoService departamentoService;
    private PerfilService perfilService;

    public ColaboradorService(ColaboradorConversorService conversorService, DepartamentoService departamentoService, PerfilService perfilService) {
        this.conversorService = conversorService;
        this.departamentoService = departamentoService;
        this.perfilService = perfilService;
    }

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        return repository.findByCpfEquals(cpf);
    }

    public ColaboradorViewDTO incluirColaborador(ColaboradorCreateDTO ColaboradorCreateDTO) {
        return conversorService.entity2Dto(adicionarColaborador(ColaboradorCreateDTO));
    }

    public List<ColaboradorViewDTO> buscarTodosColaboradores() {
        return conversorService.entityList2DtoList(pesquisarTodosColaboradores());
    }

    public ColaboradorViewDTO buscarColaborador(Long id) {
        return conversorService.entity2Dto(pesquisarColaborador(id));
    }

    public ColaboradorViewDTO atualizarColaborador(ColaboradorUpdateDTO ColaboradorUpdateDTO) {
        return conversorService.entity2Dto(editarColaborador(ColaboradorUpdateDTO));
    }


    public Colaborador adicionarColaborador(ColaboradorCreateDTO dto) {
        Colaborador novoColaborador = new Colaborador();

        novoColaborador.setNome(dto.getNome().toUpperCase());
        novoColaborador.setEmail(dto.getEmail());
        novoColaborador.setCpf(dto.getCpf());
        novoColaborador.setSenha(new BCryptPasswordEncoder().encode(dto.getSenha()));
        novoColaborador.setDepartamento(departamentoService.getOne(dto.getDepartamento()));
        novoColaborador.setListaPerfis(perfilService.getListaPerfil(dto.getPerfil()));
        repository.save(novoColaborador);

        return novoColaborador;
    }

    public List<Colaborador> pesquisarTodosColaboradores() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "idColaborador"));
    }

    public Colaborador pesquisarColaborador(Long id) {
        return getOne(id);
    }

    public Colaborador pesquisaColaboradorCpf(String cpf) {
        return repository.findByCpfEquals(cpf);
    }

    public Colaborador editarColaborador(ColaboradorUpdateDTO updateDTO) {

        Colaborador atualizaColaborador = getOne(updateDTO.getIdColaborador());

        if (updateDTO.getNome() != null) {
            atualizaColaborador.setNome(updateDTO.getNome().toUpperCase());
        }
        if (updateDTO.getEmail() != null) {
            atualizaColaborador.setEmail(updateDTO.getEmail());
        }
        if (updateDTO.getCpf() != null) {
            atualizaColaborador.setCpf(updateDTO.getCpf());
        }
        if (updateDTO.getSenha() != null) {
            atualizaColaborador.setSenha(new BCryptPasswordEncoder().encode(updateDTO.getSenha()));
        }
        if (updateDTO.getDepartamento() != null) {
            atualizaColaborador.setDepartamento(departamentoService.getOne(updateDTO.getDepartamento()));
        }
        if (updateDTO.getPerfil() != null) {
            atualizaColaborador.setListaPerfis(perfilService.getListaPerfil(updateDTO.getPerfil()));
        }

        return repository.save(atualizaColaborador);
    }

    public void excluirColaborador(Colaborador Colaborador) {
        repository.deleteById(Colaborador.getIdColaborador());
    }

    public void criarNovoAdm() {
        Optional<Colaborador> colaborador = findById(1L);
        if (!colaborador.isPresent()) {
            Colaborador colaboradorADM = new Colaborador();

            colaboradorADM.setNome("ADM");
            colaboradorADM.setEmail("adm@kiwifin.br");
            colaboradorADM.setCpf("11111111111");
            colaboradorADM.setSenha(new BCryptPasswordEncoder().encode("adm123"));
            colaboradorADM.setDepartamento(departamentoService.getOne(1L));
            colaboradorADM.setListaPerfis(perfilService.getListaPerfil(Collections.singletonList(1L)));

            save(colaboradorADM);
        }
    }


}
