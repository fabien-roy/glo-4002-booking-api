package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.entities.PassengerEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PassengerRepositoryTest {

    private PassengerRepository subject;
    private PassengerRepositoryContext context;

    @BeforeEach
    public void setUp() {
        context = new PassengerRepositoryContext();
        subject = new PassengerRepositoryImpl(context.entityManager);
    }

    @Test
    public void findAll_shouldReturnCorrectPassengers() {
        List<PassengerEntity> passengers = new ArrayList<>();

        subject.findAll().forEach(passengers::add);

        assertEquals(2, passengers.size());
        assertTrue(passengers.contains(context.aPassenger));
        assertTrue(passengers.contains(context.anotherPassenger));
    }
}
