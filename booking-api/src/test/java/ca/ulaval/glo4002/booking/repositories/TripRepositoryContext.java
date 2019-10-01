package ca.ulaval.glo4002.booking.repositories;

import javax.persistence.EntityManager;

import static org.mockito.Mockito.mock;

public class TripRepositoryContext {

    public EntityManager entityManager;

    public TripRepositoryContext() {
        setUpTrips();
        setUpEntityManager();
    }

    private void setUpTrips() {
        // TODO
    }

    private void setUpEntityManager() {
        entityManager = mock(EntityManager.class);

        // TODO
    }

    // TODO
}
