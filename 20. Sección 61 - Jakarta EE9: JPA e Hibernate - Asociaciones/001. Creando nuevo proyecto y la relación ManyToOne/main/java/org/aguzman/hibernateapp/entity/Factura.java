package org.aguzman.hibernateapp.entity;

import javax.persistence.*;

// Marca la clase como una entidad JPA.
@Entity
// Mapea esta entidad a una tabla llamada 'facturas' en la base de datos.
@Table(name="facturas")
public class Factura {

    // Define la llave primaria de la tabla.
    @Id
    // Configura la estrategia de generación de la llave primaria (autoincremental).
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private Long total;

    // --- Definición de la Relación ---
    // La anotación @ManyToOne define una relación de "muchos a uno".
    // 'Many' (Muchos) se refiere a la clase actual (Factura).
    // 'One' (Uno) se refiere a la clase del atributo (Cliente).
    @ManyToOne
    // Este atributo representa la relación con la entidad Cliente.
    // JPA lo traducirá a una columna de llave foránea en la tabla 'facturas'.
    private Cliente cliente;

    // Constructor vacío, requerido por JPA.
    public Factura() {}

    // Constructor útil para crear nuevas facturas con datos iniciales.
    public Factura(String descripcion, Long total) {
        this.descripcion = descripcion;
        this.total = total;
    }

    // --- Getters y Setters para todos los atributos ---
    public Long getId() {return id;}
    
    public void setId(Long id) {this.id = id;}
    
    public String getDescripcion() {return descripcion;}
    
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
    
    public Long getTotal() {return total;}
    
    public void setTotal(Long total) {this.total = total;}
    
    public Cliente getCliente() {return cliente;}
    
    public void setCliente(Cliente cliente) {this.cliente = cliente;}
}