package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.entity.Direccion;
import org.aguzman.hibernateapp.entity.Factura;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;

public class HibernateAsociacionesOneToManyBidireccional {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        try{
            em.getTransaction().begin();

            Cliente cliente = new Cliente("Cata", "Edu");
            cliente.setFormaPago("paypal");

            Factura f1 = new Factura("compras de supermercado", 5000L);
            Factura f2 = new Factura("compras de tecnología", 7000L);
            /* cliente.getFacturas().add(f1);
            cliente.getFacturas().add(f2);

            f1.setCliente(cliente);
            f2.setCliente(cliente);*/

            //Alternativa
            cliente.addFactura(f1)
                   .addFactura(f2);

            em.persist(cliente);
            em.getTransaction().commit();

            // Inicia una SEGUNDA transacción para la operación de eliminación.
            em.getTransaction().begin();

            // --- Estrategia de Eliminación con Objeto Detached ---
            // 1. Se crea una nueva instancia de Factura en memoria (objeto detached).
            Factura f3 = new Factura("compras de supermercado", 5000L);
            
            // 2. Se le asigna el ID de la factura que queremos eliminar de la base de datos.
            f3.setId(1L); // El ID de la primera factura creada.

            // 3. Se llama al método ayudante para eliminar la factura.
            //    Esto funciona porque la clase Factura ahora tiene un método equals() bien definido.
            //    La lista buscará y encontrará el objeto gestionado que es "igual" a f3 y lo removerá.
            cliente.removeFactura(f3);
            
            // 4. Confirma la transacción.
            em.getTransaction().commit();

            // Imprime el estado final del cliente, ahora con una sola factura.
            System.out.println(cliente);
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
    }
}