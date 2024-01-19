package com.kiwifin.api.DTO.create;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;

@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "serialVersionUID")
public class DepartamentoCreateDTO implements Serializable {

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
