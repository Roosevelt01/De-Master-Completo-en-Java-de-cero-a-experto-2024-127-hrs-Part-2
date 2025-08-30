package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.entity.Direccion;
import org.aguzman.hibernateapp.entity.Factura;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;

public class HibernateAsociacionesOneToManyBidireccional {
    public static void main(String[] args) {
        // Obtiene el EntityManager, nuestra interfaz para interactuar con el contexto de persistencia.
        EntityManager em = JpaUtil.getEntityManager();

        try{
            // Inicia una nueva transacción. Todas las operaciones de escritura deben estar dentro de una.
            em.getTransaction().begin();

            // Crea un nuevo Cliente.
            Cliente cliente = new Cliente("Cata", "Edu");
            cliente.setFormaPago("paypal");

            // Crea dos nuevas Facturas.
            Factura f1 = new Factura("compras de supermercado", 5000L);
            Factura f2 = new Factura("compras de tecnología", 7000L);
            
            
            /* cliente.getFacturas().add(f1);
            cliente.getFacturas().add(f2);

            f1.setCliente(cliente);
            f2.setCliente(cliente);*/

            //Alternativa

            // Usa el método ayudante para añadir las facturas.
            // Esto sincroniza ambos lados de la relación de forma segura.
            cliente.addFactura(f1)
                   .addFactura(f2);

            // Persiste únicamente el cliente. La cascada se encarga de las facturas.
            em.persist(cliente);

            // Confirma la transacción, escribiendo los cambios (los 3 INSERTs) en la base de datos.
            em.getTransaction().commit();
            // Imprime el estado final del objeto cliente y sus facturas asociadas.
            System.out.println(cliente);
        }catch (Exception e){
            // Si ocurre un error, revierte la transacción.
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            // Cierra el EntityManager para liberar la conexión y los recursos.
            em.close(); 
        }
    }
}