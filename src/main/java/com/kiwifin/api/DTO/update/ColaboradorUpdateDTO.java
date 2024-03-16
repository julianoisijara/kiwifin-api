package com.kiwifin.api.DTO.update;

import com.kiwifin.api.DTO.view.PerfilViewDTO;
import com.kiwifin.api.entities.Departamento;

import java.io.Serializable;
import java.util.List;

public class ColaboradorUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idColaborador;
    private String nome;
    private String email;
    private String cpf;
    private String senha;
    private Long departamento;
    private List<Long> perfil;

    public ColaboradorUpdateDTO() {

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

    public List<Long> getPerfil() {
        return perfil;
    }

    public void setPerfil(List<Long> perfil) {
        this.perfil = perfil;
    }
}
