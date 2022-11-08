package com.example.videojuegos.services;

import com.example.videojuegos.entities.Videojuego;
import com.example.videojuegos.repository.RepositorioVideoJuego;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioVideoJuego implements ServiceBase<Videojuego> {

    @Autowired
    RepositorioVideoJuego repositorioVideoJuego;

    @Override
    @Transactional
    public List<Videojuego> findAll() throws Exception {
      try {
          List<Videojuego>videoJuegos=this.repositorioVideoJuego.findAll();
          return videoJuegos;
      }catch (Exception e){
          throw new Exception(e.getMessage());
      }
    }

    @Override
    @Transactional
    public Videojuego findById(Long id) throws Exception {
        try {
            Optional<Videojuego>opt=this.repositorioVideoJuego.findById(id);
            return opt.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Videojuego saveOne(Videojuego entity) throws Exception {
        try {
            Videojuego videoJuego= this.repositorioVideoJuego.save(entity);
            return videoJuego;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Videojuego updateOne(Videojuego entity, long id) throws Exception {
        try {
            Optional<Videojuego>opt=this.repositorioVideoJuego.findById(id);
            Videojuego videoJuego= opt.get();
            videoJuego= this.repositorioVideoJuego.save(entity);
            return videoJuego;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean deleteById(long id) throws Exception {
        try {
            Optional<Videojuego> opt = this.repositorioVideoJuego.findById(id);
            if (!opt.isEmpty()) {
                Videojuego videojuego = opt.get();
                videojuego.setActivo(!videojuego.isActivo());
                this.repositorioVideoJuego.save(videojuego);
            } else {
                throw new Exception();
            }
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public List<Videojuego> findAllByActivo() throws Exception{
        try {
            List<Videojuego> entities = this.repositorioVideoJuego.findAllByActivo();
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Videojuego findByIdAndActivo(long id) throws Exception {
        try {
            Optional<Videojuego> opt = this.repositorioVideoJuego.findByIdAndActivo(id);
            return opt.get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public List<Videojuego> findByTitle(String q) throws Exception{
        try{
            List<Videojuego> entities = this.repositorioVideoJuego.findByTitle(q);
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
