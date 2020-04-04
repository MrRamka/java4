package com.yabcompany.entityManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * Class illustrate EntityManager
 * */
public class UserEntityManager {


    private final EntityManagerFactory emf;

    public UserEntityManager(String persistenceUnitName) {
        /**
         * Creating Entity Manager. The Persistence class looks for META-INF/persistence.xml in classpath
         */
        emf = Persistence.createEntityManagerFactory(persistenceUnitName);
    }


    /**
     * Returning Entity Manager
     *
     * @return EntityManager
     */
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }




}
