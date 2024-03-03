package com.kiwifin.api.DTO.view;

import java.io.Serializable;

public class PerfilViewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idPerfil;
    private String descricao;

    public PerfilViewDTO() {
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
}
