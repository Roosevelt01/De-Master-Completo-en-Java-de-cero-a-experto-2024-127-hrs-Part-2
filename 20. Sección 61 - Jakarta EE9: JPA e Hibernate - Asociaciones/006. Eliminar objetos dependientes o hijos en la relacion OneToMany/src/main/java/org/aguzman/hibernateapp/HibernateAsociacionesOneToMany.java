package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.entity.Direccion;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;

public class HibernateAsociacionesOneToMany {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        try{
            em.getTransaction().begin();

            Cliente cliente = new Cliente("Cata", "Edu");
            cliente.setFormaPago("mercado pago");

            Direccion d1 = new Direccion("el vergel", 123);
            Direccion d2 = new Direccion("vasco de gama", 456);

            cliente.getDirecciones().add(d1);
            cliente.getDirecciones().add(d2);
            em.persist(cliente);

            em.getTransaction().commit();

            // --- Primera Transacción: Eliminación de la dirección 'd1' ---
            System.out.println("\n"+cliente);
            em.getTransaction().begin();
            cliente = em.find(Cliente.class, cliente.getId());
            cliente.getDirecciones().remove(d1);
            em.getTransaction().commit();

            System.out.println("\n"+cliente);// Imprime el cliente con la dirección eliminada

            // --- Segunda Transacción: Intento de eliminar 'd1' desde el estado 'detached' ---
            em.getTransaction().begin();
            // d1 es un objeto 'detached', ya que proviene de una transacción anterior.
            // Intentar eliminarlo directamente podría no funcionar como se espera.
            d1 = em.find(Direccion.class, 1L);
            cliente.getDirecciones().remove(d1);
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