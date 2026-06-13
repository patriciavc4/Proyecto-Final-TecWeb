package com.upiiz.proyecto_final.controllers;

import com.upiiz.proyecto_final.entities.CarreraEntity;
import com.upiiz.proyecto_final.services.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CarreraController {
    @Autowired
    private CarreraService carreraService;

    @GetMapping("/carreras")
    public String carreras(){
        return "carreras";
    }

    @GetMapping("/carreras/api/carreras")
    @ResponseBody
    public ResponseEntity<List<CarreraEntity>> listadoCarreras(){
        return ResponseEntity.ok(carreraService.listadoCarreras());
    }

    @GetMapping("/carreras/api/carreras/{id}")
    @ResponseBody
    public ResponseEntity<Optional<CarreraEntity>> carreraPorId(@PathVariable Long id){
        return ResponseEntity.ok(carreraService.carreraPorId(id));
    }

    @PostMapping("/carreras/api/carreras")
    @ResponseBody
    public ResponseEntity<CarreraEntity> agregarCarrera(@RequestBody CarreraEntity carrera){
        return ResponseEntity.ok(carreraService.agregarCarrera(carrera));
    }

    @PatchMapping("/carreras/api/carreras/{id}")
    @ResponseBody
    public ResponseEntity<CarreraEntity> actualizarCarrera(@PathVariable Long id, @RequestBody CarreraEntity carrera){
        return ResponseEntity.ok(carreraService.actualizarCarrera(id, carrera));
    }

    @DeleteMapping("/carreras/api/carreras/{id}")
    @ResponseBody
    public void eliminarCarrera(@PathVariable Long id){
        carreraService.eliminarCarrera(id);
    }
}