package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.Arrays;
import java.util.List;

public class HibernateCriteria {
    public static void main(String[] args) {
        //El resto del código

        // --- Ejemplo 1: Conjunción 'AND' ---
        System.out.println("\n========== Consulta con los predicados conjunción(and) ==========");
        // Paso 1: Se reinician los objetos de consulta para un nuevo query.
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        
        // Paso 2: Se crea el primer predicado para la condición nombre = 'Andres'.
        Predicate porNombre = criteria.equal(from.get("nombre"), "Andres");
        // Paso 3: Se crea el segundo predicado para la condición formaPago = 'debito'.
        Predicate porFormaPago = criteria.equal(from.get("formaPago"), "debito");
        
        // Paso 4: Se construye la consulta. La cláusula WHERE combina los dos predicados usando criteria.and().
        query.select(from).where(criteria.and(porNombre, porFormaPago));
        
        // Paso 5: Se ejecuta la consulta y se imprimen los resultados.
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        // --- Ejemplo 2: Disyunción 'OR' ---
        System.out.println("\n========== Consulta con los predicados disyunsión(OR) ==========");
        // Paso 1: Se reinician los objetos de consulta.
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        
        // Paso 2 Se definen los predicados individuales (se pueden reutilizar los nombres de las variables
        // si se desea, aquí se usan nuevos para mayor claridad).
        Predicate porNombre2 = criteria.equal(from.get("nombre"), "Andres");
        Predicate porFormaPago2 = criteria.equal(from.get("formaPago"), "debito");

        // Paso 3: Se construye la consulta, esta vez combinando los predicados con criteria.or().
        query.select(from).where(criteria.or(porNombre2, porFormaPago2));
        
        // Paso 4: Se ejecuta y se imprimen los resultados.
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        // --- Ejemplo 3: Anidación de predicados (AND y OR) ---
        System.out.println("\n========== Consulta con predicados anidados (AND y OR) ==========");
        // Paso 1: Se reinician los objetos de consulta.
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        // Paso 2: Se crea el predicado para la condición nombre = 'Andres'.
        Predicate porNombre3 = criteria.equal(from.get("nombre"), "Andres");
        // Paso 3: Se crea el predicado para la condición formaPago = 'debito'. 
        Predicate porFormaPago3 = criteria.equal(from.get("formaPago"), "debito");
        // Paso 4: Se crea el predicado para la condición id >= 4.
        Predicate p3 = criteria.ge(from.get("id"), 4L);

        // Paso 5: Se construye la consulta anidando las llamadas a los métodos.
        // El 'and' es el operador principal, y uno de sus argumentos es el resultado de 'or'.
        query.select(from).where(criteria.and(p3, criteria.or(porNombre3, porFormaPago3)));

        // Paso 6: Se ejecuta la consulta y se imprimen los resultados.
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        // Cerrar el EntityManager para liberar los recursos.
        em.close();
    }
}