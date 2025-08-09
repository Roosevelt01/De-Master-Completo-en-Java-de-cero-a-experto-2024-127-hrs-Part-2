package org.aguzman.hibernateapp.entity;

import javax.persistence.*;

// Paso 1: Marca esta clase como una Entidad JPA, indicando que representa una tabla.
@Entity
Paso 2: @Table(name="clientes")
// Especifica que esta clase se mapea a la tabla llamada "clientes".
// Es necesario porque el nombre de la clase ("Cliente") no es igual al de la tabla ("clientes").
@Table(name="clientes")
public class Cliente {
    @Id // Paso 3: Marca este atributo como la clave primaria (Primary Key) de la tabla.
    // Paso 4: Le dice a JPA cómo se genera el valor del ID. IDENTITY se usa para columnas
    // auto-incrementales, como en MySQL. JPA delega la generación del ID a la base de datos.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;

    // Paso 5: Mapea el atributo "formaPago" (camelCase en Java) a la columna "forma_pago"
    // (snake_case en la BD). Es necesario cuando los nombres no coinciden.
    @Column(name = "forma_pago")//PAso 5
    private String formaPago;

    // Requisito de JPA: un constructor vacío para que pueda crear instancias.    
    public Cliente() {}

    // Constructor con parámetros para conveniencia.
    public Cliente(Long id, String nombre, String apellido, String formaPago) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.formaPago = formaPago;
    }

    // Getters y Setters
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getApellido() {return apellido;}
    public void setApellido(String apellido) {this.apellido = apellido;}
    public String getFormaPago() {return formaPago;}
    public void setFormaPago(String formaPago) {this.formaPago = formaPago;}

    //El método toString() se sobrescribe para una representación legible de la entidad,
    //útil para la depuración.    
    @Override
    public String toString() {
        return  "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", formaPago='" + formaPago;
    }
}