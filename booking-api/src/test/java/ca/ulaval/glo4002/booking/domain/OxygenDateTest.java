package ca.ulaval.glo4002.booking.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.exceptions.genericException;

class OxygenDateTest {

	@Test
	public void constructing_shouldThrowInvalidOxygenDateException_whenOxygenDateIsInvalid() {
		String anInvalidOxygenDate = "anInvalidDate";

		assertThrows(genericException.class, () -> new OxygenDate(anInvalidOxygenDate));
	}

	@Test
	public void constructing_shouldCreateOxygenDate_whenOxygenIsValid() {
		// TODO create this test
	}

}
