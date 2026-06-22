package com.upiiz.proyecto_final.controllers;

import com.upiiz.proyecto_final.entities.UsuarioEntity;
import com.upiiz.proyecto_final.services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String login(HttpSession session) {
        if (session.getAttribute("usuario") != null) {
            return "redirect:/Aspirantes";
        }
        return "Sesion/inicio";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> loginPost(@RequestParam String usuario,
                                       @RequestParam String password,
                                       HttpSession session) {

        Map<String, Object> respuesta = new HashMap<>();
        UsuarioEntity user = usuarioService.validarUsuario(usuario, password);

        if (user == null) {
            respuesta.put("success", false);
            respuesta.put("message", "Credenciales incorrectas o usuario no encontrado");
            return ResponseEntity.status(401).body(respuesta);
        }

        session.setAttribute("usuario", user);
        respuesta.put("success", true);
        respuesta.put("redirect", "/Aspirantes");

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}