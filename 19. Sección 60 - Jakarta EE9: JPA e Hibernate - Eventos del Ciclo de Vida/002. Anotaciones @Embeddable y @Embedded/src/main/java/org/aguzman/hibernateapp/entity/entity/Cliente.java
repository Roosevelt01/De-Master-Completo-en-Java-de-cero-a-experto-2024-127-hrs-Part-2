package org.aguzman.hibernateapp.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;

    @Column(name = "forma_pago")
    private String formaPago;

    // --- NUEVOS CAMPOS DE AUDITORÍA ---
    // Paso 1: Mapea el atributo 'creadoEn' a la columna 'creado_en' en la base de datos.
    @Column(name = "creado_en")
    private LocalDateTime creadoEn;

    //Paso 2: Mapea el atributo 'editadoEn' a la columna 'editado_en'.
    @Column(name = "editado_en")
    private LocalDateTime editadoEn;

    public Cliente() {
    }

    public Cliente(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Cliente(Long id, String nombre, String apellido, String formaPago) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.formaPago = formaPago;
    }

    // --- MÉTODOS DE CALLBACK DEL CICLO DE VIDA ---
    
    // Paso 1: La anotación @PrePersist indica a JPA que ejecute este método
    // justo antes de realizar la operación INSERT en la base de datos.
    @PrePersist
    public void prePersist(){
        // Imprime un mensaje en la consola para demostrar que el callback se está ejecutando.
        System.out.println("Inicializar algo justo antesl del persist");

        // Asigna la fecha y hora actual al campo 'creadoEn'.
        this.creadoEn = LocalDateTime.now();
    }

    //Paso 2: La anotación @PreUpdate indica a JPA que ejecute este método
    // justo antes de realizar la operación UPDATE en una entidad ya existente.
    @PreUpdate
    public void preUpdate(){
        // Imprime un mensaje en la consola para demostrar la ejecución del callback.
        System.out.println("Inicializar algo justo antesl del update");

        // Asigna la fecha y hora actual al campo 'editadoEn'.
        this.editadoEn = LocalDateTime.now();
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    // --- GETTERS Y SETTERS PARA LOS NUEVOS CAMPOS ---
    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }

    public LocalDateTime getEditadoEn() {
        return editadoEn;
    }

    public void setEditadoEn(LocalDateTime editadoEn) {
        this.editadoEn = editadoEn;
    }

    // Se actualiza el método toString() para incluir los nuevos campos de auditoría.
    @Override
    public String toString() {
        return  "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", formaPago='" + formaPago + '\'' +
                ", creadoEn='" + creadoEn + '\'' +
                ", editadoEn='" + editadoEn + '\'' ;
    }
}