package com.kiwifin.api.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="ATENDIMENTO", schema = "KIWIFIN")
public class Atendimento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_ATENDIMENTO")
    private Long idAtendimento;
    @Column(name="PROTOCOLO", nullable = false)
    private String protocolo;
    @Column(name="STATUS_ANDAMENTO", nullable = false)
    private String statusAndamento;
    @Column(name="STATUS_PRAZO", nullable = false)
    private Long statusPrazo;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_PROTOCOLO", length = 7, nullable = false)
    private Date dataProtocolo;
    @Column(name="ASSUNTO", nullable = false)
    private String assunto;
    @Column(name="DETALHAMENTO_SOLICITACAO", nullable = false)
    private String detalhamentoSolicitacao;
    @Column(name="DETALHAMENTO_EMPRESA", nullable = false)
    private String detalhamentoEmpresa;
    @Column(name="QUESTIONAMENTO_EMPRESA", nullable = false)
    private String questionamentoEmpresa;
    @Column(name="RESPOSTA_QUESTIONAMENTO", nullable = false)
    private String respostaQuestionamento;
    @OneToOne
    @JoinColumn(name = "ID_MOTIVO", referencedColumnName = "ID_MOTIVO")
    private Motivo motivo;
    @OneToOne
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE")
    private Cliente cliente;
    @OneToOne
    @JoinColumn(name = "ID_ATENDENTE", referencedColumnName = "ID_COLABORADOR")
    private Colaborador atendente;
    @OneToOne
    @JoinColumn(name = "ID_SUPERVISOR_QUALIDADE", referencedColumnName = "ID_COLABORADOR")
    private Colaborador supervisorQualidade;
    @OneToMany(mappedBy = "atendimento", cascade = CascadeType.ALL)
    private List<AtendimentoHistorico> historico;

    public Atendimento () {
    }


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

    public Motivo getMotivo() {
        return motivo;
    }

    public void setMotivo(Motivo motivo) {
        this.motivo = motivo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public Colaborador getAtendente() {
        return atendente;
    }

    public void setAtendente(Colaborador atendente) {
        this.atendente = atendente;
    }


    public Colaborador getSupervisorQualidade() {
        return supervisorQualidade;
    }

    public void setSupervisorQualidade(Colaborador supervisorQualidade) {
        this.supervisorQualidade = supervisorQualidade;
    }


    public List<AtendimentoHistorico> getHistorico() {
        return historico;
    }

    public void setHistorico(List<AtendimentoHistorico> historico) {
        this.historico = historico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atendimento that = (Atendimento) o;
        return idAtendimento.equals(that.idAtendimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAtendimento);
    }
}
