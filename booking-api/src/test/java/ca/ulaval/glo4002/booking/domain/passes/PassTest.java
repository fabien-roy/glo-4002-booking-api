package ca.ulaval.glo4002.booking.domain.passes;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.events.EventDate;
import ca.ulaval.glo4002.booking.domain.money.Money;

class PassTest {

	Pass pass;

	@Test
	void constructing_shouldSetCorrectPassNumber() {
		Number passNumber = new Number(2L);

		pass = new Pass(passNumber, null, null);

		assertEquals(pass.getPassNumber(), passNumber);
	}

	@Test
	void constructing_shouldSetCorrectPrice() {
		BigDecimal number = new BigDecimal(2.0);
		Money price = new Money(number);

		pass = new Pass(null, price, null);

		assertEquals(pass.getPrice(), price);
	}

	@Test
	void constructing_shouldSetCorrectEventDate() {
		LocalDate date = EventDate.START_DATE.plusDays(2);
		EventDate eventDate = new EventDate(date);

		pass = new Pass(null, null, eventDate);

		assertEquals(pass.getEventDate(), eventDate);
	}

}
