package org.aguzman.apiservlet.webapp.headers.models.Entities;

import jakarta.persistence.*;
// Paso 1: Indica que esta clase es una entidad JPA.
@Entity 
// Paso 2: Mapea la clase a la tabla `categorias`.
@Table(name = "categorias")
public class Categoria {
    @Id // Paso 3: Clave primaria de la entidad.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Paso 4: Generación automática del ID.
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
