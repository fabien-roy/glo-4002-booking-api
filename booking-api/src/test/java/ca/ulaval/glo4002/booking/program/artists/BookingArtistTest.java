package ca.ulaval.glo4002.booking.program.artists;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.profits.domain.Money;

class BookingArtistTest {

	BookingArtist bookingArtist;

	@Test
	void constructing_shouldSetCorrectId() {
		Integer id = 1;

		bookingArtist = new BookingArtist(id, null, null, null, null, null, null);

		assertEquals(bookingArtist.getId(), id);
	}

	@Test
	void constructing_shouldSetCorrectName() {
		String name = "nom";

		bookingArtist = new BookingArtist(null, name, null, null, null, null, null);

		assertEquals(bookingArtist.getName(), name);
	}

	@Test
	void constructing_shouldSetCorrectCost() {
		BigDecimal bigDecimalCost = new BigDecimal(40);
		Money cost = new Money(bigDecimalCost);

		bookingArtist = new BookingArtist(null, null, cost, null, null, null, null);

		assertEquals(bookingArtist.getCost(), cost);
	}

	@Test
	void constructing_shouldSetCorrectNumberOfPeople() {
		Integer numberOfPeople = 3;

		bookingArtist = new BookingArtist(null, null, null, numberOfPeople, null, null, null);

		assertEquals(bookingArtist.getNumberOfPeople(), numberOfPeople);
	}

	@Test
	void constructing_shouldSetCorrectMusicStyle() {
		String musicStyle = "style";

		bookingArtist = new BookingArtist(null, null, null, null, musicStyle, null, null);

		assertEquals(bookingArtist.getMusicStyle(), musicStyle);
	}

	@Test
	void constructing_shouldSetCorrectPopularityRank() {
		Integer popularityRank = 5;

		bookingArtist = new BookingArtist(null, null, null, null, null, popularityRank, null);

		assertEquals(bookingArtist.getPopularityRank(), popularityRank);
	}

	@Test
	void constructing_shouldSetCorrectAvailabilities() {
		List<Availability> availabilities = new ArrayList<>();

		bookingArtist = new BookingArtist(null, null, null, null, null, null, availabilities);

		assertEquals(bookingArtist.getAvailabilities(), availabilities);
	}

}
