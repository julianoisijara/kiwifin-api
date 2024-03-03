package com.kiwifin.api.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name="PERFIL", schema = "KIWIFIN")
public class Perfil implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idPerfil;
    private String descricao;

    public Perfil() {
    }


    @Id
    @Column(name="ID_PERFIL")
    public Long getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Long idPerfil) {
        this.idPerfil = idPerfil;
    }

    @Column(name="DESCRICAO")
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
