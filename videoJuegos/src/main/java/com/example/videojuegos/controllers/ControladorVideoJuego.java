package com.example.videojuegos.controllers;

import com.example.videojuegos.entities.Videojuego;
import com.example.videojuegos.services.ServicioCategoria;
import com.example.videojuegos.services.ServicioEstudio;
import com.example.videojuegos.services.ServicioVideoJuego;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.util.Calendar.*;

@Controller
public class ControladorVideoJuego {

    @Autowired
    private ServicioVideoJuego servcioVideoJuego;
    @Autowired
    private ServicioCategoria servicioCategoria;
    @Autowired
    private ServicioEstudio servicioEstudio;

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
    @GetMapping("/formulario/videojuego/{id}")
    public String formularioVideojuego(Model model, @PathVariable("id")long id){
        try{
            model.addAttribute("categorias",this.servicioCategoria.findAll());
            model.addAttribute("estudios",this.servicioEstudio.findAll());
            if (id==0){
                model.addAttribute("videojuego",new Videojuego());
            }else{
                model.addAttribute("videojuego",this.servcioVideoJuego.findById(id));
            }
            return "views/formulario/videojuego";

        }catch (Exception e){
            model.addAttribute("error",e.getMessage());
            return "error";
        }
    }
    @PostMapping("/formulario/videojuego/{id}")
    public String guardarVideojuego(@ModelAttribute("videojuego")Videojuego videojuego,
                                    @RequestParam("archivo") MultipartFile archivo,
                                    Model model,
                                    @PathVariable("id")long id){
        try{
            String ruta = "C://videojuegos/imagenes";
            int index =archivo.getOriginalFilename().indexOf(".");
            String extension="";
            extension="."+archivo.getOriginalFilename().substring(index+1);
            String nombreFoto= getInstance().getTimeInMillis()+extension;
            Path rutaAbsoluta=id !=0 ? Paths.get(ruta+"//"+videojuego.getImagen()):
                    Paths.get(ruta+"//"+nombreFoto);

            if (id==0){
                Files.write(rutaAbsoluta,archivo.getBytes());
                videojuego.setImagen(nombreFoto);
                this.servcioVideoJuego.saveOne(videojuego);
            }else{
                if(!archivo.isEmpty()){
                    Files.write(rutaAbsoluta,archivo.getBytes());
                    this.servcioVideoJuego.updateOne(videojuego,id);
                }

            }
            return "redirect:/crud";

        }catch (Exception e){
            model.addAttribute("error",e.getMessage());
            return "error";
        }
    }

    @GetMapping("/eliminar/videojuego/{id}")
    public String eliminarVideojuego(Model model, @PathVariable("id")long id){

        try{
            model.addAttribute("videojuego",this.servcioVideoJuego.findById(id));
            return "views/formulario/eliminar";
        }catch (Exception e){
            model.addAttribute("error",e.getMessage());
            return "error";
        }
    }

    @PostMapping("/eliminar/videojuego/{id}")
    public String desactivarVideojuego(Model model, @PathVariable("id")long id){

        try{
            this.servcioVideoJuego.deleteById(id);
            return "redirect:/crud";
        }catch (Exception e){
            model.addAttribute("error",e.getMessage());
            return "error";
        }
    }







}
