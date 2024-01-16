package com.kiwifin.api.entities;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="DEPARTAMENTO", schema = "KIWIFIN")
public class Departamento {

    private Long idDepartamento;
    private String nome;
    private String statusDepartamento;

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
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name="STATUS_DEPARTAMENTO", nullable = false)
    public String getStatusDepartamento() {
        return statusDepartamento;
    }

    public void setStatusDepartamento(String statusDepartamento) {
        this.statusDepartamento = statusDepartamento;
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
