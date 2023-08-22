package org.djcortez.hibernatejpa.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {

    private static final EntityManagerFactory entityManagerFactory = buildEntityManager();

    private static EntityManagerFactory buildEntityManager(){
        return Persistence.createEntityManagerFactory("exampleJpa");
    }

    public static EntityManager getEntityManager(){
        return entityManagerFactory.createEntityManager();
    }
}
