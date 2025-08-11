package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class HibernateListar {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        // Se añade Cliente.class para especificar el tipo de resultado.
        List<Cliente> clientes = em.createQuery("select c  from Cliente c", Cliente.class).getResultList();
        clientes.forEach(System.out::println);
        em.close();
    }
}