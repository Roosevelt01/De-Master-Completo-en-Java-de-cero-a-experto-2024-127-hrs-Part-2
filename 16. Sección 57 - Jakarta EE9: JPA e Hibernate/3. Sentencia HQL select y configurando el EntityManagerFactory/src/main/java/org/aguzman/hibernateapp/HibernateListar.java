package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class HibernateListar {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        List<Cliente> clientes = em.createQuery("select c  from Cliente c").getResultList();
        clientes.forEach(System.out::println);
    }
}
