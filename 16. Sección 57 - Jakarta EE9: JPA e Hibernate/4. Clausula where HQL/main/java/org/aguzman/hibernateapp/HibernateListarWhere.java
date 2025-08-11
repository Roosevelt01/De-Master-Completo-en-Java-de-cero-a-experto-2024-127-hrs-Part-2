package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Scanner;

public class HibernateListarWhere {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        // 1. Obtener el EntityManager
        EntityManager em = JpaUtil.getEntityManager();
        
        // 2. Crear la consulta HQL con un parámetro posicional
        Query query = em.createQuery("select c from Cliente c where c.formaPago=?1", Cliente.class);

        System.out.println("Ingresa una forma de pago: ");
        String pago = s.next();
        
        // 3. Asignar el valor al parámetro de la consulta
        query.setParameter(1, pago);
       
        // 4. Ejecutar la consulta y obtener un único resultado
        Cliente c = (Cliente) query.getSingleResult();
        
        
        System.out.println(c);
        
        // 5. Cerrar el EntityManager
        em.close();
    }
}