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
        System.out.println("\n============ Consulta con nombre y apellido concatenados ============");
        //nombres = em.createQuery("select concat(c.nombre, ' ', c.apellido) as nombreCompleto from Cliente c", String.class)
        //              .getResultList();

        //Alternativa
        nombres = em.createQuery("select c.nombre || ' ' || c.apellido as nombreCompleto from Cliente c", String.class)
                .getResultList();

        nombres.forEach(System.out::println);

        //Paso 2
        System.out.println("\n============ Consulta con nombre y apellido concatenados en maýúsculas ============");
        nombres = em.createQuery("select upper(concat(c.nombre, ' ', c.apellido)) as nombreCompleto from Cliente c", String.class)
                      .getResultList();

        nombres.forEach(System.out::println);

        //Paso 3
        System.out.println("\n============ Consulta con nombre y apellido concatenados en minúsculas ============");
        nombres = em.createQuery("select lower(concat(c.nombre, ' ', c.apellido)) as nombreCompleto from Cliente c", String.class)
                .getResultList();

        nombres.forEach(System.out::println);

        //Paso 4
        System.out.println("\n============ Consulta para buscar por nombre ============");
        String param= "and";
        clientes = em.createQuery("select c from Cliente c where c.nombre like :parametro", Cliente.class)
                        .setParameter("parametro", "%" + param + "%")
                                .getResultList();
        clientes.forEach(System.out::println);

        em.close();

    }
}

.
└── src
    └── main
        ├── java
        │   └── org
        │       └── aguzman
        │           └── hibernateapp
        │               └── ... (paquetes existentes)
        │               └── HibernateQL.java      // Modificado
        └── resources
            └── META-INF
                └── persistence.xml