package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Alumno;
import org.aguzman.hibernateapp.entity.Curso;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;

public class HibernateAsociacionesManyToManyFind {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            // 1. Buscar entidades existentes por su ID
            Alumno alumno1 = em.find(Alumno.class, 1L);
            Alumno alumno2 = em.find(Alumno.class, 2L);

            Curso curso1 = em.find(Curso.class, 1L);// new Curso("Curso Java", "Andres");
            Curso curso2 = em.find(Curso.class, 2L);// new Curso("Curso Hibernate y Jpa", "Andres");
            
            // 2. Crear las asociaciones en memoria
            alumno1.getCursos().add(curso1);
            alumno1.getCursos().add(curso2);
            alumno2.getCursos().add(curso1);

            // 3. Confirmar la transacción
            em.getTransaction().commit();

            System.out.println(alumno1);
            System.out.println(alumno2);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
