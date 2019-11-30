package ca.ulaval.glo4002.booking.program.artists.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.profits.domain.Money;

class ArtistTest {

	Artist artist;

	@Test
	void constructing_shouldSetCorrectId() {
		Integer id = 1;

		artist = new Artist(id, null, null, null, null, null, null);

		assertEquals(artist.getId(), id);
	}

	@Test
	void constructing_shouldSetCorrectName() {
		String name = "nom";

		artist = new Artist(null, name, null, null, null, null, null);

		assertEquals(artist.getName(), name);
	}

	@Test
	void constructing_shouldSetCorrectCost() {
		BigDecimal bigDecimalCost = new BigDecimal(40);
		Money cost = new Money(bigDecimalCost);

		artist = new Artist(null, null, cost, null, null, null, null);

		assertEquals(artist.getCost(), cost);
	}

	@Test
	void constructing_shouldSetCorrectNumberOfPeople() {
		Integer numberOfPeople = 3;

		artist = new Artist(null, null, null, numberOfPeople, null, null, null);

		assertEquals(artist.getNumberOfPeople(), numberOfPeople);
	}

	@Test
	void constructing_shouldSetCorrectMusicStyle() {
		String musicStyle = "style";

		artist = new Artist(null, null, null, null, musicStyle, null, null);

		assertEquals(artist.getMusicStyle(), musicStyle);
	}

	@Test
	void constructing_shouldSetCorrectPopularityRank() {
		Integer popularityRank = 5;

		artist = new Artist(null, null, null, null, null, popularityRank, null);

		assertEquals(artist.getPopularityRank(), popularityRank);
	}

	@Test
	void constructing_shouldSetCorrectAvailabilities() {
		List<Availability> availabilities = new ArrayList<>();

		artist = new Artist(null, null, null, null, null, null, availabilities);

		assertEquals(artist.getAvailabilities(), availabilities);
	}

}
