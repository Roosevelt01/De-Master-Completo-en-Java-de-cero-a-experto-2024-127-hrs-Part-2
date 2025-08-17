package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.dominio.ClienteDto;
import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

public class HibernateQL {
    public static void main(String[] args) {
        // ... (consultas anteriores) ...

        // Paso 1: Obtener el cliente con el nombre más corto y su longitud
        System.out.println("\n============ Consulta con el nombre más corto y su largo ============");
        // La subconsulta `(select min(length(c.nombre)) ...)` encuentra la longitud mínima de todos los nombres.
        // La consulta principal luego filtra y devuelve los clientes que coinciden con esa longitud.
        registros = em.createQuery("select c.nombre, length(c.nombre) from Cliente c where " +
                        "length(c.nombre) = (select min(length(c.nombre)) from Cliente c)", Object[].class)
                        .getResultList();       
        // Itera sobre los resultados para imprimirlos.
        registros.forEach(reg -> {
            // Extrae el nombre del primer elemento del arreglo.
            String nom = (String) reg[0];
            // Extrae el largo del segundo elemento del arreglo.
            Integer largo = (Integer) reg[1];
            System.out.println("Nombre = " + nom + "| Largo = " + largo);
        });

        // Paso 2: Obtener el cliente con el nombre más largo y su longitud
        System.out.println("\n============ Consulta con el nombre más largo y su largo ============");
        // Se utiliza la misma lógica que en el paso anterior, pero con la función `max()` en la subconsulta.
        registros = em.createQuery("select c.nombre, length(c.nombre) from Cliente c where " +
                        "length(c.nombre) = (select max(length(c.nombre)) from Cliente c)", Object[].class)
                .getResultList();
        registros.forEach(reg -> {
            String nom = (String) reg[0];
            Integer largo = (Integer) reg[1];
            System.out.println("Nombre = " + nom + "| Largo = " + largo);
        });

        // Paso 3: Obtener el último cliente registrado (el que tiene el ID más alto)
        System.out.println("\n============ Consulta para obtener el último registro ============");
        // La subconsulta `(select max(c.id) ...)` encuentra el ID más alto.
        // La consulta principal busca el cliente cuyo ID coincide con ese valor.
        Cliente ultimoCliente = em.createQuery("select c from Cliente c where c.id = (select max(c.id) from Cliente c)", Cliente.class)
                .getSingleResult();
        System.out.println(ultimoCliente);

        // Paso 4: Consulta que usa la cláusula 'WHERE IN' con parámetros
        System.out.println("\n============ Consulta where in ============");
        // La cláusula `in` permite filtrar por un conjunto de valores.
        // Aquí se usa `:ids` como parámetro, el cual se enlaza con una lista de IDs.
        clientes = em.createQuery("select c from Cliente c where c.id in :ids", Cliente.class)
                        .setParameter("ids", Arrays.asList(1L, 2L, 9L, 6L))
                                .getResultList();
        clientes.forEach(System.out::println);

        em.close();
    }
}