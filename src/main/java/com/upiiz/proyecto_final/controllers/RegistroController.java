package com.upiiz.proyecto_final.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistroController {

    @GetMapping("/")
    public String redireccionRegistro() {
        return "redirect:/registro";
    }

    @GetMapping("/registro")
    public String registro() {
        return "Sesion/registro";
    }
}