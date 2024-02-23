package com.kiwifin.api.repositories;

import com.kiwifin.api.entities.Atendente;
import com.kiwifin.api.entities.Atendimento;
import com.kiwifin.api.entities.AtendimentoHistorico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtendimentoHistoricoRepository extends JpaRepository<AtendimentoHistorico, Long> {

    @Query("select h from AtendimentoHistorico h where h.atendimento =: atendimento")
    List<AtendimentoHistorico> findAtendimentoHistoricoByAtendimentoAndIdAtendimento(@Param("atendimento") Long atendimento);
    @Query("select h from AtendimentoHistorico h where h.atendente =: atendente")
    List<AtendimentoHistorico> findAtendimentoHistoricoByAtendenteAndIdAtendimentoHistorico(@Param("atendente") Long atendente);
}
