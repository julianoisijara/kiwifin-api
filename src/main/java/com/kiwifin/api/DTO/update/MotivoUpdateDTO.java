package com.kiwifin.api.DTO.update;

import com.kiwifin.api.entities.Departamento;

import java.io.Serializable;

public class MotivoUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idMotivo;
    private String nome;
    private Boolean status;
    private Long prazo;
    private Long departamento;

    public MotivoUpdateDTO() {
    }

    public Long getIdMotivo() {
        return idMotivo;
    }

    public String getNome() {
        return nome;
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
