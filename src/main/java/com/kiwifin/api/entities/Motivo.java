package com.kiwifin.api.entities;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="MOTIVO", schema = "KIWIFIN")
public class Motivo implements Serializable {

    private static final long serialVersionUID = -6738348962179643515L;

    private Long idMotivo;
    private String nome;
    private Boolean status;
    private Long prazo;
    private Departamento departamento;

    public Motivo() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_MOTIVO")
    public Long getIdMotivo() {
        return idMotivo;
    }

    public void setIdMotivo(Long idMotivo) {
        this.idMotivo = idMotivo;
    }

    @Column(name="NOME", nullable = false)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name="STATUS", nullable = false)
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Column(name="PRAZO", nullable = false)
    public Long getPrazo() {
        return prazo;
    }

    public void setPrazo(Long prazo) {
        this.prazo = prazo;
    }

    @OneToOne
    @JoinColumn(name = "ID_DEPARTAMENTO", referencedColumnName = "ID_DEPARTAMENTO")
    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Motivo motivo = (Motivo) o;
        return idMotivo.equals(motivo.idMotivo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMotivo);
    }
}
