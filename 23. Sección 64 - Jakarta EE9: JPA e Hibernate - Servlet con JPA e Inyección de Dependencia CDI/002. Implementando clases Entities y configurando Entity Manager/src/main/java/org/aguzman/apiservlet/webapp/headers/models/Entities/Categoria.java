package org.aguzman.apiservlet.webapp.headers.models.Entities;

import jakarta.persistence.*;

@Entity//Paso 1
@Table(name = "categorias")//Paso 2
public class Categoria {
    @Id//Paso 3
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Paso 4
    private Long id;

    private String nombre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
