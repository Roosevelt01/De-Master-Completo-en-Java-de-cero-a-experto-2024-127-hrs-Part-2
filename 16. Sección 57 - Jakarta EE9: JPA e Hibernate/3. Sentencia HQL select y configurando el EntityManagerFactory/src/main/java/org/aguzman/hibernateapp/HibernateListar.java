package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class HibernateListar {
    public static void main(String[] args) {
        // 1. Obtener una instancia del EntityManager
        EntityManager em = JpaUtil.getEntityManager();
        // 2. Crear y ejecutar la consulta HQL
        List<Cliente> clientes = em.createQuery("select c  from Cliente c").getResultList();
        // 3. Imprimir los resultados utilizando un método de referencia
        clientes.forEach(System.out::println);
        // 4. Cerrar el EntityManager
        em.close();
    }
}
