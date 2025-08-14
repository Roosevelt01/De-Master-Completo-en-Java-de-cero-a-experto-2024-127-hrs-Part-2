package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class HibernateQL {
    public static void main(String[] args) {
        // Paso 1: Obtenemos una instancia de EntityManager para realizar las operaciones.
        EntityManager em = JpaUtil.getEntityManager();

        // ====================================================================
        //              Ejemplo 1: Consultar todos los clientes
        // ====================================================================        
        System.out.println("============ Consultar todos ============");
        List<Cliente> clientes = em.createQuery("select c from Cliente c", Cliente.class)
                .getResultList();
        clientes.forEach(System.out::println);

        // ====================================================================
        //              Ejemplo 2: Consultar por ID
        // ====================================================================
        System.out.println("\n============ Consultar todos ============");
        // La consulta utiliza un parámetro con nombre ':id' para mayor seguridad.
        Cliente cliente = em.createQuery("select c from Cliente c where c.id=:id", Cliente.class)
                .setParameter("id", 1L)// Asignamos el valor al parámetro.
                .getSingleResult();
        System.out.println(cliente);

        // ====================================================================
        //              Ejemplo 3: Consultar un solo campo (nombre)
        // ====================================================================
        System.out.println("\n============ Consulta solo el nombre por el id ============");
        // La consulta selecciona solo el campo 'nombre' y devuelve un String.
        String nombreCliente = em.createQuery("select c from Cliente c where c.id=:id", Cliente.class)
                .setParameter("id", 2L)
                .getSingleResult();
        System.out.println(nombreCliente);

        // ====================================================================
        //              Ejemplo 4: Consulta de campos personalizados
        // ==================================================================== 
        System.out.println("\n============ Consultas por campo personalizado ============");
        // La consulta devuelve un arreglo de objetos (Object[]) con los campos seleccionados.
        Object[] objetoCliente = em.createQuery("select c.id, c.nombre, c.apellido from Cliente c where c.id=:id", Object[].class)
                .setParameter("id", 1L)
                .getSingleResult();
        // Se realiza un casting explícito para acceder a cada campo.
        Long id = (Long) objetoCliente[0];
        String nombre = (String) objetoCliente[1];
        String apellido = (String) objetoCliente[2];
        System.out.println("id = "+ id +
                "\nNombre: "+ nombre+
                "\nApellido: " + apellido);

        // ====================================================================
        //        Ejemplo 5: Consulta de una lista de campos personalizados
        // ====================================================================    
        System.out.println("\n============ Consultas por campo personalizado lista ============");
        // La consulta devuelve una lista de arreglos de objetos (List<Object[]>).
        List<Object[]> registros = em.createQuery("select c.id, c.nombre, c.apellido from Cliente c", Object[].class)
                .getResultList();
        // Iteramos sobre la lista para acceder a cada arreglo y sus campos.
        registros.forEach(reg -> {
            Long idCli = (Long) reg[0];
            String nombreCli = (String) reg[1];
            String apellidoCli = (String) reg[2];
            System.out.println("id = " + idCli + "| nombre = "+ nombreCli + "| apellido = " + apellidoCli);
        });

        em.close();

    }
}