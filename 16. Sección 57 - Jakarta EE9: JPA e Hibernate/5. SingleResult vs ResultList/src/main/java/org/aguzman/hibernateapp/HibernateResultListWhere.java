package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class HibernateResultListWhere {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        EntityManager em = JpaUtil.getEntityManager();
        Query query = em.createQuery("select c from Cliente c where c.formaPago=?1", Cliente.class);
        System.out.println("Ingresa una forma de pago: ");
        String pago = s.next();
        // 1. Limitar el resultado a 1 para evitar NonUniqueResultException
        query.setParameter(1, pago);
        List<Cliente> clientes = query.getResultList();
        System.out.println(clientes);
        em.close();
    }
}