package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.dominio.ClienteDto;
import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class HibernateQL {
    public static void main(String[] args) {
        // ... (consultas anteriores) ...

        // Paso 1: Consulta para obtener el total de registros de la tabla Clientes
        System.out.println("\n============ Consulta con total de registros de la tabla ============");
        Long total = em.createQuery("select count(c) as total from Cliente c", Long.class).getSingleResult();
        System.out.println(total);

        // Paso 2: Consulta para encontrar el valor mínimo del ID
        System.out.println("\n============ Consulta con total de registros de la tabla ============");
        Long minId = em.createQuery("select min(c.id) as minimo from Cliente c", Long.class).getSingleResult();
        System.out.println(minId);

        // Paso 3: Consulta para encontrar el valor máximo del ID
        System.out.println("\n============ Consulta con max / último id ============");
        Long maxId = em.createQuery("select max(c.id) as maximo from Cliente c", Long.class).getSingleResult();
        System.out.println(maxId);

        // Paso 4: Consulta que devuelve el nombre de cada cliente y la longitud de su nombre
        System.out.println("\n============ Consulta con nombre y su largo ============");
        registros = em.createQuery("select c.nombre, length(c.nombre) from Cliente c", Object[].class).getResultList();
        // Itera sobre cada registro de la lista.
        registros.forEach(reg -> {
            // Extrae el nombre del primer elemento del arreglo (índice 0).
            String name = (String) reg[0];
            // Extrae el largo del segundo elemento del arreglo (índice 1).
            Integer largo = (Integer) reg[1];
            System.out.println("Nombre = " + name + " | largo = " +largo);
        });

        // Paso 5: Consulta para encontrar la longitud mínima de los nombres de los clientes
        System.out.println("\n============ Consulta con el nombre más corto ============");
        Integer minLargoNombre = em.createQuery("select min(length(c.nombre)) from Cliente c", Integer.class).getSingleResult();
        System.out.println(minLargoNombre);

        // Paso 6: Consulta para encontrar la longitud máxima de los nombres de los clientes
        System.out.println("\n============ Consulta con el nombre más largo ============");
        Integer maxLargoNombre = em.createQuery("select max(length(c.nombre)) from Cliente c", Integer.class).getSingleResult();
        System.out.println(maxLargoNombre);

        // Paso 7: Consulta que combina varias funciones de agregación en un solo resultado
        System.out.println("\n============ Consulta resumen funciones agregaciones count, min, max, avg y sum ============");
        Object[] estadisticas = em.createQuery("select min(c.id), max(c.id), sum(c.id), count(c.id), avg(length(c.nombre)) from Cliente c", Object[].class)
                        .getSingleResult();
        // Extrae y castea cada valor del arreglo a su tipo de dato correcto.
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
