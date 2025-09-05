package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;

public class HibernateFetchLazyOneToManyJoinFetch {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        Cliente cliente = em.createQuery("select c from Cliente c left outer join fetch c.direcciones left join fetch c.detalle where c.id=:id", Cliente.class)
                .setParameter("id",1L)
                .getSingleResult();

        System.out.println("Nombre: "+ cliente.getNombre() + " | Dirección: " + cliente.getDirecciones());
        System.out.println("Nombre: "+ cliente.getNombre() + " | Detalle: " + cliente.getDetalle());
        em.close();
    }
}
