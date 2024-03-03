package com.kiwifin.api.service;

import com.kiwifin.api.entities.Administrador;
import com.kiwifin.api.entities.Atendente;
import com.kiwifin.api.entities.Cliente;
import com.kiwifin.api.entities.SupervisorQualidade;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {

    private final PasswordEncoder encoder;
    private final ClienteService clienteService;
    private final AdministradorService administradorService;
    private final AtendenteService atendenteService;
    private final SupervisorQualidadeService supervisorQualidadeService;

    public AutenticacaoService(ClienteService clienteService, AdministradorService administradorService, AtendenteService atendenteService, SupervisorQualidadeService supervisorQualidadeService, PasswordEncoder encoder) {
        this.clienteService = clienteService;
        this.administradorService = administradorService;
        this.atendenteService = atendenteService;
        this.supervisorQualidadeService = supervisorQualidadeService;
        this.encoder = encoder;
    }

    public Boolean autenticaCliente(String cpf, String senha) {

        Cliente cliente = clienteService.pesquisarClientes(null, cpf, null).get(0);
        return encoder.matches(senha, cliente.getSenha());

    }

    public Boolean autenticaAdministrador(String cpf, String senha) {
        Administrador administrador = administradorService.pesquisaAdministradorCpf(cpf);
        return encoder.matches(senha, administrador.getSenha());
    }

    public Boolean autenticaAtendente(String cpf, String senha) {
        Atendente atendente = atendenteService.pesquisarAtendenteCpf(cpf);
        return encoder.matches(senha, atendente.getSenha());
    }

    public Boolean autenticaSupervisorQualidade(String cpf, String senha) {
        SupervisorQualidade supervisor = supervisorQualidadeService.pesquisarSupervisorQualidadeCpf(cpf);
        return encoder.matches(senha, supervisor.getSenha());
    }




}
