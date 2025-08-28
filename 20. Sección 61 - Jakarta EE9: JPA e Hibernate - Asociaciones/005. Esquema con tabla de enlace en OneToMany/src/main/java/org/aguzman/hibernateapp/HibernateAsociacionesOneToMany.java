package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.entity.Direccion;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;

public class HibernateAsociacionesOneToMany {
    public static void main(String[] args) {
        // Obtiene el EntityManager para interactuar con la base de datos.
        EntityManager em = JpaUtil.getEntityManager();

        try{
            // Inicia una nueva transacción.
            em.getTransaction().begin();

            // Crea una nueva instancia de Cliente.
            Cliente cliente = new Cliente("Cata", "Edu");
            // Establece un atributo simple del cliente.
            cliente.setFormaPago("mercado pago");
            
            // Crea dos nuevas instancias de Direccion.
            Direccion d1 = new Direccion("el vergel", 123);
            Direccion d2 = new Direccion("vasco de gama", 456);

            // ¡Paso clave! Añade las nuevas direcciones a la lista del cliente.
            // Se accede a la lista (inicializada en el constructor de Cliente) y se añaden los objetos.
            cliente.getDirecciones().add(d1);
            cliente.getDirecciones().add(d2);

            // Se persiste ÚNICAMENTE el objeto 'cliente'.
            // Gracias a 'cascade = CascadeType.ALL', Hibernate automáticamente
            // persistirá también los objetos 'd1' y 'd2' y establecerá la llave foránea.
            em.persist(cliente);

            // Confirma la transacción, ejecutando los INSERTs en la base de datos.
            em.getTransaction().commit();
        }catch (Exception e){
            // Si hay un error, revierte la transacción.
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            // Cierra el EntityManager para liberar recursos.
            em.close();
        }
    }
}
