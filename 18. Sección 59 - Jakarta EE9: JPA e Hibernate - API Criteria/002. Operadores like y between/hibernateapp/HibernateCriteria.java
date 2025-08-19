package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

public class HibernateCriteria {
    public static void main(String[] args) {
        // Resto del código

        // --- Ejemplo 1: Usando WHERE LIKE ---
        System.out.println("\n========== Usando where like para buscar clientes por nombre ==========");
        // Paso 1: Se reinician los objetos 'query' y 'from' para una nueva consulta limpia.
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        // Paso 2: // Se construye la cláusula LIKE con el método `criteria.like()`.
        // Los caracteres '%' actúan como comodines para buscar cualquier coincidencia.
        query.select(from).where(criteria.like(from.get("nombre"), "%and%"));

        clientes = em.createQuery(query).getResultList();        
        clientes.forEach(System.out::println);

        //Alternativa: LIKE con UPPER y parámetros
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        
        // Paso 3: Se crea un `ParameterExpression` para usar un parámetro seguro.
        ParameterExpression<String> nombreParamLike = criteria.parameter(String.class, "nombreParam");
        
        // Paso 4: Se usa `criteria.upper()` para convertir el nombre de la entidad y el parámetro a mayúsculas
        // antes de la comparación. Esto asegura que la búsqueda sea "case-insensitive".
        query.select(from).where(criteria.like(criteria.upper(from.get("nombre")), criteria.upper(nombreParamLike)));
        
        // Paso 5: El valor del parámetro incluye los comodines de búsqueda.
        clientes = em.createQuery(query).setParameter("nombreParam", "%jo%").getResultList();
        clientes.forEach(System.out::println);

        // --- Ejemplo 2: Usando WHERE BETWEEN ---
        System.out.println("\n========== Usando usando where between para rangos ==========");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        // Paso 6: El método `criteria.between()` filtra los IDs que se encuentran en el rango [2, 6], ambos inclusive.
        query.select(from).where(criteria.between(from.get("id"), 2L, 6L));
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        em.close();
    }
}