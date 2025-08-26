package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.entity.Factura;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;

public class HibernateAsociacionesManyToOneFind {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        try{
           // Paso 1. Obtener un cliente existente por su ID
            Cliente cliente = em.find(Cliente.class, 1L);

            // Paso 2. Crear una nueva factura y asociarla al cliente encontrado
            Factura factura = new Factura("compras de oficina", 1000L);
            factura.setCliente(cliente);
            em.persist(factura);

            em.getTransaction().commit();
            
            // Paso 3. Imprimir el resultado
            System.out.println("\n" + factura.getCliente());

            // Paso 4: Confirma la transacción.
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally{
            em.close();
        }
    }
}
