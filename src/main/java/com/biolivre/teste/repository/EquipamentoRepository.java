package com.biolivre.teste.repository;

import com.biolivre.teste.domain.Equipamento;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Equipamento entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {}
