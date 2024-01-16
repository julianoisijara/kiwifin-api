package com.kiwifin.api.repositories;

import com.kiwifin.api.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNomeContains(String nome);

    List<Cliente> findByCpfEquals(String cpf);

}
