package com.kiwifin.api.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="ATENDIMENTO_HISTORICO", schema = "KIWIFIN")
public class AtendimentoHistorico implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_ATENDIMENTO_HISTORICO")
    private  Long idAtendimentoHistorico;
    @ManyToOne
    @JoinColumn(name="ID_ATENDIMENTO", nullable = false)
    private Atendimento atendimento;
    @OneToOne
    @JoinColumn(name = "ID_ATENDENTE", referencedColumnName = "ID_COLABORADOR")
    private Colaborador atendente;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_ALTERACAO", length = 7, nullable = false)
    private Date dataAlteracao;
    @Column(name="TEXTO_OBSEVACAO", nullable = false)
    private String textoObservacao;

    public AtendimentoHistorico() {}


    public Long getIdAtendimentoHistorico() {
        return idAtendimentoHistorico;
    }

    public void setIdAtendimentoHistorico(Long idAtendimentoHistorico) {
        this.idAtendimentoHistorico = idAtendimentoHistorico;
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public Colaborador getAtendente() {
        return atendente;
    }

    public void setAtendente(Colaborador atendente) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtendimentoHistorico that = (AtendimentoHistorico) o;
        return idAtendimentoHistorico.equals(that.idAtendimentoHistorico);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAtendimentoHistorico);
    }
}
