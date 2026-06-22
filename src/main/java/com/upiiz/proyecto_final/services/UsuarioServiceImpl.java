package com.upiiz.proyecto_final.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upiiz.proyecto_final.entities.UsuarioEntity;
import com.upiiz.proyecto_final.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UsuarioEntity validarUsuario(String usuario, String password) {

        UsuarioEntity usuarioEncontrado = usuarioRepository.findByUsuario(usuario);

        if (usuarioEncontrado != null && usuarioEncontrado.getPassword().equals(password)) {
            return usuarioEncontrado;
        }

        return null;
    }
}