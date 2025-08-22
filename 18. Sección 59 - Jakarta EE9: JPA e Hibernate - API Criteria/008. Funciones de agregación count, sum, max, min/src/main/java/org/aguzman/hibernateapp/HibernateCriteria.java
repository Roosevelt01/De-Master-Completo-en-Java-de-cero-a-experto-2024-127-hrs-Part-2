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

        // --- Ejemplo 1: Contar registros con COUNT ---
        System.out.println("\n========== Consulta registros de la consulta con count ==========");
        // Paso 1: Se crea una CriteriaQuery cuyo resultado se espera que sea de tipo Long.
        CriteriaQuery<Long> queryLong = criteria.createQuery(Long.class);
        
        // Paso 2: Se establece el FROM en la entidad Cliente.
        from = queryLong.from(Cliente.class);
        
        // Paso 3: Se define el SELECT para que devuelva el resultado de la función count().
        // criteria.count() cuenta el número de registros. Se puede pasar 'from' o un atributo como 'id'.
        queryLong.select(criteria.count(from.get("id")));
        
        // Paso 4: Se ejecuta la consulta, que devuelve un único valor Long.
        Long count = em.createQuery(queryLong).getSingleResult();
        
        // Paso 5: Se imprime el total de registros.
        System.out.println(count);

        // --- Ejemplo 2: Sumar un campo con SUM ---
        System.out.println("\n========== Sumar datos con algún campo de la table ==========");
        // Paso 1: Se reutiliza y reinicia la consulta de tipo Long.
        queryLong = criteria.createQuery(Long.class);
        from = queryLong.from(Cliente.class);
        
        // Paso 3: // El SELECT ahora usa criteria.sum() para sumar todos los valores del atributo 'id'.
        queryLong.select(criteria.sum(from.get("id")));

        // Paso 4: Se ejecuta la consulta y se almacena la suma.               
        Long sum = em.createQuery(queryLong).getSingleResult();
        
        // Paso 5: Se imprime el resultado de la suma.
        System.out.println(sum);

        // --- Ejemplo 3: Obtener el valor máximo con MAX ---
        System.out.println("\n========== Consulta con el máximo id ==========");
        // Paso 1: Se reinicia la consulta.
        queryLong = criteria.createQuery(Long.class);
        from = queryLong.from(Cliente.class);

        // Paso 2: El SELECT usa criteria.max() para encontrar el valor más alto del atributo 'id'.
        queryLong.select(criteria.max(from.get("id")));
        Long max = em.createQuery(queryLong).getSingleResult();
        System.out.println(max);

        // --- Ejemplo 4: Obtener el valor mínimo con MIN ---
        System.out.println("\n========== Consulta con el mínimo id ==========");
        // Paso 1: Se reinicia la consulta.
        queryLong = criteria.createQuery(Long.class);
        from = queryLong.from(Cliente.class);

        // Paso 2: El SELECT usa criteria.min() para encontrar el valor más bajo del atributo 'id'.
        queryLong.select(criteria.min(from.get("id")));
        Long min = em.createQuery(queryLong).getSingleResult();
        System.out.println(min);

        // --- Ejemplo 5: Combinar funciones de agregación en una sola consulta con MultiSelect ---
        System.out.println("\n========== Ejemplo resultados de funciones de agregación en una sola constante ==========");
                
        // Paso 1: Se crea una CriteriaQuery que devolverá un único arreglo de objetos (Object[]).
        queryObject = criteria.createQuery(Object[].class);
        
        // Paso 2: Se establece el FROM.        
        from = queryObject.from(Cliente.class);

        // Paso 3: Se usa multiselect para especificar todas las funciones de agregación a calcular.
        queryObject.multiselect(
                criteria.count(from.get("id")),// Índice 0
                criteria.sum(from.get("id")),// Índice 1
                criteria.max(from.get("id")),// Índice 2
                criteria.min(from.get("id"))// Índice 3
            );
        // Paso 4: Se ejecuta la consulta, que devuelve un único Object[] con los 4 resultados.
        registro = em.createQuery(queryObject).getSingleResult();

        // Paso 5: Se extrae y castea cada valor del arreglo según su índice y tipo esperado.
        count = (Long) registro[0];
        sum = (Long) registro[1];
        max = (Long) registro[2];
        min = (Long) registro[3];

        // Paso 6: Se imprimen todas las estadísticas obtenidas.
        System.out.println("count = " + count + " | sum = " +sum+ " | max = " +max+ " | min = " + min);
        
        em.close();
    }
}