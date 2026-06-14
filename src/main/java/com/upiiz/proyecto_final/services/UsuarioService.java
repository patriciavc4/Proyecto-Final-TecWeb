package com.upiiz.proyecto_final.services;

import com.upiiz.proyecto_final.entities.UsuarioEntity;

public interface UsuarioService {
    UsuarioEntity validarUsuario(String email, String contrasenia);
}