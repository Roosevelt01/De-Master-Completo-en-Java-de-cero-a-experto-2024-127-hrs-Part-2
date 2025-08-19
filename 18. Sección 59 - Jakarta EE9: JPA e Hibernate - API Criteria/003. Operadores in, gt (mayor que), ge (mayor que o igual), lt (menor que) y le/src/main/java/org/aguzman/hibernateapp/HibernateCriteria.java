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

        //Ejemplo 1
        System.out.println("\n========== Consulta where in ==========");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        //query.select(from).where(from.get("nombre").in("Andres", "John", "Lou"));
        query.select(from).where(from.get("nombre").in(Arrays.asList("Andres", "John", "Lou")));//Alternativ
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        //Alternativa 2
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        ParameterExpression<List> listParam = criteria.parameter(List.class, "nombres");
        query.select(from).where(from.get("nombre").in(listParam));
        clientes = em.createQuery(query)
                .setParameter("nombres", Arrays.asList("Andres", "John", "Lou"))
                .getResultList();
        clientes.forEach(System.out::println);

        //Ejemplo 2
        System.out.println("\n========== Filtrar ge ==========");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        query.select(from).where(criteria.ge(from.get("id"), 3L ));
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        //Ejemplo 3
        System.out.println("\n========== Filtrar gt ==========");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        query.select(from).where(criteria.gt(criteria.length(from.get("nombre")), 5L ));
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        //Ejemplo 2
        System.out.println("\n========== Filtrar con le ==========");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        query.select(from).where(criteria.le(from.get("id"), 2L ));
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        //Ejemplo 2
        System.out.println("\n========== Filtrar con lt ==========");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        query.select(from).where(criteria.lt(from.get("id"), 2L ));
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        em.close();
    }
}
