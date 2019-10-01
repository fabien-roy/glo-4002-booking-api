package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.constants.FestivalConstants;
import ca.ulaval.glo4002.booking.constants.RepositoryConstants;
import ca.ulaval.glo4002.booking.constants.TripConstants;
import ca.ulaval.glo4002.booking.entities.TripEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TripRepositoryContext {

    private final static Long A_ID = 1L;
    private final static Long ANOTHER_ID = 2L;
    private final static LocalDate A_DATE = FestivalConstants.Dates.START_DATE;
    private final static LocalDate ANOTHER_DATE = FestivalConstants.Dates.START_DATE.plusDays(1);
    private final static Long A_TYPE_ID = TripConstants.Types.DEPARTURE_ID;
    private final static Long ANOTHER_TYPE_ID = TripConstants.Types.ARRIVAL_ID;

    public EntityManager entityManager;
    public TripEntity aTrip;
    public TripEntity anotherTrip;

    public TripRepositoryContext() {
        setUpTrips();
        setUpEntityManager();
    }

    private void setUpTrips() {
        aTrip = new TripEntity(
                A_ID,
                A_DATE,
                A_TYPE_ID
        );

        anotherTrip = new TripEntity(
                ANOTHER_ID,
                ANOTHER_DATE,
                ANOTHER_TYPE_ID
        );
    }

    private void setUpEntityManager() {
        entityManager = mock(EntityManager.class);
        TypedQuery<TripEntity> createQuery = mock(TypedQuery.class);

        when(createQuery.getResultList()).thenReturn(Arrays.asList(aTrip, anotherTrip));
        when(entityManager.createQuery(RepositoryConstants.TRIP_FIND_ALL_QUERY, TripEntity.class)).thenReturn(createQuery);
    }
}
