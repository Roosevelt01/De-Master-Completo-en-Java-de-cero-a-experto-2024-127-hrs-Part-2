package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.entity.Factura;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;

public class HibernateAsociacionesManyToOne {
    public static void main(String[] args) {
        // Paso 1: Obtiene el EntityManager para interactuar con la base de datos.
        EntityManager em = JpaUtil.getEntityManager();

        try{
            // Paso 2: Inicia una nueva transacción. Todas las operaciones de escritura deben estar dentro de una.
            em.getTransaction().begin();
            
            // Paso 3: Crear y persistir un nuevo cliente
            Cliente cliente = new Cliente("Cara", "Edu");
            cliente.setFormaPago("credito");            
            em.persist(cliente);

            //Paso 4: Crear una nueva factura y asociarla al cliente
            Factura factura = new Factura("compras de oficina", 1000L);
            factura.setCliente(cliente);// Establece la relación

            em.persist(factura);
            
            //Paso 5: Imprimir el resultado
            System.out.println("\n"+factura);
            System.out.println("\n"+factura.getCliente());
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally{
            em.close();
        }
    }
}
