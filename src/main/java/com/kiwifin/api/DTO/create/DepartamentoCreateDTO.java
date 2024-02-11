package com.kiwifin.api.DTO.create;

import java.io.Serializable;

public class DepartamentoCreateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private Boolean status;

    public DepartamentoCreateDTO(String nome, Boolean status) {
        this.nome = nome;
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
