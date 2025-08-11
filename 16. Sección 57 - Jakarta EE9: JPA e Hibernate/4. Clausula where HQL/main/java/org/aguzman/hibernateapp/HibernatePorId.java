package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Scanner;

public class HibernatePorId {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Ingrese el id: ");
        Long id = s.nextLong();
        // 1. Obtener el EntityManager
        EntityManager em = JpaUtil.getEntityManager();

        // 2. Usar el método find() para la búsqueda por llave primaria
        Cliente cliente = em.find(Cliente.class, id);

        // 3. Imprimir el resultado
        System.out.println(cliente);

        // 4. Cerrar el EntityManager
        em.close();
    }
}
