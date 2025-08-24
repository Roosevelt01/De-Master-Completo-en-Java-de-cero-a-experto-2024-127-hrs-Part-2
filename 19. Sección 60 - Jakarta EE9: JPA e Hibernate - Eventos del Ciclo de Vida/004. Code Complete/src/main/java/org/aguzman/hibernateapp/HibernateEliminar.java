package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class HibernateEliminar {
    public static void main(String[] args) {
        //Paso 1
        Scanner s = new Scanner(System.in);
        //Paso 2
        System.out.print("Ingrese el id del cliente a eliminar: ");
        //Paso 3
        Long id = s.nextLong();
        //Paso 4
        EntityManager em = JpaUtil.getEntityManager();

        try{
            //Paso 5
            Cliente cliente = em.find(Cliente.class, id);
            //Paso 6
            em.getTransaction().begin();
            //Paso 7
            em.remove(cliente);
            //Paso 8
            em.getTransaction().commit();
        }catch(Exception e){
            //Paso 9
            em.getTransaction().rollback();
            //Paso 10
            e.printStackTrace();
        }finally {
            //Paso 11
            em.close();
        }

    }
}
