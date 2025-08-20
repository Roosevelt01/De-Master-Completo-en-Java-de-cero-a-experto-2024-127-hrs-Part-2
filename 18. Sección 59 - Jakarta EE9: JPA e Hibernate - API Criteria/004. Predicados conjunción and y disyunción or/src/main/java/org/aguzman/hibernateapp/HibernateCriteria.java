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

        //Ejemplo 1
        System.out.println("\n========== Consulta con los predicados conjunción(and) ==========");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        Predicate porNombre = criteria.equal(from.get("nombre"), "Andres");
        Predicate porFormaPago = criteria.equal(from.get("formaPago"), "debito");
        query.select(from).where(criteria.and(porNombre, porFormaPago));
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        //Ejemplo 2
        System.out.println("\n========== Consulta con los predicados disyunsión(OR) ==========");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        Predicate porNombre2 = criteria.equal(from.get("nombre"), "Andres");
        Predicate porFormaPago2 = criteria.equal(from.get("formaPago"), "debito");
        query.select(from).where(criteria.or(porNombre2, porFormaPago2));
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        //Ejemplo 3
        System.out.println("\n========== Consulta con los predicados disyunsión(OR) ==========");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        Predicate porNombre3 = criteria.equal(from.get("nombre"), "Andres");
        Predicate porFormaPago3 = criteria.equal(from.get("formaPago"), "debito");
        Predicate p3 = criteria.ge(from.get("id"), 4L);
        query.select(from).where(criteria.and(p3, criteria.or(porNombre3, porFormaPago3)));
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        em.close();
    }
}
