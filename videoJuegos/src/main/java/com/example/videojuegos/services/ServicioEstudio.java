package com.example.videojuegos.services;

import com.example.videojuegos.entities.Estudio;
import com.example.videojuegos.repository.RepositorioEstudio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioEstudio implements ServiceBase<Estudio> {
    @Autowired
    RepositorioEstudio repositorioEstudio;

    @Override
    @Transactional
    public List<Estudio> findAll() throws Exception {
        try{
            List<Estudio> estudios= this.repositorioEstudio.findAll();
            return estudios;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Estudio findById(Long id) throws Exception {
        try {
            Optional<Estudio> opt =this.repositorioEstudio.findById(id);
            return opt.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Estudio saveOne(Estudio entity) throws Exception {
        try {
            Estudio estudio= this.repositorioEstudio.save(entity);
            return estudio;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Estudio updateOne(Estudio entity, long id) throws Exception {
      try {
          Optional<Estudio>opt=this.repositorioEstudio.findById(id);
          Estudio estudio= opt.get();
          estudio= this.repositorioEstudio.save(entity);
          return estudio;
      }catch(Exception e){
          throw new Exception(e.getMessage());
      }
    }

    @Override
    @Transactional
    public boolean deleteById(long id) throws Exception {
        try {
            this.repositorioEstudio.deleteById(id);
            return true;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
