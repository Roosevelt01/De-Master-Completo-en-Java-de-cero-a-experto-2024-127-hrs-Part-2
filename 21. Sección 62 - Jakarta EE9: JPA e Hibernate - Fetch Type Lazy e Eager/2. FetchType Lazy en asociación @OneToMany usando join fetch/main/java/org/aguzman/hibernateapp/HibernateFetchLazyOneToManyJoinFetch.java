package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;

public class HibernateFetchLazyOneToManyJoinFetch {
    public static void main(String[] args) {
        // Paso 1: Obtener el EntityManager.
        EntityManager em = JpaUtil.getEntityManager();

        // Paso 2: Crear y ejecutar la consulta JPQL personalizada con JOIN FETCH.
        Cliente cliente = em.createQuery("select c from Cliente c left outer join fetch c.direcciones left join fetch c.detalle where c.id=:id", Cliente.class)
                .setParameter("id",1L)
                .getSingleResult();

        // Paso 3: Imprimir los datos para verificar la carga.                
        System.out.println("Nombre: "+ cliente.getNombre() + " | Dirección: " + cliente.getDirecciones());
        System.out.println("Nombre: "+ cliente.getNombre() + " | Detalle: " + cliente.getDetalle());
        
        // Paso 4: Cerrar el EntityManager.
        em.close();
    }
}