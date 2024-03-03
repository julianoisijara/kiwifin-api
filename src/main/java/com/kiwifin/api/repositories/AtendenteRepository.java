package com.kiwifin.api.repositories;

import com.kiwifin.api.entities.Atendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtendenteRepository extends JpaRepository<Atendente, Long>{

    Atendente findByCpfEquals(String cpf);
}
