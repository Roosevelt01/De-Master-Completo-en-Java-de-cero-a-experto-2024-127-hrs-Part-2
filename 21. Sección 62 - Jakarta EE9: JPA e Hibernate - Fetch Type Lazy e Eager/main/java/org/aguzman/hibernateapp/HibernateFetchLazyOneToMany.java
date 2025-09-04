package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;

public class HibernateFetchLazyOneToMany {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        // Paso 1: Se busca el cliente. Sus datos básicos se cargan,
        // pero la lista de direcciones NO (sigue "dormida" en la BD).
        Cliente cliente = em.find(Cliente.class, 1L);

        // Paso 2: ⚠️ Se cierra la conexión y la sesión con la base de datos.
        // La puerta a los datos se cierra aquí.
        em.close();

        // Paso 3: 💥 ERROR. Se intenta acceder a las direcciones.
        // El programa intenta "despertar" la lista, pero la puerta ya está cerrada.
        System.out.println("Direcciones: " + cliente.getDirecciones());
    }
}
