package ca.ulaval.glo4002.booking.passes.domain;

import java.math.BigDecimal;

import ca.ulaval.glo4002.booking.numbers.Number;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.profits.domain.Money;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
		EventDate eventDate = EventDate.getDefaultStartEventDate().plusDays(2);

		pass = new Pass(null, null, eventDate);

		assertEquals(pass.getEventDate(), eventDate);
	}
}
