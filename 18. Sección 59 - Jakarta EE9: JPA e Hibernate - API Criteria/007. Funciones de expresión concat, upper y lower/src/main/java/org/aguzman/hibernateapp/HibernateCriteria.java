package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.Arrays;
import java.util.List;

public class HibernateCriteria {
    public static void main(String[] args) {
        //Resto del código

        // --- Ejemplo 1: Concatenar nombres y apellidos ---
        System.out.println("\n========== Consulta por nombres y apellidos concatenados ==========");
        queryString = criteria.createQuery(String.class);
        from = queryString.from(Cliente.class);

        queryString.select(criteria.concat(criteria.concat(from.get("nombre"), " "), from.get("apellido")));
        nombres = em.createQuery(queryString).getResultList();
        nombres.forEach(System.out::println);

        // --- Ejemplo 2: Concatenar y convertir a mayúsculas ---
        System.out.println("\n========== Consulta por nombres y apellidos concatenados en con upper ==========");
        queryString = criteria.createQuery(String.class);
        from = queryString.from(Cliente.class);

        queryString.select(criteria.upper(criteria.concat(criteria.concat(from.get("nombre"), " "), from.get("apellido"))));
        nombres = em.createQuery(queryString).getResultList();
        nombres.forEach(System.out::println);

        em.close();
    }
}
