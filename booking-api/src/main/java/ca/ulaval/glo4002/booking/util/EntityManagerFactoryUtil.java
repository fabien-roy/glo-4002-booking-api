package ca.ulaval.glo4002.booking.util;

import ca.ulaval.glo4002.booking.constants.RepositoryConstants;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryUtil {

    private static EntityManagerFactory entityManagerFactory;

    private EntityManagerFactoryUtil() {

    }

    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory(RepositoryConstants.PRODUCTION_PERSISTENCE_NAME);
        }

        return entityManagerFactory;
    }
}
