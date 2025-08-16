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

//========================================================================================================

1.1. BETWEEN con Tipos Numéricos

// Imprime un encabezado para esta sección en la consola.
System.out.println("\n============ Consulta por rangos numéricos ============");
// Reasigna a la variable 'clientes' el resultado de una nueva consulta JPQL.
clientes = em.createQuery("select c from Cliente c where c.id between 2 and 5", Cliente.class)
        // Ejecuta la consulta y devuelve una lista con los resultados.
        .getResultList();
// Itera sobre la lista de clientes encontrados y los imprime en la consola.
clientes.forEach(System.out::println);

Resultado en Consola:

Hibernate: select ... from clientes cliente0_ where cliente0_.id between 2 and 5
id=2, nombre='John', apellido='Doe', formaPago='credito
id=4, nombre='Pepa', apellido='Doe', formaPago='crédito
id=5, nombre='Luna', apellido='Garcia', formaPago='debito


































