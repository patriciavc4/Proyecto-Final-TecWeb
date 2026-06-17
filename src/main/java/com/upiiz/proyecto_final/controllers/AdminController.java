package com.upiiz.proyecto_final.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String panelPrincipal(){
        return "admin/dashboard";
    }

    @GetMapping("/carreras")
    public String gestionCarreras(){
        return "admin/carreras";
    }
}
