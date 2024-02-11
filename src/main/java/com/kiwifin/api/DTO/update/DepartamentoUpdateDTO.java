package com.kiwifin.api.DTO.update;

import java.io.Serializable;

public class DepartamentoUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idDepartamento;
    private String nome;
    private Boolean status;

    public DepartamentoUpdateDTO() {
    }

    public Long getIdDepartamento() {
        return idDepartamento;
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
