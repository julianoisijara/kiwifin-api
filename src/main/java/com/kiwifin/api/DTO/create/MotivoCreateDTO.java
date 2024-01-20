package com.kiwifin.api.DTO.create;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.kiwifin.api.entities.Departamento;

import java.io.Serializable;

@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "serialVersionUID")
public class MotivoCreateDTO implements Serializable {

    private String nome;
    private Boolean status;
    private Long prazo;
    private Departamento departamento;

    public MotivoCreateDTO(String nome, Boolean status, Long prazo, Departamento departamento) {
        this.nome = nome;
        this.status = status;
        this.prazo = prazo;
        this.departamento = departamento;
    }

    public String getNome() {
        return nome.toUpperCase();
    }

    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getPrazo() {
        return prazo;
    }

    public void setPrazo(Long prazo) {
        this.prazo = prazo;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}
