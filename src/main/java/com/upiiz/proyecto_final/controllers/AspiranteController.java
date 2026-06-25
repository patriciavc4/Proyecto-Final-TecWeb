package com.upiiz.proyecto_final.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Aspirantes")
public class AspiranteController {

    @GetMapping
    public String index() {
        return "aspirantes";
    }
}