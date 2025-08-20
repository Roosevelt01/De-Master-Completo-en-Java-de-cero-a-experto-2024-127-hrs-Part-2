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
        System.out.println("\n========== Consultas con order by asc ==========");

        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        query.select(from).orderBy(criteria.asc(from.get("nombre")), criteria.desc(from.get("apellido")));
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        //Ejemplo 2
        System.out.println("\n========== Consultas con order by desc ==========");

        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        query.select(from).orderBy(criteria.desc(from.get("nombre")), criteria.desc(from.get("apellido")));
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        //Ejemplo 3
        System.out.println("\n========== Consultas por id ==========");

        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        ParameterExpression<Long> idParam = criteria.parameter(Long.class, "id");

        query.select(from).where(criteria.equal(from.get("id"), idParam));

        Cliente cliente = em.createQuery(query)
                .setParameter("id", 1L)
                .getSingleResult();

        System.out.println(cliente);

        //Ejemplo 3
        System.out.println("\n========== Consulta solo el nombre de los clientes ==========");
        CriteriaQuery<String> queryString = criteria.createQuery(String.class);
        from = queryString.from(Cliente.class);
        queryString.select(from.get("nombre"));
        List<String> nombres = em.createQuery(queryString).getResultList();
        nombres.forEach(System.out::println);

        //Ejemplo 4
        System.out.println("\n========== Consulta solo el nombre de los clientes únicos con distinct ==========");
        queryString = criteria.createQuery(String.class);
        from = queryString.from(Cliente.class);
        queryString.select(criteria.upper(from.get("nombre"))).distinct(true);
        nombres = em.createQuery(queryString).getResultList();
        nombres.forEach(System.out::println);

        em.close();
    }
}
