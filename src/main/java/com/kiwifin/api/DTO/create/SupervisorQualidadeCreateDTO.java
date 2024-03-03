package com.kiwifin.api.DTO.create;

import com.kiwifin.api.DTO.view.PerfilViewDTO;

import java.io.Serializable;

public class SupervisorQualidadeCreateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private String email;
    private String cpf;
    private String senha;
    private Long departamento;

    public SupervisorQualidadeCreateDTO(String nome, String email, String cpf, String senha, Long departamento) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
        this.departamento = departamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Long departamento) {
        this.departamento = departamento;
    }

}
