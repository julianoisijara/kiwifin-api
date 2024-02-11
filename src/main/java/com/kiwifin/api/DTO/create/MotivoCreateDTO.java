package com.kiwifin.api.DTO.create;

import java.io.Serializable;

public class MotivoCreateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private Boolean status;
    private Long prazo;
    private Long departamento;

    public MotivoCreateDTO(String nome, Boolean status, Long prazo, Long departamento) {
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

    public Long getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Long departamento) {
        this.departamento = departamento;
    }
}
