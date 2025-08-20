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

        // --- Ejemplo 1: Cláusula ORDER BY (ascendente y descendente) ---
        System.out.println("\n========== Consultas con order by asc ==========");
        // Paso 1: Se reinician los objetos de consulta para un nuevo query.
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        // Paso 2: Se construye la consulta añadiendo la cláusula orderBy.
        query.select(from).orderBy(
                // Primer criterio de orden: por 'nombre' en forma ascendente.        
                criteria.asc(from.get("nombre")),
                // Segundo criterio (desempate): por 'apellido' en forma descendente.
                criteria.desc(from.get("apellido")));
        
        // Paso 3: Se ejecuta la consulta y se obtiene la lista ya ordenada.
        clientes = em.createQuery(query).getResultList();

        // Paso 4: Se imprimen los resultados ordenados.
        clientes.forEach(System.out::println);

        // --- Ejemplo 2: Cláusula ORDER BY (totalmente descendente) ---
        System.out.println("\n========== Consultas con order by desc ==========");

        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        // Paso 1: Se ordena por 'nombre' y luego por 'apellido' de forma descendente en ambos casos.
        query.select(from).orderBy(
                criteria.desc(from.get("nombre")), 
                criteria.desc(from.get("apellido")));
        
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        // --- Ejemplo 3: Obtener un solo resultado por ID ---
        System.out.println("\n========== Consultas por id ==========");
        // Paso 1: Se reinician los objetos de consulta.
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        //Paso 2: Se define un parámetro de tipo Long para el ID.
        ParameterExpression<Long> idParam = criteria.parameter(Long.class, "id");

        // Paso 3: Se construye la consulta con un WHERE para filtrar por el ID.
        query.select(from).where(criteria.equal(from.get("id"), idParam));

        // Paso 4 Se ejecuta la consulta.
        Cliente cliente = em.createQuery(query)
                // Se asigna el valor 1L al parámetro 'id'.
                .setParameter("id", 1L)
                // Se obtiene un único resultado.
                .getSingleResult();

        // Paso 5: Se imprime el cliente encontrado.
        System.out.println(cliente);

        // --- Ejemplo 4: Proyección de un solo campo (String) ---
        System.out.println("\n========== Consulta solo el nombre de los clientes ==========");
        // Paso 1: Se crea una CriteriaQuery cuyo tipo de resultado es String.
        CriteriaQuery<String> queryString = criteria.createQuery(String.class);

        // Paso 2: El FROM sigue apuntando a la entidad Cliente, de donde se sacarán los datos.
        // OJO: Se asigna a la variable 'from' que es de tipo Root<Cliente>.
        from = queryString.from(Cliente.class);            

        // Paso 3: En el SELECT, en lugar de pasar 'from', se especifica el atributo a proyectar.
        queryString.select(from.get("nombre"));
        
        // Paso 4: Se ejecuta la consulta y se obtiene una lista de Strings.
        List<String> nombres = em.createQuery(queryString).getResultList();
        
        // Paso 5: Se imprimen los nombres.
        nombres.forEach(System.out::println);

        // --- Ejemplo 5: Proyección y DISTINCT (para resultados únicos) ---
        System.out.println("\n========== Consulta solo el nombre de los clientes únicos con distinct ==========");
        //Paso 1: Se reinicia la consulta de tipo String.
        queryString = criteria.createQuery(String.class);
        
        // Paso 2: Se define de nuevo el FROM.
        from = queryString.from(Cliente.class);
        
        // Paso 3: Se construye la selección en cadena:
        queryString.select(
                // Se envuelve el atributo 'nombre' en la función upper().
                criteria.upper(from.get("nombre")))
                // Se aplica el método distinct() a la consulta para eliminar duplicados.
                .distinct(true);
        
        //Paso 4 Se ejecuta la consulta y se imprimen los resultados.
        nombres = em.createQuery(queryString).getResultList();
        nombres.forEach(System.out::println);

        em.close();
    }
}