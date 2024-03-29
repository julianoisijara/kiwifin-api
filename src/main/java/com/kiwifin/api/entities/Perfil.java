package com.kiwifin.api.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="PERFIL", schema = "KIWIFIN")
public class Perfil implements GrantedAuthority, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="ID_PERFIL")
    private Long idPerfil;
    @Column(name="DESCRICAO")
    private String descricao;

    public Perfil() {
    }

    @Override
    public String getAuthority() {
        return this.descricao;
    }

    public Long getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Long idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Perfil perfil = (Perfil) o;
        return idPerfil.equals(perfil.idPerfil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPerfil);
    }
}
