package ca.ulaval.glo4002.booking.program.artists;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.program.events.EventDate;

class AvailabilityTest {

	private Availability availability;

	@Test
	void constructing_shouldSetCorrectDate() {
		EventDate date = mock(EventDate.class);

		availability = new Availability(date);

		assertEquals(availability.getDate(), date);
	}

}
