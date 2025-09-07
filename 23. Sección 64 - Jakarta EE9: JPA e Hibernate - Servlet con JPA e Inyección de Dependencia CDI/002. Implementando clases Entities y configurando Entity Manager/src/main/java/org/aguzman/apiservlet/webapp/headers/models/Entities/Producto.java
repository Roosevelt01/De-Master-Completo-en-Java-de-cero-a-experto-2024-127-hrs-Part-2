package org.aguzman.apiservlet.webapp.headers.models.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity //Paso 1
@Table(name="productos") //Paso 1
public class Producto {
    @Id //Paso 3
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Paso 4
    private Long id;

    private String nombre;

    @ManyToMany(fetch = FetchType.LAZY)//Paso 4
    private Categoria categoria;

    private int precio;

    private String sku;

    @Column(name = "fecha_registro")//Paso 5
    private LocalDate fechaRegistro;

    public Producto() {
    }

    public Producto(Long id, String nombre, String tipo, int precio) {
        this.id = id;
        this.nombre = nombre;
        Categoria categoria = new Categoria();
        categoria.setNombre(tipo);
        this.categoria = categoria;
        this.precio = precio;
    }

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

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    //Paso 7
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
