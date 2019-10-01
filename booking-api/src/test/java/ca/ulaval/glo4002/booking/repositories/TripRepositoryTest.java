package ca.ulaval.glo4002.booking.repositories;

import org.junit.jupiter.api.BeforeEach;

// TODO : Solve persistence problem

public class TripRepositoryTest {

    private TripRepository subject;
    private TripRepositoryContext context;

    @BeforeEach
    public void setUp() {
        context = new TripRepositoryContext();
        subject = new TripRepositoryImpl(context.entityManager);
    }

    // TODO
}
