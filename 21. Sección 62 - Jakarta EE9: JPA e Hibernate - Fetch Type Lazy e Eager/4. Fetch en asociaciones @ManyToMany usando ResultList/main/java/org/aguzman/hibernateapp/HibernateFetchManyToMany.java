package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Alumno;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class HibernateFetchManyToMany {
    public static void main(String[] args) {
        // Paso 1: Obtener una instancia del EntityManager para interactuar con la base de datos.
        EntityManager em = JpaUtil.getEntityManager();

        // Paso 2: Crear una consulta JPQL personalizada con JOIN FETCH.
        List<Alumno> alumnos = em.createQuery("select distinct a from Alumno a left outer join fetch a.cursos", Alumno.class).getResultList();
        
        // Paso 3: Iterar sobre la lista de alumnos e imprimir sus nombres y cursos.
        alumnos.forEach(a -> System.out.println(a.getNombre() + " | cursos: " + a.getCursos()));
        
        // Paso 4: Cerrar el EntityManager para liberar recursos.
        em.close();
    }
}