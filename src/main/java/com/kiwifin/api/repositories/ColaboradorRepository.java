package com.kiwifin.api.repositories;

import com.kiwifin.api.entities.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {

    Colaborador findByCpfEquals(String cpf);
    UserDetails findByNomeEquals(String nome);
}
