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

    // La anotación @Embedded le dice a JPA que los campos de la clase Auditoria
    // deben ser tratados como si estuvieran declarados directamente aquí,
    // mapeándolos a las columnas de la tabla 'clientes'.
    @Embedded    
    // Se declara e inicializa el objeto embebido. La inicialización es crucial
    // para evitar NullPointerExceptions al momento de la persistencia.
    private Auditoria audit = new Auditoria();

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

    // El método toString() se actualiza para acceder a las fechas de auditoría
    // a través del objeto 'audit'.
    @Override
    public String toString() {
        return  "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", formaPago='" + formaPago + '\'' +
                ", creadoEn='" + audit.getCreadoEn() + '\'' +
                ", editadoEn='" + audit.getEditadoEn() + '\'' ;
    }
}