package com.kiwifin.api.DTO.view;

import java.io.Serializable;

public class MotivoViewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idMotivo;
    private String nome;
    private Boolean status;
    private Long prazo;
    private DepartamentoViewDTO departamento;

    public MotivoViewDTO() {
    }

    public Long getIdMotivo() {
        return idMotivo;
    }

    public void setIdMotivo(Long idMotivo) {
        this.idMotivo = idMotivo;
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

    public Long getPrazo() {
        return prazo;
    }

    public void setPrazo(Long prazo) {
        this.prazo = prazo;
    }

    public DepartamentoViewDTO getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentoViewDTO departamento) {
        this.departamento = departamento;
    }
}
