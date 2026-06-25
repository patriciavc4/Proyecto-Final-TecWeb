package com.upiiz.proyecto_final.services;

import com.upiiz.proyecto_final.entities.AspiranteEntity;
import com.upiiz.proyecto_final.repositories.AspiranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AspiranteServiceImpl implements AspiranteService {

    @Autowired
    AspiranteRepository aspiranteRepository;

    @Override
    public List<AspiranteEntity> listado() {
        return aspiranteRepository.findAll();
    }

    @Override
    public Optional<AspiranteEntity> aspirantePorId(Long id) {
        return aspiranteRepository.findById(id);
    }

    @Override
    public AspiranteEntity agregarAspirante(AspiranteEntity aspirante) {
        return aspiranteRepository.save(aspirante);
    }

    @Override
    public boolean existeEmail(String email) {
        return aspiranteRepository.existsByEmail(email);
    }

}