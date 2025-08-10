package org.aguzman.hibernateapp.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
    // 1. Atributo estático y final para garantizar una única instancia del Factory.
    private static final EntityManagerFactory entiryManagerFactory = buildEntityManagerFactory();

    // 2. Método privado para construir el EntityManagerFactory.
    private static EntityManagerFactory buildEntityManagerFactory(){
        // Usa la clase Persistence para leer el persistence.xml y crear el Factory.
        return Persistence.createEntityManagerFactory("ejemploJPA");
    }

    // 3. Método público y estático para obtener un EntityManager.
    public static EntityManager getEntityManager(){
        // El Factory crea una nueva instancia de EntityManager para cada solicitud.
        return entiryManagerFactory.createEntityManager();
    }
}
