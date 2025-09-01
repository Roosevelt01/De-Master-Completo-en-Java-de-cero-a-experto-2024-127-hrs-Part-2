package org.aguzman.hibernateapp.entity;
import javax.persistence.*;

@Entity
@Table(name="cursos")
public class Curso {

    @Id // Marca el campo 'id' como la clave primaria de la entidad.
    // Configura la estrategia para la generación automática del valor de la clave primaria. En este caso, IDENTITY 
    // utiliza una columna de identidad de la base de datos (como el AUTO_INCREMENT de MySQL), lo que delega la 
    // generación del ID a la base de datos.
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id; // Declara el campo 'id' de tipo Long para almacenar el identificador único de la entidad.

    private String titulo;
    private String profesor;

    // Constructores, Getters, Setters y toString()
    public Curso(String titulo, String profesor) {
        this.titulo = titulo;
        this.profesor = profesor;
    }

    public Curso() {
    }

    // ... getters y setters ...

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }

    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getProfesor() { return profesor; }

    public void setProfesor(String profesor) { this.profesor = profesor; }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", profesor='" + profesor + '\'' +
                '}';
    }
}
