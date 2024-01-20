package com.kiwifin.api.repositories;

import com.kiwifin.api.entities.Motivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotivoRepository extends JpaRepository<Motivo, Long> {

    List<Motivo> findByNomeContains(String nome);
    List<Motivo> findByStatusEquals(Boolean status);
}
