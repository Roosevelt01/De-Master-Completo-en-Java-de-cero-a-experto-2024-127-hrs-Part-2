package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.entity.Direccion;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;

public class HibernateAsociacionesOneToManyFind {
    public static void main(String[] args) {

        // Obtiene el EntityManager para interactuar con la base de datos.   
        EntityManager em = JpaUtil.getEntityManager();

        try{
            // Inicia una nueva transacción, necesaria para cualquier operación de escritura.
            em.getTransaction().begin();

            // Utiliza em.find() para buscar y recuperar el Cliente con el ID=2 desde la base de datos.
            Cliente cliente = em.find(Cliente.class, 2L);

            // Crea dos nuevas instancias de Direccion en memoria.
            Direccion d1 = new Direccion("el vergel", 123);
            Direccion d2 = new Direccion("vasco de gama", 456);

            // Accede a la colección de direcciones del objeto 'cliente' gestionado.
            cliente.getDirecciones().add(d1);
            // Añade las nuevas direcciones a la lista.
            cliente.getDirecciones().add(d2);
            
            // Se llama a em.merge() para sincronizar el estado del objeto 'cliente' con la base de datos.
            em.merge(cliente);

            // Confirma la transacción, haciendo que los cambios (los INSERTs y UPDATEs)
            // se ejecuten permanentemente en la base de datos.
            em.getTransaction().commit();
        }catch (Exception e){
            // Si ocurre un error, revierte todos los cambios hechos en la transacción.
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            // Este bloque se ejecuta siempre, asegurando que el EntityManager se cierre
            // para liberar la conexión y los recursos.
            em.close();
        }
    }
}
