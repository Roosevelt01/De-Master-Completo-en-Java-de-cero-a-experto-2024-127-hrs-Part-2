package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.dominio.ClienteDto;
import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class HibernateQL {
    public static void main(String[] args) {
        // ... (consultas anteriores) ...

        System.out.println("\n============ 1. Consulta por cliente y forma de pago (Object[]) ============");
        List<Object[]> registros = em.createQuery("select c, c.formaPago from Cliente c", Object[].class)
                        .getResultList();
        registros.forEach(reg -> {
        Cliente c = (Cliente) reg[0];
        String formaPago = (String) reg[1];
        System.out.println("formaPago=" + formaPago + ", cliente=" + c);
        });

        System.out.println("\n============ 2. Consulta que puebla un objeto Entity ============");
        List<Cliente> clientes = em.createQuery("select new Cliente(c.nombre, c.apellido) from Cliente c", Cliente.class)
                        .getResultList();
        clientes.forEach(System.out.println);

        System.out.println("\n============ 3. Consulta que puebla un objeto DTO ============");
        List<ClienteDto> clientesDtos = em.createQuery("select new org.aguzman.hibernateapp.dominio.ClienteDto(c.nombre, c.apellido) from Cliente c", ClienteDto.class)
                        .getResultList();
        clientesDtos.forEach(System.out.println);

        em.close();
    }
}