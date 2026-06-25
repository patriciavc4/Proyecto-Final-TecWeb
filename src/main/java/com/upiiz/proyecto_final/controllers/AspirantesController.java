package com.upiiz.proyecto_final.controllers;

import com.upiiz.proyecto_final.entities.AspiranteEntity;
import com.upiiz.proyecto_final.services.AspiranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Aspirantes/api")
public class AspirantesController {

    @Autowired
    private AspiranteService aspiranteService;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String adminEmail;

    // Listar todos los aspirantes
    @GetMapping("/Aspirantes")
    public ResponseEntity<List<AspiranteEntity>> listar() {
        return ResponseEntity.ok(aspiranteService.listado());
    }

    // Verificar si el correo ya está registrado
    @GetMapping("/verificar-email")
    public ResponseEntity<Boolean> verificarEmail(@RequestParam String email) {
        return ResponseEntity.ok(aspiranteService.existeEmail(email));
    }

    // Registrar aspirante y enviar correo al admin
    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody AspiranteEntity aspirante) {
        // Verificar duplicado antes de guardar
        if (aspiranteService.existeEmail(aspirante.getEmail())) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "El correo ya está registrado"));
        }

        AspiranteEntity guardado = aspiranteService.agregarAspirante(aspirante);

        // Enviar correo al administrador
        try {
            SimpleMailMessage mensaje = new SimpleMailMessage();
            mensaje.setTo(adminEmail);
            mensaje.setSubject("Nuevo aspirante registrado");
            mensaje.setText(
                    "Se registró un nuevo aspirante:\n\n" +
                            "Nombre: " + guardado.getNombre() + "\n" +
                            "Correo: " + guardado.getEmail() + "\n" +
                            "Teléfono: " + guardado.getTelefono()
            );
            mailSender.send(mensaje);
        } catch (Exception e) {
            // El registro se guarda aunque falle el correo
            System.err.println("Error al enviar correo: " + e.getMessage());
        }

        return ResponseEntity.ok(guardado);
    }

    // Correo masivo a todos los aspirantes
    @PostMapping("/correo/masivo")
    public ResponseEntity<?> correoMasivo(@RequestBody Map<String, String> body) {
        String asunto = body.get("asunto");
        String mensaje = body.get("mensaje");

        List<AspiranteEntity> aspirantes = aspiranteService.listado();
        for (AspiranteEntity aspirante : aspirantes) {
            try {
                SimpleMailMessage mail = new SimpleMailMessage();
                mail.setTo(aspirante.getEmail());
                mail.setSubject(asunto);
                mail.setText(mensaje);
                mailSender.send(mail);
            } catch (Exception e) {
                System.err.println("Error enviando a " + aspirante.getEmail() + ": " + e.getMessage());
            }
        }
        return ResponseEntity.ok(Map.of("mensaje", "Correo masivo enviado a " + aspirantes.size() + " aspirantes"));
    }

    // Correo individual a un aspirante por ID
    @PostMapping("/correo/individual/{id}")
    public ResponseEntity<?> correoIndividual(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        return aspiranteService.aspirantePorId(id).map(aspirante -> {
            try {
                SimpleMailMessage mail = new SimpleMailMessage();
                mail.setTo(aspirante.getEmail());
                mail.setSubject(body.get("asunto"));
                mail.setText(body.get("mensaje"));
                mailSender.send(mail);
                return ResponseEntity.ok(Map.of("mensaje", "Correo enviado a " + aspirante.getEmail()));
            } catch (Exception e) {
                return ResponseEntity.internalServerError()
                        .body(Map.of("error", "No se pudo enviar el correo"));
            }
        }).orElse(ResponseEntity.notFound().build());
    }
}