package com.upiiz.proyecto_final.repositories;

import com.upiiz.proyecto_final.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    UsuarioEntity findByUsuario(String usuario);
}