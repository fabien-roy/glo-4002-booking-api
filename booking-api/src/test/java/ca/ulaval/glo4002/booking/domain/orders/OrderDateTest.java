package ca.ulaval.glo4002.booking.domain.orders;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.exceptions.InvalidFormatException;
import ca.ulaval.glo4002.booking.exceptions.InvalidOrderDateException;

class OrderDateTest {

	@Test
	void constructing_shouldSetCorrectDate() {
		LocalDateTime expectedDate = OrderDate.START_DATE_TIME.plusDays(1);
		ZonedDateTime expectedZonedDate = ZonedDateTime.of(expectedDate, ZoneId.systemDefault());

		OrderDate date = new OrderDate(expectedZonedDate.toString());

		assertEquals(expectedDate, date.getDate());
	}

	@Test
	void constructing_shouldThrowInvalidFormatException_whenOrderDateIsInvalid() {
		String anInvalidOrderDate = "anInvalidDate";

		assertThrows(InvalidFormatException.class, () -> new OrderDate(anInvalidOrderDate));
	}

	@Test
	void constructing_shouldThrowInvalidOrderDateException_whenOrderDateIsUnderBounds() {
		LocalDateTime aUnderBoundDate = OrderDate.START_DATE_TIME.minusDays(1);
		ZonedDateTime aUnderBoundZonedDate = ZonedDateTime.of(aUnderBoundDate, ZoneId.systemDefault());

		assertThrows(InvalidOrderDateException.class, () -> new OrderDate(aUnderBoundZonedDate.toString()));
	}

	@Test
	void constructing_shouldThrowInvalidOrderDateException_whenOrderDateIsOverBounds() {
		LocalDateTime aOverBoundDate = OrderDate.END_DATE_TIME.plusDays(1);
		ZonedDateTime aOverBoundZonedDate = ZonedDateTime.of(aOverBoundDate, ZoneId.systemDefault());

		assertThrows(InvalidOrderDateException.class, () -> new OrderDate(aOverBoundZonedDate.toString()));
	}
}