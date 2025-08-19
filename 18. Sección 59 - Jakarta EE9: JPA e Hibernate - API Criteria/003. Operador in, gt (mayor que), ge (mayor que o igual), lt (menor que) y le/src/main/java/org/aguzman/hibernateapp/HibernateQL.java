package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.dominio.ClienteDto;
import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

public class HibernateQL {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        System.out.println("============ Consultar todos ============");
        List<Cliente> clientes = em.createQuery("select c from Cliente c", Cliente.class)
                .getResultList();
        clientes.forEach(System.out::println);

        System.out.println("\n============ Consultar todos ============");
        Cliente cliente = em.createQuery("select c from Cliente c where c.id=:id", Cliente.class)
                .setParameter("id", 1L)
                .getSingleResult();
        System.out.println(cliente);

        System.out.println("\n============ Consulta solo el nombre por el id ============");
        Cliente nombreCliente = em.createQuery("select c from Cliente c where c.id=:id", Cliente.class)
                .setParameter("id", 2L)
                .getSingleResult();
        System.out.println(nombreCliente);

        System.out.println("\n============ Consultas por campo personalizado ============");
        Object[] objetoCliente = em.createQuery("select c.id, c.nombre, c.apellido from Cliente c where c.id=:id", Object[].class)
                .setParameter("id", 1L)
                .getSingleResult();
        Long id = (Long) objetoCliente[0];
        String nombre = (String) objetoCliente[1];
        String apellido = (String) objetoCliente[2];
        System.out.println("id = "+ id +
                "\nNombre: "+ nombre+
                "\nApellido: " + apellido);

        System.out.println("\n============ Consultas por campo personalizado lista ============");
        List<Object[]> registros = em.createQuery("select c.id, c.nombre, c.apellido from Cliente c", Object[].class)
                .getResultList();
        registros.forEach(reg -> {
            Long idCli = (Long) reg[0];
            String nombreCli = (String) reg[1];
            String apellidoCli = (String) reg[2];
            System.out.println("id = " + idCli + "| nombre = "+ nombreCli + "| apellido = " + apellidoCli);
        });

        System.out.println("\n============ Consulta por cliente y forma de pago ============");
        registros = em.createQuery("select c, c.formaPago from Cliente c", Object[].class)
                        .getResultList();
        registros.forEach(reg -> {
            Cliente c = (Cliente) reg[0];
            String formaPago = (String) reg[1];
            System.out.println("formaPago = " + formaPago + ", " + c);
        });

        System.out.println("\n============ Consulta que devuelve objeto entity de una clase personalizada ============");
        clientes = em.createQuery("select new Cliente(c.nombre, c.apellido) from Cliente c", Cliente.class)
                        .getResultList();
        clientes.forEach(System.out::println);

        System.out.println("\n============ Consulta que devuelve objeto Dto de una clase personalizada ============");
        List<ClienteDto> clientesDtos = em.createQuery("select new org.aguzman.hibernateapp.dominio.ClienteDto(c.nombre, c.apellido) from Cliente c", ClienteDto.class)
                .getResultList();
        clientesDtos.forEach(System.out::println);

        System.out.println("\n============ Consulta por cliente y forma de pago ============");
        List<String> nombres = em.createQuery("select c.nombre from Cliente c", String.class)
                        .getResultList();
        nombres.forEach(System.out::println);

        System.out.println("\n============ Consulta por cliente y forma de pago ============");
        nombres = em.createQuery("select distinct(c.nombre) from Cliente c", String.class)
                        .getResultList();
        nombres.forEach(System.out::println);

        System.out.println("\n============ Consulta con formas de pago única ============");
        List<String> formasPago = em.createQuery("select distinct(c.formaPago) from Cliente c", String.class)
                .getResultList();
        formasPago.forEach(System.out::println);

        System.out.println("\n============ Consulta con números de formas de pago única ============");
        Long totalFormasPago = em.createQuery("select count(distinct(c.formaPago)) from Cliente c", Long.class)
                .getSingleResult();
        System.out.println(totalFormasPago);

        System.out.println("\n============ Consulta con nombre y apellido concatenados ============");
        //nombres = em.createQuery("select concat(c.nombre, ' ', c.apellido) as nombreCompleto from Cliente c", String.class)
        //              .getResultList();

        //Alternativa
        nombres = em.createQuery("select c.nombre || ' ' || c.apellido as nombreCompleto from Cliente c", String.class)
                .getResultList();

        nombres.forEach(System.out::println);

        System.out.println("\n============ Consulta con nombre y apellido concatenados en maýúsculas ============");
        nombres = em.createQuery("select upper(concat(c.nombre, ' ', c.apellido)) as nombreCompleto from Cliente c", String.class)
                      .getResultList();

        nombres.forEach(System.out::println);

        System.out.println("\n============ Consulta con nombre y apellido concatenados en minúsculas ============");
        nombres = em.createQuery("select lower(concat(c.nombre, ' ', c.apellido)) as nombreCompleto from Cliente c", String.class)
                .getResultList();

        nombres.forEach(System.out::println);

        System.out.println("\n============ Consulta para buscar por nombre ============");
        String param= "and";
        clientes = em.createQuery("select c from Cliente c where c.nombre like :parametro", Cliente.class)
                        .setParameter("parametro", "%" + param + "%")
                                .getResultList();
        clientes.forEach(System.out::println);

        System.out.println("\n============ Consulta por rangos ============");
        //clientes = em.createQuery("select c from Cliente c where c.id between 2 and 5", Cliente.class).getResultList();
        clientes = em.createQuery("select c from Cliente c where c.nombre between 'J' and 'Q'", Cliente.class).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("\n============ Consulta con orden ============");
        //clientes = em.createQuery("select c from Cliente c order by c.nombre asc", Cliente.class).getResultList();
        //clientes = em.createQuery("select c from Cliente c order by c.nombre desc", Cliente.class).getResultList();
        //clientes = em.createQuery("select c from Cliente c order by c.nombre desc, c. apellido desc", Cliente.class).getResultList();
        clientes = em.createQuery("select c from Cliente c order by c.nombre asc, c. apellido desc", Cliente.class).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("\n============ Consulta con total de registros de la tabla ============");
        Long total = em.createQuery("select count(c) as total from Cliente c", Long.class).getSingleResult();
        System.out.println(total);

        System.out.println("\n============ Consulta con total de registros de la tabla ============");
        Long minId = em.createQuery("select min(c.id) as minimo from Cliente c", Long.class).getSingleResult();
        System.out.println(minId);

        System.out.println("\n============ Consulta con max / último id ============");
        Long maxId = em.createQuery("select max(c.id) as maximo from Cliente c", Long.class).getSingleResult();
        System.out.println(maxId);

        System.out.println("\n============ Consulta con nombre y su largo ============");
        registros = em.createQuery("select c.nombre, length(c.nombre) from Cliente c", Object[].class).getResultList();
        registros.forEach(reg -> {
            String name = (String) reg[0];
            Integer largo = (Integer) reg[1];
            System.out.println("Nombre = " + name + " | largo = " +largo);
        });

        System.out.println("\n============ Consulta con el nombre más corto ============");
        Integer minLargoNombre = em.createQuery("select min(length(c.nombre)) from Cliente c", Integer.class).getSingleResult();
        System.out.println(minLargoNombre);

        System.out.println("\n============ Consulta con el nombre más largo ============");
        Integer maxLargoNombre = em.createQuery("select max(length(c.nombre)) from Cliente c", Integer.class).getSingleResult();
        System.out.println(maxLargoNombre);

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

        System.out.println("\n============ Consulta con el nombre más corto y su largo ============");
        registros = em.createQuery("select c.nombre, length(c.nombre) from Cliente c where " +
                        "length(c.nombre) = (select min(length(c.nombre)) from Cliente c)", Object[].class)
                        .getResultList();
        registros.forEach(reg -> {
            String nom = (String) reg[0];
            Integer largo = (Integer) reg[1];
            System.out.println("Nombre = " + nom + "| Largo = " + largo);
        });

        System.out.println("\n============ Consulta con el nombre más largo y su largo ============");
        registros = em.createQuery("select c.nombre, length(c.nombre) from Cliente c where " +
                        "length(c.nombre) = (select max(length(c.nombre)) from Cliente c)", Object[].class)
                .getResultList();
        registros.forEach(reg -> {
            String nom = (String) reg[0];
            Integer largo = (Integer) reg[1];
            System.out.println("Nombre = " + nom + "| Largo = " + largo);
        });

        System.out.println("\n============ Consulta para obtener el último registro ============");
        Cliente ultimoCliente = em.createQuery("select c from Cliente c where c.id = (select max(c.id) from Cliente c)", Cliente.class)
                .getSingleResult();
        System.out.println(ultimoCliente);

        System.out.println("\n============ Consulta where in ============");
        clientes = em.createQuery("select c from Cliente c where c.id in :ids", Cliente.class)
                        .setParameter("ids", Arrays.asList(1L, 2L, 9L, 6L))
                                .getResultList();
        clientes.forEach(System.out::println);

        em.close();
    }
}





