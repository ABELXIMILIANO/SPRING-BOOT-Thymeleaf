package com.example.videojuegos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controlador {

    @GetMapping(value = "/")
    public String index(Model model){
        String saludo = "Hola Thymleaf";
        model.addAttribute("saludo",saludo);
        return "index";
    }
}
