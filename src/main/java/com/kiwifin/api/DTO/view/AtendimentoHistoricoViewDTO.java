package com.kiwifin.api.DTO.view;

import java.io.Serializable;
import java.util.Date;

public class AtendimentoHistoricoViewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private  Long idAtendimentoHistorico;
    private AtendimentoViewDTO atendimento;
    private AtendenteViewDTO atendente;
    private Date dataAlteracao;
    private String textoObservacao;

    public AtendimentoHistoricoViewDTO() {}

    public Long getIdAtendimentoHistorico() {
        return idAtendimentoHistorico;
    }

    public void setIdAtendimentoHistorico(Long idAtendimentoHistorico) {
        this.idAtendimentoHistorico = idAtendimentoHistorico;
    }

    public AtendimentoViewDTO getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(AtendimentoViewDTO atendimento) {
        this.atendimento = atendimento;
    }

    public AtendenteViewDTO getAtendente() {
        return atendente;
    }

    public void setAtendente(AtendenteViewDTO atendente) {
        this.atendente = atendente;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public String getTextoObservacao() {
        return textoObservacao;
    }

    public void setTextoObservacao(String textoObservacao) {
        this.textoObservacao = textoObservacao;
    }
}
