package ca.ulaval.glo4002.booking.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.domainobjects.shuttles.Passenger;
import ca.ulaval.glo4002.booking.dto.PassengerDto;
import ca.ulaval.glo4002.booking.entities.PassengerEntity;

public class PassengerParserTest {
	
	private static final Long A_PASSENGER_ID = 1L;
	private static final Long ANOTHER_PASSENGER_ID = 2L;
	
	private static final Passenger aPassenger = new Passenger(A_PASSENGER_ID);
	private static final Passenger anotherPassenger = new Passenger(ANOTHER_PASSENGER_ID);
	private static final PassengerEntity aPassengerEntity = new PassengerEntity(A_PASSENGER_ID);
	private static final PassengerEntity anotherPassengerEntity = new PassengerEntity(ANOTHER_PASSENGER_ID);
	
	private PassengerParser subject;

	
	@BeforeEach
	public void setUp() {
		subject = new PassengerParser();
	}
	
	@Test
	public void whenConvertingPassengersToDto_dtosShouldBeValid() {
		List<Passenger> passengers = new ArrayList<>(Arrays.asList(aPassenger, anotherPassenger));
		List<PassengerDto> passengerDtos = subject.toDto(passengers);
		assertTrue(passengerDtos.get(0) instanceof PassengerDto);
		assertEquals(passengers.size(), passengerDtos.size());
		
	}
	
	@Test
	public void whenConvertingPassengersToEntities_entitiesShouldBeValid() {
		List<Passenger> passengers = new ArrayList<>(Arrays.asList(aPassenger, anotherPassenger));
		List<PassengerEntity> passengerEntities = subject.toEntity(passengers);
		assertTrue(passengerEntities.get(0) instanceof PassengerEntity);
		assertEquals(passengers.size(), passengerEntities.size());
	}
	
	@Test
	public void whenParsingPassengerEntities_passengersShouldBeValid() {
		List<PassengerEntity> passengerEntities = new ArrayList<>
		(Arrays.asList(aPassengerEntity, anotherPassengerEntity));
		List<Passenger> passengers = subject.parseEntity(passengerEntities);
		assertTrue(passengers.get(0) instanceof Passenger);
		assertEquals(passengerEntities.size(), passengers.size());
	}
}
