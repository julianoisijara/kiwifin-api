package com.kiwifin.api.repositories;

import com.kiwifin.api.entities.ColaboradorPerfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColaboradorPerfilRepository extends JpaRepository<ColaboradorPerfil, Long> {

    List<ColaboradorPerfil> findByIdColaboradorPerfil(Long id);

}
