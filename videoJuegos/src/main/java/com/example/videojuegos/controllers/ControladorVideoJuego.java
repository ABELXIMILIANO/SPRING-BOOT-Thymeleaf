package com.example.videojuegos.controllers;

import com.example.videojuegos.entities.Videojuego;
import com.example.videojuegos.services.ServicioVideoJuego;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ControladorVideoJuego {

    @Autowired
    private ServicioVideoJuego servcioVideoJuego;

    @GetMapping("/inicio")
    public String inicio(Model model){
        try {
            List<Videojuego> videoJuegos= this.servcioVideoJuego.findAllByActivo();
            model.addAttribute("videoJuegos", videoJuegos);
            return "views/inicio";
        }catch (Exception e){
            return "";
        }
    }
}
