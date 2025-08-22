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

        // --- Ejemplo 1: MultiSelect para obtener campos específicos ---
        System.out.println("\n========== Consulta de campos personalizados del entity cliente ==========");
        // Paso 1: Se crea una CriteriaQuery cuyo tipo de resultado es un arreglo de objetos (Object[]).
        CriteriaQuery<Object[]> queryObject = criteria.createQuery(Object[].class);

        // Paso 2: Se establece el FROM en la entidad Cliente.
        from = queryObject.from(Cliente.class);

        // Paso 3: Se usa multiselect() para especificar los atributos a proyectar: id, nombre y apellido.        
        queryObject.multiselect(from.get("id"), from.get("nombre"), from.get("apellido"));
        
        // Paso 4: Se ejecuta la consulta, que devolverá una lista de arreglos de objetos.
        List<Object[]> registros = em.createQuery(queryObject).getResultList();

        // Paso 5: Se itera sobre cada registro (cada arreglo de objetos) en la lista.
        registros.forEach(reg -> {
            // Se extrae el 'id' del índice 0 y se castea a Long.
            Long id = (Long) reg[0];

            // Se extrae el 'nombre' del índice 1 y se castea a String.            
            String nombre = (String ) reg[1];

            // Se extrae el 'apellido' del índice 2 y se castea a String.
            String  apellido = (String ) reg[2];

            // Se imprimen los datos extraídos.
            System.out.println("id = "+ id +" | nombre: " +nombre+ " | apellido: "+apellido);
        });

        // --- Ejemplo 2: MultiSelect combinado con la cláusula WHERE ---
        
        System.out.println("\n========== Consulta de campos personalizados del entity cliente con where ==========");
        // Paso 1: Se reinician los objetos de consulta.
        queryObject = criteria.createQuery(Object[].class);
        from = queryObject.from(Cliente.class);
        
        // Paso 2: Se construye la consulta, encadenando el WHERE después del multiselect.
        queryObject.multiselect(from.get("id"), from.get("nombre"), from.get("apellido"))
                .where(criteria.like(from.get("nombre"), "%lu%"));
        
        // Paso 3: Se ejecuta la consulta y se procesan los resultados como en el ejemplo anterior.
        registros = em.createQuery(queryObject).getResultList();
        registros.forEach(reg -> {
            Long id = (Long) reg[0];
            String nombre = (String ) reg[1];
            String  apellido = (String ) reg[2];
            System.out.println("id = "+ id +" | nombre: " +nombre+ " | apellido: "+apellido);
        });

        // --- Ejemplo 3: MultiSelect y getSingleResult para un solo registro ---
        System.out.println("\n========== Consulta de campos personalizados del entity cliente con where id ==========");
        // Paso 1: Se reinician los objetos de consulta.
        queryObject = criteria.createQuery(Object[].class);
        from = queryObject.from(Cliente.class);
        
        // Paso 2: Se construye la consulta para buscar por un ID específico.
        queryObject.multiselect(from.get("id"), from.get("nombre"), from.get("apellido"))
                .where(criteria.equal(from.get("id"), 2L));
        
        // Paso 3: Se ejecuta la consulta, pero esta vez se espera un único arreglo de objetos.
        Object[] registro = em.createQuery(queryObject).getSingleResult();

        // Paso 4: El proceso de extracción de datos es el mismo, pero sobre un solo arreglo.
        Long id = (Long) registro[0];
        String nombre = (String ) registro[1];
        String  apellido = (String ) registro[2];
        System.out.println("id = "+ id +" | nombre: " +nombre+ " | apellido: "+apellido);

        em.close();
    }
}