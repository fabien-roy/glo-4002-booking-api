package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.entities.TripEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// TODO : Solve persistence problem

public class TripRepositoryTest {

    private TripRepository subject;
    private TripRepositoryContext context;

    @BeforeEach
    public void setUp() {
        context = new TripRepositoryContext();
        subject = new TripRepositoryImpl(context.entityManager);
    }

    @Test
    public void findAll_shouldReturnCorrectTrips() {
        List<TripEntity> trips = new ArrayList<>();

        subject.findAll().forEach(trips::add);

        assertEquals(2, trips.size());
        assertTrue(trips.contains(context.aTrip));
        assertTrue(trips.contains(context.anotherTrip));
    }
}
