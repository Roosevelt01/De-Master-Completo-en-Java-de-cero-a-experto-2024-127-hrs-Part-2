package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class HibernateFetchResultList {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        // "DISTINCT" previene clientes duplicados en la lista final.
        // "JOIN FETCH" carga las colecciones en la misma consulta, evitando el N+1.
        List<Cliente> clientes = em.createQuery("select distinct c from Cliente c left outer join fetch c.direcciones left outer join fetch c.detalle", Cliente.class).getResultList();

        clientes.forEach(c -> System.out.println(c.getNombre() + ", direcciones: "+ c.getDirecciones()));
        em.close();
    }
}