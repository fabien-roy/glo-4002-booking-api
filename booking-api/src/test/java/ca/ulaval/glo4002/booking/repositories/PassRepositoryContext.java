package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.constants.DateConstants;
import ca.ulaval.glo4002.booking.constants.PassConstants;
import ca.ulaval.glo4002.booking.constants.RepositoryConstants;
import ca.ulaval.glo4002.booking.entities.PassEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PassRepositoryContext {

    private final static LocalDate A_PASS_EVENT_DATE = DateConstants.START_DATE;
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
        entityManager = mock(EntityManager.class);
        TypedQuery<PassEntity> createQuery = mock(TypedQuery.class);

        when(createQuery.getResultList()).thenReturn(Arrays.asList(aPass, anotherPass));
        when(entityManager.createQuery(RepositoryConstants.PASS_FIND_ALL_QUERY, PassEntity.class)).thenReturn(createQuery);
        when(entityManager.find(PassEntity.class, A_PASS_ID)).thenReturn(aPass);
        when(entityManager.find(PassEntity.class, ANOTHER_PASS_ID)).thenReturn(anotherPass);
        when(entityManager.find(PassEntity.class, A_NON_EXISTANT_PASS_ID)).thenReturn(null);
    }

    public void setUpEntityManagerForSaveAll() {
        EntityTransaction transaction = mock(EntityTransaction.class);
        when(entityManager.getTransaction()).thenReturn(transaction);

        aNonExistentPass.setId(null);
        when(entityManager.find(PassEntity.class, A_NON_EXISTANT_PASS_ID)).thenReturn(aNonExistentPass);
    }
}
