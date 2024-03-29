package com.kiwifin.api.entities;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="DEPARTAMENTO", schema = "KIWIFIN")
public class Departamento implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idDepartamento;
    private String nome;
    private Boolean status;
    private List<Motivo> motivos;

    public Departamento() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_DEPARTAMENTO")
    public Long getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Long idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    @Column(name="NOME", nullable = false)
    public String getNome() {
        return nome.toUpperCase();
    }

    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }

    @Column(name="STATUS", nullable = false)
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @OneToMany(mappedBy = "departamento")
    public List<Motivo> getMotivos() {
        return motivos;
    }

    public void setMotivos(List<Motivo> motivos) {
        this.motivos = motivos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departamento that = (Departamento) o;
        return idDepartamento.equals(that.idDepartamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDepartamento);
    }
}
