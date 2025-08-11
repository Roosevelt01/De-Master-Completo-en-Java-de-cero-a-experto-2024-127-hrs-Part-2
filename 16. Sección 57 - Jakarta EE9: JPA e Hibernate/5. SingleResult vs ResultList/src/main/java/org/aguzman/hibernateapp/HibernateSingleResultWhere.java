package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Scanner;

public class HibernateSingleResultWhere {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        EntityManager em = JpaUtil.getEntityManager();
        Query query = em.createQuery("select c from Cliente c where c.formaPago=?1", Cliente.class);
        System.out.println("Ingresa una forma de pago: ");
        String pago = s.next();
        query.setParameter(1, pago);
        query.setMaxResults(1);// 1. Limitar el resultado a 1 para evitar NonUniqueResultException
        Cliente c = (Cliente) query.getSingleResult();
        System.out.println(c);
        em.close();
    }
}
