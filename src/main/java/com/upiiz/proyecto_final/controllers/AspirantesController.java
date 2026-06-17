package com.upiiz.proyecto_final.controllers;

import com.upiiz.proyecto_final.entities.CarreraEntity;
import com.upiiz.proyecto_final.entities.UsuarioEntity;
import com.upiiz.proyecto_final.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/Aspirantes")
public class AspirantesController {

    private final UsuarioService usuarioService;

    public AspirantesController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping()
    public String getPaginaAspirantes()
    {
        return "Aspirantes";
    }

    @GetMapping("/Aspirante-registro")
    public String getAspirantesRegistro()
    {
        return "Sesion/Aspirantes-registro";
    }

    @GetMapping("/api/Aspirantes")
    @ResponseBody
    public ResponseEntity<List<UsuarioEntity>> listadoCarreras(){
        return ResponseEntity.ok(usuarioService.listarUsuarios());
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
