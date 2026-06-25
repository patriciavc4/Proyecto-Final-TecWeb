package com.upiiz.proyecto_final.repositories;

import com.upiiz.proyecto_final.entities.AspiranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AspiranteRepository extends JpaRepository<AspiranteEntity, Long> {
    boolean existsByEmail(String email);
}