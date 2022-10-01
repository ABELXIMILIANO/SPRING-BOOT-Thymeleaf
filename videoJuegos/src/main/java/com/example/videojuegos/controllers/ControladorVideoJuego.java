package com.example.videojuegos.controllers;

import com.example.videojuegos.entities.Videojuego;
import com.example.videojuegos.services.ServicioVideoJuego;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ControladorVideoJuego {

    @Autowired
    private ServicioVideoJuego servcioVideoJuego;

    @GetMapping("/inicio")
    public String inicio(Model model){
        try {
            List<Videojuego> videoJuegos= this.servcioVideoJuego.findAllByActivo();
            model.addAttribute("videojuegos", videoJuegos);
            return "views/inicio";
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }

    }
    @GetMapping("/detalle/{id}")
    public String detalleVideoJuego(Model model, @PathVariable("id")long id) {
        try {
            Videojuego videojuego = this.servcioVideoJuego.findByIdAndActivo(id);
            model.addAttribute("videojuego",videojuego);
            return "views/detalle";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }

    }
    @GetMapping(value = "/busqueda")
    public String busquedaVideoJuego(Model model, @RequestParam(value="query",required = false) String q){
        try {
            List<Videojuego>videojuegos=this.servcioVideoJuego.findByTitle(q);
            model.addAttribute("videojuegos",videojuegos);

            return "views/busqueda";
        }catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }

    }

   @GetMapping("/crud")
    public String crudVideoJuego(Model model){
        try {
            List<Videojuego> videojuegos= this.servcioVideoJuego.findAll();
            model.addAttribute("videojuegos", videojuegos);
            return "views/crud";
        }catch(Exception e){
            model.addAttribute("error",e.getMessage());
            return "error";
        }
    }



}
