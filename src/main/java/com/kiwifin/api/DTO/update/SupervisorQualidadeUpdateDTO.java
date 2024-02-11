package com.kiwifin.api.DTO.update;

import com.kiwifin.api.entities.Departamento;

import java.io.Serializable;

public class SupervisorQualidadeUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idColaborador;
    private String nome;
    private String email;
    private String cpf;
    private String senha;
    private Long departamento;
    private String perfil;

    public SupervisorQualidadeUpdateDTO() {
    }

    public Long getIdColaborador() {
        return idColaborador;
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

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}
