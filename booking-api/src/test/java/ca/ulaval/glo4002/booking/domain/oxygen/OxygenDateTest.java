package ca.ulaval.glo4002.booking.domain.oxygen;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import ca.ulaval.glo4002.booking.exceptions.oxygen.InvalidOxygenDateFormatException;
import org.junit.jupiter.api.Test;

class OxygenDateTest {

	private OxygenDate oxygenDate;

	@Test
	public void constructing_shouldThrowInvalidOxygenDateException_whenOxygenDateIsInvalid() {
		String anInvalidOxygenDate = "anInvalidDate";

		assertThrows(InvalidOxygenDateFormatException.class, () -> new OxygenDate(anInvalidOxygenDate));
	}

	@Test
	public void constructingWithString_shouldCreateOxygenDate_whenOxygenIsValid() {
		LocalDateTime expectedValue = LocalDateTime.of(2050, 7, 1, 0, 0);
		ZonedDateTime expectedZonedValue = ZonedDateTime.of(expectedValue, ZoneId.systemDefault());

		oxygenDate = new OxygenDate(expectedZonedValue.toString());

		assertEquals(expectedValue, oxygenDate.getValue());
	}

	@Test
	public void constructingWithDate_shouldCreateOxygenDate_whenOxygenIsValid() {
		LocalDate expectedValue = LocalDate.of(2050, 7, 1);

		oxygenDate = new OxygenDate(expectedValue);

		assertEquals(expectedValue.atStartOfDay(), oxygenDate.getValue());
	}

	@Test
	public void add10Days_shouldReturnDate10DaysLater() {
		LocalDate beginningDate = LocalDate.of(2050, 7, 1);
		LocalDateTime expectedDate = beginningDate.atStartOfDay();
		expectedDate = expectedDate.plusDays(10);

		oxygenDate = new OxygenDate(beginningDate);
		oxygenDate.addDays(10);

		assertEquals(expectedDate, oxygenDate.getValue());
	}
}
