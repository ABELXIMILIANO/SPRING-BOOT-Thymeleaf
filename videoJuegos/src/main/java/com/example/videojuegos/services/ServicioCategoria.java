package com.example.videojuegos.services;

import com.example.videojuegos.entities.Categoria;
import com.example.videojuegos.repository.RepositorioCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioCategoria implements ServiceBase<Categoria>{
    @Autowired
    RepositorioCategoria repositorioCategoria;

    @Override
    @Transactional
    public List<Categoria> findAll() throws Exception {
        try {
            List<Categoria> categorias = this.repositorioCategoria.findAll();
            return categorias;
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Categoria findById(Long id) throws Exception {
        try {
            Optional<Categoria> categoria = this.repositorioCategoria.findById(id);
            return categoria.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Categoria saveOne(Categoria entity) throws Exception {
        try {
            Categoria categoria = this.repositorioCategoria.save(entity);
            return categoria;
        }catch (Exception e ){
            throw new Exception(e.getMessage());
        }

    }

    @Override
    @Transactional
    public Categoria updateOne(Categoria entity, long id) throws Exception {
        try {
            Optional<Categoria>opt = this.repositorioCategoria.findById(id);
            Categoria categoria = opt.get();
            categoria=this.repositorioCategoria.save(entity);
            return categoria;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean deleteById(long id) throws Exception {
        try {
            this.repositorioCategoria.deleteById(id);
            return true;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
