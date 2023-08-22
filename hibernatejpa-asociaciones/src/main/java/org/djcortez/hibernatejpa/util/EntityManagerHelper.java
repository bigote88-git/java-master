package org.djcortez.hibernatejpa.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class EntityManagerHelper {

    private static EntityManager INSTANCE_MANAGER;

    public static synchronized EntityManager getInstance()
    {
        if (INSTANCE_MANAGER == null) {
            INSTANCE_MANAGER = Persistence.createEntityManagerFactory("exampleJpa").createEntityManager();
        }

        return INSTANCE_MANAGER;
    }
}
