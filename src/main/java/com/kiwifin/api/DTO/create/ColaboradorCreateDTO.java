package com.kiwifin.api.DTO.create;

import java.io.Serializable;

public class ColaboradorCreateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private String email;
    private String cpf;
    private String senha;
    private Long departamento;
    private Long perfil;

    public ColaboradorCreateDTO(String nome, String email, String cpf, String senha, Long departamento, Long perfil) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
        this.departamento = departamento;
        this.perfil = perfil;
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

    public Long getPerfil() {
        return perfil;
    }

    public void setPerfil(Long perfil) {
        this.perfil = perfil;
    }
}
