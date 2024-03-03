package com.kiwifin.api.repositories;

import com.kiwifin.api.entities.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

    Administrador findByCpfEquals(String cpf);
}
