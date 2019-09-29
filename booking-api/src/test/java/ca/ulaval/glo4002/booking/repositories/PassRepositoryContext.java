package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.constants.FestivalConstants;
import ca.ulaval.glo4002.booking.constants.PassConstants;
import ca.ulaval.glo4002.booking.entities.PassEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PassRepositoryContext {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ca.ulaval.glo4002.booking.noPersistence");
    private final static String A_PASS_EVENT_DATE = FestivalConstants.Dates.START_DATE.toString();
    public EntityManager entityManager;
    public PassEntity aPass;
    public PassEntity anotherPass;
    public PassEntity aNonExistentPass;
    public final static Long A_PASS_ID = 1L;
    public final static Long ANOTHER_PASS_ID = 2L;
    public final static Long A_NON_EXISTANT_PASS_ID = 0L;

    public PassRepositoryContext() {
        setUpPasses();
        setUpEntityManager();
    }

    private void setUpPasses() {
        aPass = new PassEntity(
                A_PASS_ID,
                PassConstants.Categories.SUPERNOVA_ID,
                PassConstants.Options.SINGLE_ID,
                A_PASS_EVENT_DATE
        );

        anotherPass = new PassEntity(
                ANOTHER_PASS_ID,
                PassConstants.Categories.NEBULA_ID,
                PassConstants.Options.PACKAGE_ID,
                null
        );

        aNonExistentPass = new PassEntity(
                A_NON_EXISTANT_PASS_ID,
                PassConstants.Categories.NEBULA_ID,
                PassConstants.Options.PACKAGE_ID,
                null
        );
    }

    private void setUpEntityManager() {
        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(aPass);
        entityManager.persist(anotherPass);
        entityManager.getTransaction().commit();
    }
}
