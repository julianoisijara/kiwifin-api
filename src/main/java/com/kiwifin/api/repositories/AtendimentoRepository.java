package com.kiwifin.api.repositories;

import com.kiwifin.api.entities.Atendimento;
import com.kiwifin.api.entities.Cliente;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

    List<Atendimento> findByClienteEqualsOrderByIdAtendimentoAsc(Cliente cliente);

    @Query("select a from Atendimento a order by a.idAtendimento asc")
    List<Atendimento> findByAtendimentosPaginado(Pageable pageable);
}
