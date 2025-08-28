package org.aguzman.hibernateapp.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Embedded
    private Auditoria audit = new Auditoria();

    @OneToMany(cascade =  CascadeType.ALL, orphanRemoval = true )
    // La anotación @JoinColumn le dice a Hibernate: "No crees una tabla de unión".
    // En su lugar, crea una columna en la tabla 'direcciones' llamada 'id_cliente'
    // que actuará como llave foránea hacia la tabla 'clientes'.
    @JoinColumn(name="id_cliente")
    private List<Direccion> direcciones;

    public Cliente() {
        direcciones = new ArrayList<>();
    }

    public Cliente(String nombre, String apellido) {
        this();
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Cliente(Long id, String nombre, String apellido, String formaPago) {
        this();
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.formaPago = formaPago;
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

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    @Override
    public String toString() {
        LocalDateTime creado = this.audit != null? audit.getCreadoEn():null;
        LocalDateTime editado = this.audit != null? audit.getCreadoEn():null;

        return  " many(Clientes) To One(Facturas) " + " id=" + id +
                " | nombre='" + nombre + '\'' +
                " | apellido='" + apellido + '\'' +
                " | formaPago='" + formaPago + '\'' +
                " | creadoEn='" + creado + '\'' +
                " | editadoEn='" + editado + '\'' +
                " | direcciones='" + direcciones + '\'';
    }
}
