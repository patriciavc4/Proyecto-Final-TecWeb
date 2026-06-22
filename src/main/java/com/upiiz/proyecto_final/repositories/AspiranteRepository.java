package com.upiiz.proyecto_final.repositories;

import com.upiiz.proyecto_final.entities.AspiranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AspiranteRepository extends JpaRepository<AspiranteEntity, Long> {
    boolean existsByEmail(String email);
}