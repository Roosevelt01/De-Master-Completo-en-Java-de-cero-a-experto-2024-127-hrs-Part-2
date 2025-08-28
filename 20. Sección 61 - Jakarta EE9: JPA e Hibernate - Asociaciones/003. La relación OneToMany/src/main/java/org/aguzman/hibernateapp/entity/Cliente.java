package org.aguzman.hibernateapp.entity;

import javax.persistence.*;
import java.time.LocalDate;
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

    // Paso 1: La anotación @OneToMany establece una relación de uno (Cliente) a muchos (Direccion).
    // cascade = CascadeType.ALL: Propaga las operaciones (persist, merge, remove, etc.)
    // del Cliente a sus Direcciones asociadas. Si guardas un cliente, sus direcciones también se guardan.
    // orphanRemoval = true: Si una Dirección es eliminada de esta lista, también será eliminada
    // de la base de datos, evitando registros "huérfanos".
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    // Se declara una Lista para contener las múltiples direcciones asociadas a este cliente.
    private List<Direccion> direcciones;

    // --- MODIFICACIÓN EN CONSTRUCTORES ---
    public Cliente() {
        // PAso 2: Se inicializa la lista en el constructor vacío para evitar NullPointerExceptions
        // al intentar añadir direcciones a un cliente nuevo.
        direcciones = new ArrayList<>();
    }

    public Cliente(String nombre, String apellido) {
        // Paso 3: Se llama al constructor vacío (this()) para asegurar que la lista 'direcciones'
        // sea siempre inicializada, manteniendo la consistencia.
        this();
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Cliente(Long id, String nombre, String apellido, String formaPago) {
        // Se llama al constructor vacío (this()) para asegurar que la lista 'direcciones'
        // sea siempre inicializada, manteniendo la consistencia.
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

    // --- GETTERS Y SETTERS AÑADIDOS ---
    // Paso 3: Getter para acceder a la lista de direcciones del cliente.
    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    // --- MODIFICACIÓN EN toString() ---
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
                // Paso 4: Se añade la lista de direcciones a la representación en String del objeto Cliente.
                " | direcciones='" + direcciones + '\'';
    }
}
