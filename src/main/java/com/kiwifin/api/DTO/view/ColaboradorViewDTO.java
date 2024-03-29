package com.kiwifin.api.DTO.view;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

public class ColaboradorViewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idColaborador;
    private String nome;
    private String email;
    private String cpf;
    @JsonIgnore
    private String senha;
    private DepartamentoViewDTO departamento;
    private List<PerfilViewDTO> perfil;

    public ColaboradorViewDTO() {
    }

    public Long getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Long idColaborador) {
        this.idColaborador = idColaborador;
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

    public DepartamentoViewDTO getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentoViewDTO departamento) {
        this.departamento = departamento;
    }

    public List<PerfilViewDTO> getPerfil() {
        return perfil;
    }

    public void setPerfil(List<PerfilViewDTO> perfil) {
        this.perfil = perfil;
    }
}
