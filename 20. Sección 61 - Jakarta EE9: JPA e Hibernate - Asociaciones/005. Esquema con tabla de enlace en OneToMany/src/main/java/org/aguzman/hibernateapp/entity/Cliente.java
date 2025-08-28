package org.aguzman.hibernateapp.entity;

import org.hibernate.mapping.Join;

import javax.persistence.*;
import javax.xml.namespace.QName;
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
    //@JoinColumn(name="id_cliente")
    // La anotación @JoinTable especifica que la relación se gestionará a través de una tabla de unión.
    @JoinTable(
        // name: Define el nombre personalizado de la tabla de unión que se creará en la base de datos.
        name = "tbl_clientes_direcciones",
    
        // joinColumns: Define la columna de la llave foránea en la tabla de unión
        // que hace referencia a la entidad propietaria de la relación (la clase actual: Cliente).
        joinColumns = @JoinColumn(name="id_cliente"),
        
        // inverseJoinColumns: Define la columna de la llave foránea en la tabla de unión
        // que hace referencia a la entidad del otro lado de la relación (la clase en la colección: Direccion).
        inverseJoinColumns = @JoinColumn(name = "id_direccion"),

        // uniqueConstraints: Aplica restricciones de unicidad a la tabla de unión. Es un punto crucial
        // para reforzar la lógica de "OneToMany": cada 'Direccion' solo puede pertenecer a un 'Cliente',
        // por lo tanto, la columna 'id_direccion' debe ser única en esta tabla.
        uniqueConstraints = @UniqueConstraint(columnNames = {"id_direccion"}))
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

        return  " --> " + " id=" + id +
                " | nombre='" + nombre + '\'' +
                " | apellido='" + apellido + '\'' +
                " | formaPago='" + formaPago + '\'' +
                " | creadoEn='" + creado + '\'' +
                " | editadoEn='" + editado + '\'' +
                " | direcciones='" + direcciones + '\'';
    }
}
