package com.upiiz.proyecto_final.services;

import com.upiiz.proyecto_final.entities.AspiranteEntity;

import java.util.List;
import java.util.Optional;

public interface AspiranteService {
    List<AspiranteEntity> listado();
    Optional<AspiranteEntity> aspirantePorId(Long id);
    AspiranteEntity agregarAspirante(AspiranteEntity aspirante);
    boolean existeEmail(String email);
}