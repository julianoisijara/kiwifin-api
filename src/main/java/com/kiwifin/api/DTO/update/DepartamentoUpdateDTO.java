package com.kiwifin.api.DTO.update;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;

@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "serialVersionUID")
public class DepartamentoUpdateDTO implements Serializable {

    private Long idDepartamento;
    private String nome;
    private String status;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
