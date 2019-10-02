package ca.ulaval.glo4002.booking.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.entities.OxygenTankEntity;
import ca.ulaval.glo4002.booking.entities.PassengerEntity;
import ca.ulaval.glo4002.booking.exceptions.passengers.PassengerNotFoundException;

public class PassengerRepositoryTest {

    private PassengerRepository subject;
    private PassengerRepositoryContext context;

    @BeforeEach
    public void setUp() {
        context = new PassengerRepositoryContext();
        subject = new PassengerRepositoryImpl(context.entityManager);
    }
    
	@Test
	public void findById_shouldThrowPassengerNotFoundException_whenPassengerDoesNotExist() {
		PassengerNotFoundException thrown = assertThrows(
				PassengerNotFoundException.class,
				() -> subject.findById(PassengerRepositoryContext.NON_EXISTENT_ID)
		);

		assertEquals(ExceptionConstants.Passenger.NOT_FOUND_ERROR, thrown.getMessage());
	}

    @Test
    public void findAll_shouldReturnCorrectPassengers() {
        List<PassengerEntity> passengers = new ArrayList<>();

        subject.findAll().forEach(passengers::add);

        assertEquals(2, passengers.size());
        assertTrue(passengers.contains(context.aPassenger));
        assertTrue(passengers.contains(context.anotherPassenger));
    }
    
    @Test
    public void findOne_shouldReturnCorrectPassenger() {
    	PassengerEntity passengerEntity = subject
    			.findById(PassengerRepositoryContext.A_THIRD_ID).get();
    	
    	assertEquals(context.aThirdPassenger, passengerEntity);
    }

}
