package com.kiwifin.api.DTO.create;

import com.kiwifin.api.dominio.AtendimentoEnum;

import java.io.Serializable;
import java.util.Date;

public class AtendimentoCreateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String protocolo;
    private AtendimentoEnum statusAndamento;
    private Long statusPrazo;
    private Date dataProtocolo;
    private String assunto;
    private String detalhamentoSolicitacao;
    private String detalhamentoEmpresa;
    private String questionamentoEmpresa;
    private String respostaQuestionamento;
    private Long motivo;
    private Long cliente;
    private Long atendente;
    private Long supervisorQualidade;

    public AtendimentoCreateDTO(String assunto, String detalhamentoSolicitacao, Long motivo, Long cliente) {
        this.assunto = assunto;
        this.detalhamentoSolicitacao = detalhamentoSolicitacao;
        this.motivo = motivo;
        this.cliente = cliente;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public AtendimentoEnum getStatusAndamento() {
        return statusAndamento;
    }

    public void setStatusAndamento(AtendimentoEnum statusAndamento) {
        this.statusAndamento = statusAndamento;
    }

    public Long getStatusPrazo() {
        return statusPrazo;
    }

    public void setStatusPrazo(Long statusPrazo) {
        this.statusPrazo = statusPrazo;
    }

    public Date getDataProtocolo() {
        return dataProtocolo;
    }

    public void setDataProtocolo(Date dataProtocolo) {
        this.dataProtocolo = dataProtocolo;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getDetalhamentoSolicitacao() {
        return detalhamentoSolicitacao;
    }

    public void setDetalhamentoSolicitacao(String detalhamentoSolicitacao) {
        this.detalhamentoSolicitacao = detalhamentoSolicitacao;
    }

    public String getDetalhamentoEmpresa() {
        return detalhamentoEmpresa;
    }

    public void setDetalhamentoEmpresa(String detalhamentoEmpresa) {
        this.detalhamentoEmpresa = detalhamentoEmpresa;
    }

    public String getQuestionamentoEmpresa() {
        return questionamentoEmpresa;
    }

    public void setQuestionamentoEmpresa(String questionamentoEmpresa) {
        this.questionamentoEmpresa = questionamentoEmpresa;
    }

    public String getRespostaQuestionamento() {
        return respostaQuestionamento;
    }

    public void setRespostaQuestionamento(String respostaQuestionamento) {
        this.respostaQuestionamento = respostaQuestionamento;
    }

    public Long getMotivo() {
        return motivo;
    }

    public void setMotivo(Long motivo) {
        this.motivo = motivo;
    }

    public Long getCliente() {
        return cliente;
    }

    public void setCliente(Long cliente) {
        this.cliente = cliente;
    }

    public Long getAtendente() {
        return atendente;
    }

    public void setAtendente(Long atendente) {
        this.atendente = atendente;
    }

    public Long getSupervisorQualidade() {
        return supervisorQualidade;
    }

    public void setSupervisorQualidade(Long supervisorQualidade) {
        this.supervisorQualidade = supervisorQualidade;
    }
}
