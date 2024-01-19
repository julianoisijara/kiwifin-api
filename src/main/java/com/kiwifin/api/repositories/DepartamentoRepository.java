package com.kiwifin.api.repositories;

import com.kiwifin.api.entities.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

    List<Departamento> findByNomeContains(String nome);
    List<Departamento> findByStatusEquals(Boolean status);
}
