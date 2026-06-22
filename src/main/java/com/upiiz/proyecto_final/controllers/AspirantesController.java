package com.upiiz.proyecto_final.controllers;

import com.upiiz.proyecto_final.entities.AspiranteEntity;
import com.upiiz.proyecto_final.services.AspiranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class AspirantesController {

    @Autowired
    private AspiranteService aspiranteService;

    @GetMapping("/Aspirantes")
    public String aspirantes() {
        return "Aspirantes";
    }

    // Devuelve todos los aspirantes en JSON (para llenar la tabla con AJAX)
    @GetMapping("/aspirantes/api/aspirantes")
    @ResponseBody
    public ResponseEntity<List<AspiranteEntity>> listadoAspirantes() {
        return ResponseEntity.ok(aspiranteService.listado());
    }

    // Devuelve un aspirante por su ID en JSON (para ver sus datos en modal)
    @GetMapping("/aspirantes/api/aspirantes/{id}")
    @ResponseBody
    public ResponseEntity<Optional<AspiranteEntity>> aspirantePorId(@PathVariable Long id) {
        return ResponseEntity.ok(aspiranteService.aspirantePorId(id));
    }

    // Guarda un nuevo aspirante desde el formulario de registro público
    @PostMapping("/aspirantes/api/aspirantes")
    @ResponseBody
    public ResponseEntity<AspiranteEntity> registrarAspirante(@RequestBody AspiranteEntity aspirante) {
        return ResponseEntity.ok(aspiranteService.agregarAspirante(aspirante));
    }

    // Verifica si un email ya está registrado
    @GetMapping("/aspirantes/api/verificar-email")
    @ResponseBody
    public ResponseEntity<Boolean> verificarEmail(@RequestParam String email) {
        return ResponseEntity.ok(aspiranteService.existeEmail(email));
    }

    // Correo masivo a todos los aspirantes
    @PostMapping("/aspirantes/api/correo/masivo")
    @ResponseBody
    public ResponseEntity<?> correoMasivo() {
        return ResponseEntity.ok("Correo masivo enviado");
    }

    // Correo individual a un aspirante específico
    @PostMapping("/aspirantes/api/correo/{id}")
    @ResponseBody
    public ResponseEntity<?> correoIndividual(@PathVariable Long id) {
        return ResponseEntity.ok("Correo enviado");
    }
}