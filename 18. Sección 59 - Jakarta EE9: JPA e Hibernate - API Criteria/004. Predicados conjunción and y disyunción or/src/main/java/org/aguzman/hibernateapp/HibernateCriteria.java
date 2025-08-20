package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;

public class HibernateCriteria {
    public static void main(String[] args) {
        //Resto del código

        // --- Ejemplo 1: Operador `IN` ---
        System.out.println("\n========== Consulta where in ==========");
        // Paso 1: // Se crea una nueva consulta para este ejemplo.
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        
        //query.select(from).where(from.get("nombre").in("Andres", "John", "Lou"));

        // Paso 2: Utilizando `Arrays.asList` para pasar una lista de valores directamente.
        // Se busca a los clientes cuyo nombre esté en la lista ("Andres", "John", "Lou").
        query.select(from).where(from.get("nombre").in(Arrays.asList("Andres", "John", "Lou")));//Alternativ
        
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        // --- (Alternativa con parámetros): `IN` con `ParameterExpression` ---
        // Paso 1: Se reinician los objetos 'query' y 'from' para una nueva consulta.
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        // Paso 2: Se crea un ParameterExpression cuyo tipo es una Lista (List.class).
        // Esto representará el conjunto de valores para la cláusula IN.
        ParameterExpression<List> listParam = criteria.parameter(List.class, "nombres");

        // Paso 3: Se construye la consulta. El predicado se crea llamando al método '.in()'
        // sobre la expresión del atributo (from.get("nombre")).
        query.select(from).where(from.get("nombre").in(listParam));
        
        // Paso 4: Se ejecuta la consulta.
        clientes = em.createQuery(query)
                // Se asigna una lista de nombres reales al parámetro 'nombres'.
                .setParameter("nombres", Arrays.asList("Andres", "John", "Lou"))
                // Se obtiene el resultado.
                .getResultList();
        // Paso 5: Se imprimen los clientes encontrados.
        clientes.forEach(System.out::println);

         // --- Ejemplo 2: Operador `GE` (Mayor o Igual Que) ---
        System.out.println("\n========== Filtrar con 'ge' (Mayor o Igual Que) ==========");

        // Paso 6. Se reinician los objetos de consulta.
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        // Paso 7: // Se construye la consulta con la cláusula WHERE.
        // criteria.ge() crea el predicado "mayor o igual que".
        query.select(from).where(criteria.ge(from.get("id"), 3L ));

        // Paso 8: Se ejecuta la consulta.
        clientes = em.createQuery(query).getResultList();

        // Paso 8: Se imprimen los resultados.
        clientes.forEach(System.out::println);

        // --- Ejemplo 3: Operador `GT` (Mayor Que) con `length` ---
        System.out.println("\n========== Filtrar usando gt (>) con una función anidada ==========");
        // Paso 1: Se reinician los objetos de consulta.
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        // Paso 2: Se construye la consulta con la cláusula WHERE.
        // criteria.gt() crea el predicado "mayor que".
        query.select(from).where(
            // El primer argumento es una expresión: el largo del atributo 'nombre'.
            criteria.gt(criteria.length(from.get("nombre")),
            // El segundo argumento es el valor a comparar: 5.
            5L ));

        // Paso 3: Se ejecuta la consulta y se imprimen los resultados.
        clientes = em.createQuery(query).getResultList();        
        clientes.forEach(System.out::println);

        // --- Ejemplo 4: Operador `LE` (Menor o Igual Que) ---
        System.out.println("\n========== Filtrar usando le (<=) ==========");
        // Paso 1: Se reinician los objetos de consulta.        
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        
        // Paso 2: criteria.le() crea el predicado "menor o igual que".
        query.select(from).where(criteria.le(from.get("id"), 2L ));        
        
        clientes = em.createQuery(query).getResultList();        
        clientes.forEach(System.out::println);

        // --- Ejemplo 5: Operador `LT` (Menor Que) ---
        System.out.println("\n========== Filtrar con lt ==========");
        // Paso 1: Se reinician los objetos de consulta.
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        //Paso 2: criteria.lt() crea el predicado "menor que".
        query.select(from).where(criteria.lt(from.get("id"), 2L ));
        
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        em.close();
    }
}