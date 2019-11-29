package ca.ulaval.glo4002.booking.oxygen.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import ca.ulaval.glo4002.booking.oxygen.domain.OxygenDate;
import org.junit.jupiter.api.Test;

class OxygenDateTest {

	private OxygenDate oxygenDate;

	@Test
	public void constructingWithDate_shouldCreateOxygenDate_whenOxygenIsValid() {
		LocalDate expectedValue = LocalDate.of(2050, 7, 1);

		oxygenDate = new OxygenDate(expectedValue);

		assertEquals(expectedValue, oxygenDate.getValue());
	}

	@Test
	public void addDays_shouldReturnDateWithNumberDaysLater() {
		LocalDate beginningDate = LocalDate.of(2050, 7, 1);
		LocalDate expectedDate = beginningDate;
		expectedDate = expectedDate.plusDays(10);

		oxygenDate = new OxygenDate(beginningDate);
		oxygenDate.addDays(10);

		assertEquals(expectedDate, oxygenDate.getValue());
	}
}
