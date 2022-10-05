package com.example.videojuegos.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Table(name = "videojuegos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Videojuego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String titulo;
    private String descripcion;
    private String imagen;
    private float precio;
    private short stock;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaLanzamiento;

    private boolean activo = true;

    @NotNull(message = "Es requerido el estudio")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_estudio",nullable = false)
    private Estudio estudio;

    @NotNull(message = "Es requerido la categoria ")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_categoria",nullable = false)
    private Categoria categoria;

}
