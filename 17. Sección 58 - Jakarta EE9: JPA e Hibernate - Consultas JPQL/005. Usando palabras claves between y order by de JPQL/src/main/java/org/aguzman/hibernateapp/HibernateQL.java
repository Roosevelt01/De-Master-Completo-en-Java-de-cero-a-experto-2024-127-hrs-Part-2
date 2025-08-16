package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.dominio.ClienteDto;
import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class HibernateQL {
    public static void main(String[] args) {
        // ... (consultas anteriores) ...
        
        //Paso 1
        System.out.println("\n============ Consulta por rangos ============");
        //clientes = em.createQuery("select c from Cliente c where c.id between 2 and 5", Cliente.class).getResultList();
        clientes = em.createQuery("select c from Cliente c where c.nombre between 'J' and 'Q'", Cliente.class).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("\n============ Consulta con orden ============");
        //clientes = em.createQuery("select c from Cliente c order by c.nombre asc", Cliente.class).getResultList();
        //clientes = em.createQuery("select c from Cliente c order by c.nombre desc", Cliente.class).getResultList();
        //clientes = em.createQuery("select c from Cliente c order by c.nombre desc, c. apellido desc", Cliente.class).getResultList();
        clientes = em.createQuery("select c from Cliente c order by c.nombre asc, c. apellido desc", Cliente.class).getResultList();
        clientes.forEach(System.out::println);

        em.close();
    }
}

































