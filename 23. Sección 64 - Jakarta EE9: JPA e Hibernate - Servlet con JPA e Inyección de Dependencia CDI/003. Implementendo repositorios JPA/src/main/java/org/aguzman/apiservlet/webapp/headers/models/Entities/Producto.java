package org.aguzman.apiservlet.webapp.headers.models.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity // Paso 1: Anotación obligatoria para marcar la clase como una entidad JPA.
@Table(name="productos") // Paso 2: Define el nombre de la tabla en la base de datos a la que se mapea esta entidad.
public class Producto {
    @Id // Paso 3: Marca el campo como la clave primaria de la tabla.
    // Paso 4: Configura la estrategia para la generación automática del valor de la clave primaria.
    // `IDENTITY` usa la columna `AUTO_INCREMENT` de la base de datos.
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    private String nombre;
    
    // Paso 5: Define una relación de muchos a uno. Muchos productos pueden
    // pertenecer a una sola categoría. El `fetch = LAZY` indica una carga perezosa para optimizar el rendimiento.
    @ManyToMany(fetch = FetchType.LAZY)//Paso 4
    private Categoria categoria;

    private int precio;

    private String sku;

    // Paso 6: Se usa cuando el nombre del campo en la clase Java es diferente al
    // de la columna en la base de datos.
    @Column(name = "fecha_registro")
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
