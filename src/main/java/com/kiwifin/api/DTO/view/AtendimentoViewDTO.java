package com.kiwifin.api.DTO.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class AtendimentoViewDTO implements Serializable {

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
    private MotivoViewDTO motivo;
    private ClienteViewDTO cliente;
    private ColaboradorViewDTO atendente;
    private ColaboradorViewDTO supervisorQualidade;
    private List<AtendimentoHistoricoViewDTO> historico;
    
    public AtendimentoViewDTO () {}

    public Long getIdAtendimento() {
        return idAtendimento;
    }

    public void setIdAtendimento(Long idAtendimento) {
        this.idAtendimento = idAtendimento;
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

    public MotivoViewDTO getMotivo() {
        return motivo;
    }

    public void setMotivo(MotivoViewDTO motivo) {
        this.motivo = motivo;
    }

    public ClienteViewDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteViewDTO cliente) {
        this.cliente = cliente;
    }

    public ColaboradorViewDTO getAtendente() {
        return atendente;
    }

    public void setAtendente(ColaboradorViewDTO atendente) {
        this.atendente = atendente;
    }

    public ColaboradorViewDTO getSupervisorQualidade() {
        return supervisorQualidade;
    }

    public void setSupervisorQualidade(ColaboradorViewDTO supervisorQualidade) {
        this.supervisorQualidade = supervisorQualidade;
    }

    public List<AtendimentoHistoricoViewDTO> getHistorico() {
        return historico;
    }

    public void setHistorico(List<AtendimentoHistoricoViewDTO> historico) {
        this.historico = historico;
    }
}
