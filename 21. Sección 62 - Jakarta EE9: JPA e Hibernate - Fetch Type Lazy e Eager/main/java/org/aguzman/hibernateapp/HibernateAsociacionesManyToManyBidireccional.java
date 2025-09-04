package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Alumno;
import org.aguzman.hibernateapp.entity.Curso;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;

public class HibernateAsociacionesManyToManyBidireccional {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        try{
            em.getTransaction().begin();

            Alumno alumno1 = new Alumno("Cata", "Edu");
            Alumno alumno2 = new Alumno("Jano", "Fernan");

            Curso curso1 = new Curso("Curso Java", "Andres");
            Curso curso2 = new Curso("Curso Hibernate y JPA", "Andres");

            alumno1.addCurso(curso1);// Usa el método de conveniencia para añadir y sincronizar la relación
            alumno1.addCurso(curso2);
            alumno2.addCurso(curso1);

            em.persist(alumno1);// Persiste el alumno y, en cascada, sus cursos
            em.persist(alumno2);

            em.getTransaction().commit();
            System.out.println(alumno1);
            System.out.println(alumno2);

            // Bloque de código para la eliminación
            em.getTransaction().begin();
            Curso c2 = new Curso("Curso Java", "Andres");
            c2.setId(3L);
            alumno1.getCursos().remove(c2);
            em.getTransaction().commit();
            System.out.println(alumno1);
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
    }
}
