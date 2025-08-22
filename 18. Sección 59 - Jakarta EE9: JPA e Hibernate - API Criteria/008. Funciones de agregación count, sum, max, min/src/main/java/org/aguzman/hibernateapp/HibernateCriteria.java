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


        //Ejemplo 1
        System.out.println("\n========== Consulta registros de la consulta con count ==========");
        CriteriaQuery<Long> queryLong = criteria.createQuery(Long.class);
        from = queryLong.from(Cliente.class);
        queryLong.select(criteria.count(from.get("id")));
        Long count = em.createQuery(queryLong).getSingleResult();
        System.out.println(count);

        //Ejemplo 2
        System.out.println("\n========== Sumar datos con algún campo de la table ==========");
        queryLong = criteria.createQuery(Long.class);
        from = queryLong.from(Cliente.class);
        queryLong.select(criteria.sum(from.get("id")));
        Long sum = em.createQuery(queryLong).getSingleResult();
        System.out.println(sum);

        //Ejemplo 3
        System.out.println("\n========== Consulta con el máximo id ==========");
        queryLong = criteria.createQuery(Long.class);
        from = queryLong.from(Cliente.class);
        queryLong.select(criteria.max(from.get("id")));
        Long max = em.createQuery(queryLong).getSingleResult();
        System.out.println(max);

        //Ejemplo 4
        System.out.println("\n========== Consulta con el mínimo id ==========");
        queryLong = criteria.createQuery(Long.class);
        from = queryLong.from(Cliente.class);
        queryLong.select(criteria.min(from.get("id")));
        Long min = em.createQuery(queryLong).getSingleResult();
        System.out.println(min);

        //Ejemplo 5
        System.out.println("\n========== Ejemplo resultados de funciones de agregación en una sola constante ==========");
        queryObject = criteria.createQuery(Object[].class);
        from = queryObject.from(Cliente.class);
        queryObject.multiselect(criteria.count(from.get("id"))
                , criteria.sum(from.get("id"))
                , criteria.max(from.get("id"))
                , criteria.min(from.get("id")));

        registro = em.createQuery(queryObject).getSingleResult();

        count = (Long) registro[0];
        sum = (Long) registro[1];
        max = (Long) registro[2];
        min = (Long) registro[3];

        System.out.println("count = " + count + " | sum = " +sum+ " | max = " +max+ " | min = " + min);
        
        em.close();
    }
}