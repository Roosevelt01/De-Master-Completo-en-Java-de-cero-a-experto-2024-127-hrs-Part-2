package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.entity.ClienteDetalle;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;

public class HibernateAsociacionesOneToOneFind {
    public static void main(String[] args) {
        
        EntityManager em = JpaUtil.getEntityManager();

        try{
            em.getTransaction().begin();

            // 1. Buscar un cliente existente
            Cliente cliente = em.find(Cliente.class, 2L);

            // 2. Crear y persistir un nuevo detalle
            ClienteDetalle detalle = new ClienteDetalle(true, 5000L);
            em.persist(detalle);

            // 3. Asignar el detalle al cliente encontrado
            cliente.setDetalle(detalle);

            em.getTransaction().commit();
            System.out.println("\n"+cliente);
        }catch (Exception e){
            em.getTransaction().rollback();
        }finally {
            em.close();
        }

    }
}
