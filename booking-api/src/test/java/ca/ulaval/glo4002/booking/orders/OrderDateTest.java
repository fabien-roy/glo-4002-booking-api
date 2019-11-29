package ca.ulaval.glo4002.booking.orders;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import ca.ulaval.glo4002.booking.orders.rest.exceptions.InvalidOrderDateException;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.interfaces.rest.exceptions.InvalidFormatException;

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
		LocalDateTime anUnderBoundDate = OrderDate.START_DATE_TIME.minusDays(1);
		ZonedDateTime anUnderBoundZonedDate = ZonedDateTime.of(anUnderBoundDate, ZoneId.systemDefault());

		assertThrows(InvalidOrderDateException.class, () -> new OrderDate(anUnderBoundZonedDate.toString()));
	}

	@Test
	void constructing_shouldThrowInvalidOrderDateException_whenOrderDateIsOverBounds() {
		LocalDateTime anOverBoundDate = OrderDate.END_DATE_TIME.plusDays(1);
		ZonedDateTime anOverBoundZonedDate = ZonedDateTime.of(anOverBoundDate, ZoneId.systemDefault());

		assertThrows(InvalidOrderDateException.class, () -> new OrderDate(anOverBoundZonedDate.toString()));
	}
}