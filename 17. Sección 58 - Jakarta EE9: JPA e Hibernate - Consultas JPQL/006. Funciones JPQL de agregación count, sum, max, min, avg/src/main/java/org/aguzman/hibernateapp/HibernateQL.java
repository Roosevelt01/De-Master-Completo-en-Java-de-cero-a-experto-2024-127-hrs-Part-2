package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.dominio.ClienteDto;
import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class HibernateQL {
    public static void main(String[] args) {
        // ... (consultas anteriores) ...

        //Paso 1
        System.out.println("\n============ Consulta con total de registros de la tabla ============");
        Long total = em.createQuery("select count(c) as total from Cliente c", Long.class).getSingleResult();
        System.out.println(total);

        //Paso 2
        System.out.println("\n============ Consulta con total de registros de la tabla ============");
        Long minId = em.createQuery("select min(c.id) as minimo from Cliente c", Long.class).getSingleResult();
        System.out.println(minId);

        //Paso 3
        System.out.println("\n============ Consulta con max / último id ============");
        Long maxId = em.createQuery("select max(c.id) as maximo from Cliente c", Long.class).getSingleResult();
        System.out.println(maxId);

        //Paso 4
        System.out.println("\n============ Consulta con nombre y su largo ============");
        registros = em.createQuery("select c.nombre, length(c.nombre) from Cliente c", Object[].class).getResultList();
        registros.forEach(reg -> {
            String name = (String) reg[0];
            Integer largo = (Integer) reg[1];
            System.out.println("Nombre = " + name + " | largo = " +largo);
        });

        //Paso 5
        System.out.println("\n============ Consulta con el nombre más corto ============");
        Integer minLargoNombre = em.createQuery("select min(length(c.nombre)) from Cliente c", Integer.class).getSingleResult();
        System.out.println(minLargoNombre);

        //Paso 6
        System.out.println("\n============ Consulta con el nombre más largo ============");
        Integer maxLargoNombre = em.createQuery("select max(length(c.nombre)) from Cliente c", Integer.class).getSingleResult();
        System.out.println(maxLargoNombre);

        //Paso 7
        System.out.println("\n============ Consulta resumen funciones agregaciones count, min, max, avg y sum ============");
        Object[] estadisticas = em.createQuery("select min(c.id), max(c.id), sum(c.id), count(c.id), avg(length(c.nombre)) from Cliente c", Object[].class)
                        .getSingleResult();
        Long min = (Long) estadisticas[0];
        Long max = (Long) estadisticas[1];
        Long sum = (Long) estadisticas[2];
        Long count = (Long) estadisticas[3];
        Double avg = (Double) estadisticas[4];

        System.out.println("Min = " + min
                + "\nmax = " + max
                + "\nsum = " + sum
                + "\ncount = " + count
                + "\navg = " + avg);

        em.close();
    }
}