package com.kiwifin.api.DTO.view;

import java.io.Serializable;
import java.util.List;

public class DepartamentoViewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idDepartamento;
    private String nome;
    private Boolean status;
    private List<MotivoViewDTO> motivoViewDTO;

    public DepartamentoViewDTO() {
    }

    public Long getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Long idDepartamento) {
        this.idDepartamento = idDepartamento;
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

    public List<MotivoViewDTO> getMotivoViewDTO() {
        return motivoViewDTO;
    }

    public void setMotivoViewDTO(List<MotivoViewDTO> motivoViewDTO) {
        this.motivoViewDTO = motivoViewDTO;
    }
}
