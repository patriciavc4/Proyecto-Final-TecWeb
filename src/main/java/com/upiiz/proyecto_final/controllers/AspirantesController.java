package com.upiiz.proyecto_final.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/Aspirantes")
public class AspirantesController {

    @GetMapping()
    public String getPaginaAspirantes()
    {
        return "Aspirantes";
    }

    @GetMapping("/correo/Masivo")
    @ResponseBody
    public ResponseEntity<?> getCorreoMasivo()
    {
        return (ResponseEntity<?>) ResponseEntity.ok();
    }

    @GetMapping("/correo/Individual")
    @ResponseBody
    public ResponseEntity<?> getCorreoIndividual() {
        return (ResponseEntity<?>) ResponseEntity.ok();
    }

    @GetMapping("/informacion/Personal")
    @ResponseBody
    public ResponseEntity<?> getInformacionPersonal()
    {
        return (ResponseEntity<?>) ResponseEntity.ok();
    }
    

}
