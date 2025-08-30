package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.entity.Factura;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;

public class HibernateAsociacionesOneToManyBidireccionalFind {
    public static void main(String[] args) {
        // Obtiene el EntityManager, nuestra puerta de enlace para las operaciones con la base de datos.
        EntityManager em = JpaUtil.getEntityManager();
        try {
            // Inicia una nueva transacción, necesaria para cualquier operación de lectura y escritura.
            em.getTransaction().begin();

            Cliente cliente = em.find(Cliente.class, 1L);
            cliente.setFormaPago("paypal");

            Factura f1 = new Factura("compras de supermercado", 5000L);
            Factura f2 = new Factura("compras de tecnología", 7000L);

            cliente.addFactura(f1)
                   .addFactura(f2);

            em.persist(cliente);
            em.getTransaction().commit();
            System.out.println(cliente);
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
    }
}
