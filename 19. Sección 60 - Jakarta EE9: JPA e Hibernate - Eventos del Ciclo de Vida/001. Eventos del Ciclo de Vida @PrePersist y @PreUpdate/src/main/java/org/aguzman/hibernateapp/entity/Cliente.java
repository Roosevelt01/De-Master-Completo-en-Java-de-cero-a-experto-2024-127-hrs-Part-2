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

    //Paso 1
    @Column(name = "creado_en")
    private LocalDateTime creadoEn;

    //Paso 2
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

    //Paso 3
    @PrePersist
    public void prePersist(){
        System.out.println("Inicializar algo justo antesl del persist");
        this.creadoEn = LocalDateTime.now();
    }

    //Paso 4
    @PreUpdate
    public void preUpdate(){
        System.out.println("Inicializar algo justo antesl del update");
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

    // Paso 5
    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    // Paso 6
    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }

    // Paso 7
    public LocalDateTime getEditadoEn() {
        return editadoEn;
    }

    // Paso 8
    public void setEditadoEn(LocalDateTime editadoEn) {
        this.editadoEn = editadoEn;
    }

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
