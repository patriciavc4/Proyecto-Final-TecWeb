package com.upiiz.proyecto_final.services;

import com.upiiz.proyecto_final.entities.UsuarioEntity;

import java.util.List;

public interface UsuarioService {
    UsuarioEntity validarUsuario(String usuario, String password);
}