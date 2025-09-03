package org.aguzman.hibernateapp.entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String profesor;

    // Paso 1: Mapea esta relación de muchos a muchos a la propiedad "cursos" en la clase Alumno.
    // Esto establece a 'Curso' como el lado "inverso" o "mapeado",
    //  indicando que la relación es gestionada por la clase Alumno.
    @ManyToMany(mappedBy = "cursos")
    // Paso 2: Se añade una lista de Alumnos para permitir la navegación
    // bidireccional, es decir, un Curso ahora sabe qué Alumnos están inscritos en él.
    private List<Alumno> alumnos;

    public Curso(String titulo, String profesor) {
        this();
        this.titulo = titulo;
        this.profesor = profesor;
    }

    public Curso() {
        // Paso 3: Se inicializa la lista de alumnos en el constructor para evitar NullPointerExceptions.
        this.alumnos = new ArrayList<>(); 
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    // Paso 4: Getter and setter de alumnos
    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    // Paso 5: Sobrescribe el método equals() para comparar objetos Curso.
    // La comparación se basa únicamente en el 'id' para que la búsqueda y eliminación de objetos en colecciones sea precisa.
    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Curso curso = (Curso) object;
        return Objects.equals(id, curso.id);
    }

    // Paso 6: Sobrescribe el método hashCode() para asegurar que el comportamiento de equals() sea consistente.
    // Se basa en el 'id' para generar un código hash único para cada objeto.
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", profesor='" + profesor + '\'' +
                '}';
    }
}
