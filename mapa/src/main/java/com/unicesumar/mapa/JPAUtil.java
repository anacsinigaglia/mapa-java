package com.unicesumar.mapa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    private static EntityManagerFactory entityManagerFactory;

    public static EntityManager getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("projetos");
        }
        return entityManagerFactory.createEntityManager();
    }
}
