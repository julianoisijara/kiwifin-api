package com.kiwifin.api.repositories;

import com.kiwifin.api.entities.SupervisorQualidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupervisorQualidadeRepository extends JpaRepository<SupervisorQualidade, Long> {
}
