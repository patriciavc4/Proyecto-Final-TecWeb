package com.upiiz.proyecto_final.services;

import com.upiiz.proyecto_final.entities.CarreraEntity;

import java.util.List;
import java.util.Optional;


public interface CarreraService {
    List<CarreraEntity> listadoCarreras();
    Optional<CarreraEntity> carreraPorId(Long id);
    CarreraEntity agregarCarrera(CarreraEntity carrera);
    CarreraEntity actualizarCarrera(Long id, CarreraEntity carrera);
    void eliminarCarrera(Long id);
}
