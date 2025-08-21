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
        // Paso 1: Se crea una nueva CriteriaQuery cuyo resultado será de tipo String.
        queryString = criteria.createQuery(String.class);
        
        // Paso 2: Se establece el FROM en la entidad Cliente.
        from = queryString.from(Cliente.class);

        // Paso 3: Se define la selección de la consulta usando 'concat' de forma anidada.
        queryString.select(
                // La función 'concat' externa une el resultado de la interna con el apellido.
                criteria.concat(criteria.concat(from.get("nombre"), " "),
                // El segundo argumento de la 'concat' externa es el atributo 'apellido'.
                from.get("apellido")));

        // Paso 4: Se ejecuta la consulta y se obtiene una lista de Strings con los nombres completos.
        nombres = em.createQuery(queryString).getResultList();
        
        // Paso 5: Se imprimen los resultados en la consola.              
        nombres.forEach(System.out::println);

        // --- Ejemplo 2: Concatenar y convertir a mayúsculas ---
        System.out.println("\n========== Consulta por nombres y apellidos concatenados en con upper ==========");
        // Paso 1: Se crea una nueva CriteriaQuery cuyo resultado será de tipo String.
        queryString = criteria.createQuery(String.class);
        // Paso 2: Se establece el FROM en la entidad Cliente.
        from = queryString.from(Cliente.class);

        // Paso 3: Se define la selección, envolviendo toda la expresión de 'concat' anidada dentro de 'upper'.
        queryString.select
        
                (criteria.upper // La función 'upper' externa convierte todo a mayúsculas.
                        (criteria.concat(criteria.concat(from.get("nombre"), " "),
                        from.get("apellido"))));

        // Paso 4: Se ejecuta la consulta y se obtiene una lista de Strings con los nombres completos.
        nombres = em.createQuery(queryString).getResultList();
        
        // Paso 5: Se imprimen los resultados en la consola.              
        nombres.forEach(System.out::println);

        em.close();
    }
}