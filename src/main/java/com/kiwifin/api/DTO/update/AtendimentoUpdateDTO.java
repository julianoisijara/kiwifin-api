package com.kiwifin.api.DTO.update;

import com.kiwifin.api.DTO.base.BaseDTO;

import java.io.Serializable;
import java.util.Date;


public class AtendimentoUpdateDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idAtendimento;
    private String protocolo;
    private String statusAndamento;
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
    
    public AtendimentoUpdateDTO () {        
    }

    public Long getIdAtendimento() {
        return idAtendimento;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getStatusAndamento() {
        return statusAndamento;
    }

    public void setStatusAndamento(String statusAndamento) {
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
