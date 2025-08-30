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

            // Utiliza em.find() para buscar y recuperar el Cliente con ID=1 de la base de datos.
            Cliente cliente = em.find(Cliente.class, 1L);
            // Modifica un atributo simple del cliente encontrado.
            cliente.setFormaPago("paypal");

            // Crea dos nuevas instancias de Factura en memoria.
            Factura f1 = new Factura("compras de supermercado", 5000L);
            Factura f2 = new Factura("compras de tecnología", 7000L);

            // Utiliza el método ayudante 'addFactura' para añadir las nuevas facturas al cliente.
            // El método encadenado (.addFactura(...).addFactura(...)) es posible porque devuelve 'this'.
            cliente.addFactura(f1)
                   .addFactura(f2);

            // Se llama a em.persist(cliente) en la transcripción, aunque em.merge(cliente) también sería válido.
            em.persist(cliente);
            
            // Confirma la transacción. En este punto, JPA ejecuta el SQL necesario.
            em.getTransaction().commit();
            // Imprime el estado final del cliente para verificar los cambios.
            System.out.println(cliente);
        }catch (Exception e){
            // Si hay un error, revierte todos los cambios de la transacción.
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            // Este bloque se ejecuta siempre, asegurando que el EntityManager se cierre.
            em.close();
        }
    }
}

// 1. Resultado de em.find(Cliente.class, 1L)
Hibernate: select cliente0_.id as id1_0_0_, ... from clientes cliente0_ where cliente0_.id=?
// 2. Resultado de em.persist(cliente) con las nuevas facturas (del commit)
Hibernate: insert into facturar (id_cliente, descripción, total) values (?, ?, ?)
Hibernate: insert into facturar (id_cliente, descripción, total) values (?, ?, ?)
// 3. Resultado de la modificación cliente.setFormaPago("paypal")
Hibernate: update clientes set apellido=?, ..., forma_pago=?, nombre=? where id=?
// 4. Resultado de la llamada a System.out.println(cliente), que accede a las colecciones
Hibernate: select direccione0_... from tbl_clientes_direcciones direccione0_ ... where direccione0_.id_cliente=?
Hibernate: select facturas0_... from facturar facturas0_ where facturas0_.id_cliente=?

