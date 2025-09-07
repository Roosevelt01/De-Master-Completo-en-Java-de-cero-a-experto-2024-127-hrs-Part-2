package org.aguzman.apiservlet.webapp.headers.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    // Paso 1: Atributo estático y final para el EntityManagerFactory.
    private static final EntityManagerFactory entiryManagerFactory = buildEntityManagerFactory();

    // Paso 2: Método privado para construir la instancia.
    private static EntityManagerFactory buildEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("ejemploJpa");
    }

    // Paso 3: Método público para obtener un EntityManager por cada solicitud.
    public static EntityManager getEntityManager(){
        return  entiryManagerFactory.createEntityManager();
    }
}
