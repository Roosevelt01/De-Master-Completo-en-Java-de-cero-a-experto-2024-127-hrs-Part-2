package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.entity.ClienteDetalle;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;

public class HibernateAsociacionesOneToOne {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        try{
            em.getTransaction().begin();

            // 1. Crear y persistir la entidad principal
            Cliente cliente = new Cliente("Cata", "Edu");
            cliente.setFormaPago("paypal");
            em.persist(cliente);

            // 2. Crear y persistir la entidad de detalle
            ClienteDetalle detalle = new ClienteDetalle(true, 5000L);
            em.persist(detalle);

            // 3. Establecer la relación en el objeto dueño
            cliente.setDetalle(detalle);
            
            // 4. Confirmar la transacción
            em.getTransaction().commit();

            System.out.println("\n"+cliente);
        }catch (Exception e){
            em.getTransaction().rollback();
        }finally {
            em.close();
        }

    }
}
