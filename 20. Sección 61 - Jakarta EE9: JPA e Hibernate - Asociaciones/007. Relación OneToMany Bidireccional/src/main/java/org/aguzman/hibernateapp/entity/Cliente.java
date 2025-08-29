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
    @JoinTable(name = "tbl_clientes_direcciones", joinColumns = @JoinColumn(name="id_cliente")
    , inverseJoinColumns = @JoinColumn(name = "id_direccion")
    , uniqueConstraints = @UniqueConstraint(columnNames = {"id_direccion"}))
    private List<Direccion> direcciones;

    // Paso 1: Definición de la relación inversa
    // La anotación @OneToMany define la colección.
    // cascade = CascadeType.ALL y orphanRemoval = true funcionan igual que antes.
    // mappedBy = "cliente": ¡Este es el atributo CLAVE! Le dice a JPA:
    // "No intentes crear una llave foránea o tabla de unión para esta relación.
    // La gestión de esta relación está 'mapeada por' el atributo llamado 'cliente'
    // en la clase Factura". Esto designa a Cliente como el lado inverso.
    @OneToMany(cascade =  CascadeType.ALL, orphanRemoval = true, mappedBy = "cliente")
    private List<Factura> facturas;

    public Cliente() {
        // Paso 2: Inicialización de la nueva colección
        // Se inicializa la lista de facturas en el constructor para evitar NullPointerExceptions.
        facturas = new ArrayList<>();
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

    // Paso 3: Getter y Setter para la nueva colección
    public Auditoria getAudit() {
        return audit;
    }

    public void setAudit(Auditoria audit) {
        this.audit = audit;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    // Paso 4: Método Ayudante (Helper Method) para Sincronización
    // Este método es una buena práctica para gestionar relaciones bidireccionales.
    public Cliente addFactura(Factura factura){
        // Añade la factura a la colección del cliente (lado inverso).
        this.facturas.add(factura);

        // Establece el cliente en la factura (lado propietario), manteniendo ambos lados sincronizados.
        factura.setCliente(this);
        
        // Devuelve la instancia actual para permitir llamadas encadenadas.
        return this;
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
                " | direcciones='" + direcciones + '\'' +
                " | factura='" + facturas + '\'';// Paso 5: Se añade la colección de facturas a la representación en String.
    }
}
