package org.aguzman.hibernateapp.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "alumnos")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "tbl_alumnos_cursos", joinColumns = @JoinColumn(name="alumno_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"alumno_id", "curso_id"}))
    private List<Curso> cursos;

    public Alumno() {
        this.cursos = new ArrayList<>();
    }

    public Alumno(String nombre, String apellido) {
        this();
        this.nombre = nombre;
        this.apellido = apellido;
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

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    // Paso 1: Método de conveniencia para añadir un Curso a la lista del Alumno.
    // Sincroniza la relación bidireccional, añadiendo también el Alumno a la lista de Alumnos del Curso.
    public void addCurso(Curso curso){
        this.cursos.add(curso);
        curso.getAlumnos().add(this);
    }

    // Paso 2: Método de conveniencia para remover un Curso de la lista del Alumno.
    // Sincroniza la eliminación en ambas direcciones, removiendo también el Alumno de la lista de Alumnos del Curso.
    public void removeCurso(Curso curso){
        this.cursos.remove(curso);
        curso.getAlumnos().remove(this);
    }

    // Paso 3: Sobrescribe el método equals() para comparar objetos Alumno.
    // La comparación se basa únicamente en el 'id' para que la búsqueda y
    // eliminación de objetos en colecciones sea precisa.
    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Alumno alumno = (Alumno) object;
        return Objects.equals(id, alumno.id);
    }

    // Paso 4: Sobrescribe el método hashCode() para asegurar que el comportamiento de equals() sea consistente.
    // Se basa en el 'id' para generar un código hash único.
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", cursos=" + cursos +
                '}';
    }
}
