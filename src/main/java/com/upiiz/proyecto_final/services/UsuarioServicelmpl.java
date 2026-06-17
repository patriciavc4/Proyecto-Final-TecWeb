package com.upiiz.proyecto_final.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upiiz.proyecto_final.entities.UsuarioEntity;
import com.upiiz.proyecto_final.repositories.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioServicelmpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UsuarioEntity validarUsuario(String email, String contrasenia) {

        UsuarioEntity usuario = usuarioRepository.findByEmail(email);

        if (usuario != null && usuario.getContrasenia().equals(contrasenia)) {
            return usuario;
        }

        return null;
    }

    @Override
    public List<UsuarioEntity> listarUsuarios() {
        return usuarioRepository.findAll();
    }
}