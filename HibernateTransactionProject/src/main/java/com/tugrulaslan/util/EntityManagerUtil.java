package com.tugrulaslan.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Tugrul on 7/2/2017.
 */
public class EntityManagerUtil {
    private final static Logger logger = LoggerFactory.getLogger(EntityManagerUtil.class);
    private static EntityManagerFactory localEntityManagerFactory;
    private static EntityManager entityManager;

    static {
        try {
            localEntityManagerFactory = Persistence.createEntityManagerFactory("mysqlPU");
            entityManager = localEntityManagerFactory.createEntityManager();
        } catch (Throwable ex) {
            logger.error(ex.getMessage());
        }
    }

    public static EntityManager getEntityManager() {
        return entityManager;
    }
}
