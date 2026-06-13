package com.upiiz.proyecto_final.services;

import com.upiiz.proyecto_final.entities.CarreraEntity;
import com.upiiz.proyecto_final.repositories.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarreraServiceImpl implements CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;

    @Override
    public List<CarreraEntity> listadoCarreras() {
        return carreraRepository.findAll();
    }

    @Override
    public Optional<CarreraEntity> carreraPorId(Long id) {
        return carreraRepository.findById(id);
    }

    @Override
    public CarreraEntity agregarCarrera(CarreraEntity carrera) {
        return carreraRepository.save(carrera);
    }

    @Override
    public CarreraEntity actualizarCarrera(Long id, CarreraEntity carrera) {
        carrera.setId(id);
        return carreraRepository.save(carrera);
    }

    @Override
    public void eliminarCarrera(Long id) {
        carreraRepository.deleteById(id);
    }
}
