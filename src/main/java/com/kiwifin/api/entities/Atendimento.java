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
    private Motivo motivo;
    private Cliente cliente;
    private Atendente atendente;
    private SupervisorQualidade supervisorQualidade;
    private List<AtendimentoHistorico> historico;

    public Atendimento () {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_ATENDIMENTO")
    public Long getIdAtendimento() {
        return idAtendimento;
    }

    public void setIdAtendimento(Long idAtendimento) {
        this.idAtendimento = idAtendimento;
    }

    @Column(name="PROTOCOLO", nullable = false)
    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    @Column(name="STATUS_ANDAMENTO", nullable = false)
    public String getStatusAndamento() {
        return statusAndamento;
    }

    public void setStatusAndamento(String statusAndamento) {
        this.statusAndamento = statusAndamento;
    }

    @Column(name="STATUS_PRAZO", nullable = false)
    public Long getStatusPrazo() {
        return statusPrazo;
    }

    public void setStatusPrazo(Long statusPrazo) {
        this.statusPrazo = statusPrazo;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_PROTOCOLO", length = 7, nullable = false)
    public Date getDataProtocolo() {
        return dataProtocolo;
    }

    public void setDataProtocolo(Date dataProtocolo) {
        this.dataProtocolo = dataProtocolo;
    }

    @Column(name="ASSUNTO", nullable = false)
    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    @Column(name="DETALHAMENTO_SOLICITACAO", nullable = false)
    public String getDetalhamentoSolicitacao() {
        return detalhamentoSolicitacao;
    }

    public void setDetalhamentoSolicitacao(String detalhamentoSolicitacao) {
        this.detalhamentoSolicitacao = detalhamentoSolicitacao;
    }

    @Column(name="DETALHAMENTO_EMPRESA", nullable = false)
    public String getDetalhamentoEmpresa() {
        return detalhamentoEmpresa;
    }

    public void setDetalhamentoEmpresa(String detalhamentoEmpresa) {
        this.detalhamentoEmpresa = detalhamentoEmpresa;
    }

    @Column(name="QUESTIONAMENTO_EMPRESA", nullable = false)
    public String getQuestionamentoEmpresa() {
        return questionamentoEmpresa;
    }

    public void setQuestionamentoEmpresa(String questionamentoEmpresa) {
        this.questionamentoEmpresa = questionamentoEmpresa;
    }

    @Column(name="RESPOSTA_QUESTIONAMENTO", nullable = false)
    public String getRespostaQuestionamento() {
        return respostaQuestionamento;
    }

    public void setRespostaQuestionamento(String respostaQuestionamento) {
        this.respostaQuestionamento = respostaQuestionamento;
    }

    @OneToOne
    @JoinColumn(name = "ID_MOTIVO", referencedColumnName = "ID_MOTIVO")
    public Motivo getMotivo() {
        return motivo;
    }

    public void setMotivo(Motivo motivo) {
        this.motivo = motivo;
    }

    @OneToOne
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE")
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @OneToOne
    @JoinColumn(name = "ID_ATENDENTE", referencedColumnName = "ID_COLABORADOR")
    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    @OneToOne
    @JoinColumn(name = "ID_SUPERVISOR_QUALIDADE", referencedColumnName = "ID_COLABORADOR")
    public SupervisorQualidade getSupervisorQualidade() {
        return supervisorQualidade;
    }

    public void setSupervisorQualidade(SupervisorQualidade supervisorQualidade) {
        this.supervisorQualidade = supervisorQualidade;
    }

    @OneToMany(mappedBy = "atendimento", cascade = CascadeType.ALL)
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
