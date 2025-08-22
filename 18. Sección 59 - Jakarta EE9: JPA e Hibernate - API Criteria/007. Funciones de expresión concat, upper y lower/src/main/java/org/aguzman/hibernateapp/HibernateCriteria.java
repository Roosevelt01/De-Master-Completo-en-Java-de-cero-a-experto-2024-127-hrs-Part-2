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
        System.out.println("\n========== Consulta de campos personalizados del entity cliente ==========");
        CriteriaQuery<Object[]> queryObject = criteria.createQuery(Object[].class);
        from = queryObject.from(Cliente.class);
        queryObject.multiselect(from.get("id"), from.get("nombre"), from.get("apellido"));
        List<Object[]> registros = em.createQuery(queryObject).getResultList();
        registros.forEach(reg -> {
            Long id = (Long) reg[0];
            String nombre = (String ) reg[1];
            String  apellido = (String ) reg[2];
            System.out.println("id = "+ id +" | nombre: " +nombre+ " | apellido: "+apellido);
        });

        //Ejemplo 2
        System.out.println("\n========== Consulta de campos personalizados del entity cliente con where ==========");
        queryObject = criteria.createQuery(Object[].class);
        from = queryObject.from(Cliente.class);
        queryObject.multiselect(from.get("id"), from.get("nombre"), from.get("apellido")).where(criteria.like(from.get("nombre"), "%lu%"));
        registros = em.createQuery(queryObject).getResultList();
        registros.forEach(reg -> {
            Long id = (Long) reg[0];
            String nombre = (String ) reg[1];
            String  apellido = (String ) reg[2];
            System.out.println("id = "+ id +" | nombre: " +nombre+ " | apellido: "+apellido);
        });

        //Ejemplo 3
        System.out.println("\n========== Consulta de campos personalizados del entity cliente con where id ==========");
        queryObject = criteria.createQuery(Object[].class);
        from = queryObject.from(Cliente.class);
        queryObject.multiselect(from.get("id"), from.get("nombre"), from.get("apellido")).where(criteria.equal(from.get("id"), 2L));
        Object[] registro = em.createQuery(queryObject).getSingleResult();

        Long id = (Long) registro[0];
        String nombre = (String ) registro[1];
        String  apellido = (String ) registro[2];
        System.out.println("id = "+ id +" | nombre: " +nombre+ " | apellido: "+apellido);

        em.close();

        em.close();
    }
}