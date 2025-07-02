package org.aguzman.apiservlet.webapp.headers.models;

import java.time.LocalDate;

public class Producto {
    private Long id;
    private String nombre;
    private Categoria categoria; // Paso 1: Reemplazo de 'tipo'
    private int precio;
    private String sku; // Paso 2: Nuevo atributo
    private LocalDate fechaRegistro; // Paso 3: Nuevo atributo
    
    public Producto() {
    }

    public Producto(Long id, String nombre, String tipo, int precio) {
        this.id = id;
        this.nombre = nombre;
        // Paso 4: Se crea un objeto Categoria internamente
        Categoria categoria = new Categoria();
        // Paso 5: Se le asigna el nombre
        categoria.setNombre(tipo);
        // Paso 6: Se asigna el objeto completo
        this.categoria = categoria;
        this.precio = precio;
    }

    // Paso 7: Nuevos Getters y Setters para categoria, sku y fechaRegistro...

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
