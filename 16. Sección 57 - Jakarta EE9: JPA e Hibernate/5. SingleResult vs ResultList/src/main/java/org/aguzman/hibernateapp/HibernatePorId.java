package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Scanner;

public class HibernatePorId {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.print("Ingrese el id: ");
        Long id = s.nextLong();
        EntityManager em = JpaUtil.getEntityManager();
        
        System.out.println("----- PRIMERA BÚSQUEDA -----");
        Cliente cliente = em.find(Cliente.class, id);// Paso 1: Se ejecuta una consulta SQL a la BD.
        System.out.println(cliente);

        System.out.println("----- SEGUNDA BÚSQUEDA -----");
        Cliente cliente2 = em.find(Cliente.class, id);
        System.out.println(cliente2);//Paso 2: NO se ejecuta SQL. El objeto se recupera de la caché.
        em.close();
    }
}