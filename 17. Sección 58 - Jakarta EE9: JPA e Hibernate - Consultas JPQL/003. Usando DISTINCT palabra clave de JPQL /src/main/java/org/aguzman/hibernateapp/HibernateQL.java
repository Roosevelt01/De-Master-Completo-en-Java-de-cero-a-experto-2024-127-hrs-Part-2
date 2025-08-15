package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.dominio.ClienteDto;
import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class HibernateQL {
    public static void main(String[] args) {
        // ... (consultas anteriores) ...

        // PASO 1: Consulta para obtener nombres de clientes
        System.out.println("\n============ Consulta con nombres de clientes (con duplicados) ============");
        List<String> nombres = em.createQuery("select c.nombre from Cliente c", String.class)
                        .getResultList();
        nombres.forEach(System.out::println);

        // PASO 2: Consulta para obtener nombres de clientes únicos
        System.out.println("\n============ Consulta con nombres de clientes únicos (sin duplicados) ============");
        nombres = em.createQuery("select distinct(c.nombre) from Cliente c", String.class)
                        .getResultList();
        nombres.forEach(System.out::println);

        // PASO 3: Consulta para obtener formas de pago únicas
        System.out.println("\n============ Consulta con formas de pago únicas ============");
        List<String> formasPago = em.createQuery("select distinct(c.formaPago) from Cliente c", String.class)
                .getResultList();
        formasPago.forEach(System.out::println);

        // PASO 4: Consulta para contar el número de formas de pago únicas
        System.out.println("\n============ Consulta con número de formas de pago únicas ============");
        // La consulta devuelve un solo resultado, por lo que se utiliza getSingleResult()
        Long totalFormasPago = em.createQuery("select count(distinct(c.formaPago)) from Cliente c", Long.class)
                .getSingleResult();
        System.out.println("Total de formas de pago únicas: " + totalFormasPago);
    }
}