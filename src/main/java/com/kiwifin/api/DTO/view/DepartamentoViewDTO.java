package com.kiwifin.api.DTO.view;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;

@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "serialVersionUID")
public class DepartamentoViewDTO implements Serializable {

    private Long idDepartamento;
    private String nome;
    private Boolean status;

    public DepartamentoViewDTO() {
    }

    public Long getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Long idDepartamento) {
        this.idDepartamento = idDepartamento;
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
