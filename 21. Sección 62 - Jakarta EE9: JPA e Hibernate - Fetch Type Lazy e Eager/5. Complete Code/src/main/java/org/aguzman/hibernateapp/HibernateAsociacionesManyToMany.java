package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Alumno;
import org.aguzman.hibernateapp.entity.Curso;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;

public class HibernateAsociacionesManyToMany {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        try{
            em.getTransaction().begin();

            Alumno alumno1 = new Alumno("Cata", "Edu");
            Alumno alumno2 = new Alumno("Jano", "Fernan");

            Curso curso1 = new Curso("Curso Java", "Andres");
            Curso curso2 = new Curso("Curso Hibernate y JPA", "Andres");

            alumno1.getCursos().add(curso1);
            alumno1.getCursos().add(curso2);

            alumno2.getCursos().add(curso1);

            em.persist(alumno1);
            em.persist(alumno2);
            em.getTransaction().commit();

            System.out.println(alumno1);
            System.out.println(alumno2);

            //Se agrega nuevos códigos
            em.getTransaction().begin(); //Paso 1
            Curso c2 = em.find(Curso.class, 3L); //Paso 2
            alumno1.getCursos().remove(c2); //Paso 3
            em.getTransaction().commit(); //Paso 4
            System.out.println(alumno1); //Paso 5
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
    }
}
