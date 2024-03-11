package com.kiwifin.api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="COLABORADOR_PERFIL", schema = "KIWIFIN")
public class ColaboradorPerfil implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="ID_COLABORADOR_PERFIL")
    private Long idColaboradorPerfil;
    @Column(name="ID_PERFIL")
    private Long idPerfil;

    public ColaboradorPerfil() {
    }

    public Long getIdColaboradorPerfil() {
        return idColaboradorPerfil;
    }

    public void setIdColaboradorPerfil(Long idColaboradorPerfil) {
        this.idColaboradorPerfil = idColaboradorPerfil;
    }

    public Long getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Long idPerfil) {
        this.idPerfil = idPerfil;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColaboradorPerfil that = (ColaboradorPerfil) o;
        return idColaboradorPerfil.equals(that.idColaboradorPerfil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idColaboradorPerfil);
    }
}
