package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.domain.artist.BookingArtist;
import ca.ulaval.glo4002.organisation.domain.Artist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ArtistFactoryTest {

	private static ArtistFactory artistFactory;
	
	@BeforeEach
	void setUpFactory() {
		artistFactory = new ArtistFactory();
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3})
	void buildAll_shouldCorrectAmountOfArtists(int artistAmount) {
		Artist aArtist = mockArtist("aName", 100, 1, "aMusicStyle", 1);

		List<BookingArtist> bookingArtists = artistFactory.buildAll(Collections.nCopies(artistAmount, aArtist));

		assertEquals(artistAmount, bookingArtists.size());
	}

	@Test
	void buildAll_shouldBuildArtistWithCorrectName() {
		String expectedName = "aName";
		Artist aArtist = mockArtist(expectedName, 100, 1, "aMusicStyle", 1);

		List<BookingArtist> bookingArtists = artistFactory.buildAll(Collections.singletonList(aArtist));
		BookingArtist bookingArtist = bookingArtists.get(0);

		assertEquals(expectedName, bookingArtist.getName());
	}

	@Test
	void buildAll_shouldBuildArtistsWithCorrectName_whenThereAreMultipleArtists() {
		String expectedName = "aName";
		String expectedOtherName = "anotherName";
		Artist aArtist = mockArtist(expectedName, 100, 1, "aMusicStyle", 1);
		Artist anotherArtist = mockArtist(expectedOtherName, 100, 1, "aMusicStyle", 1);

		List<BookingArtist> bookingArtists = artistFactory.buildAll(Arrays.asList(aArtist, anotherArtist));

		assertTrue(bookingArtists.stream().anyMatch(artist -> expectedName.equals(artist.getName())));
		assertTrue(bookingArtists.stream().anyMatch(artist -> expectedOtherName.equals(artist.getName())));
	}

	@Test
	void buildAll_shouldBuildArtistWithCorrectCost() {
		Integer expectedCost = 100;
		Artist aArtist = mockArtist("aName", expectedCost, 1, "aMusicStyle", 1);

		List<BookingArtist> bookingArtists = artistFactory.buildAll(Collections.singletonList(aArtist));
		BookingArtist bookingArtist = bookingArtists.get(0);

		assertEquals(expectedCost, bookingArtist.getCost().getValue().intValue());
	}

	@Test
	void buildAll_shouldBuildArtistsWithCorrectCost_whenThereAreMultipleArtists() {
		Integer expectedCost = 100;
		Integer expectedOtherCost = 200;
		Artist aArtist = mockArtist("aName", expectedCost, 1, "aMusicStyle", 1);
		Artist anotherArtist = mockArtist("aName", expectedOtherCost, 1, "aMusicStyle", 1);

		List<BookingArtist> bookingArtists = artistFactory.buildAll(Arrays.asList(aArtist, anotherArtist));

		assertTrue(bookingArtists.stream().anyMatch(artist -> expectedCost.equals(artist.getCost().getValue().intValue())));
		assertTrue(bookingArtists.stream().anyMatch(artist -> expectedOtherCost.equals(artist.getCost().getValue().intValue())));
	}

	@Test
	void buildAll_shouldBuildArtistsWithCorrectNumberOfPeople() {
		Integer expectedNumberOfPeople = 1;
		Artist aArtist = mockArtist("aName", 100, expectedNumberOfPeople, "aMusicStyle", 1);

		List<BookingArtist> bookingArtists = artistFactory.buildAll(Collections.singletonList(aArtist));
		BookingArtist bookingArtist = bookingArtists.get(0);

		assertEquals(expectedNumberOfPeople, bookingArtist.getNumberOfPeople());
	}

	@Test
	void buildAll_shouldBuildArtistsWithCorrectNumberOfPeople_whenThereAreMultipleArtists() {
		Integer expectedNumberOfPeople = 1;
		Integer expectedOtherNumberOfPeople = 2;
		Artist aArtist = mockArtist("aName", 100, expectedNumberOfPeople, "aMusicStyle", 1);
		Artist anotherArtist = mockArtist("aName", 200, expectedOtherNumberOfPeople, "aMusicStyle", 1);

		List<BookingArtist> bookingArtists = artistFactory.buildAll(Arrays.asList(aArtist, anotherArtist));

		assertTrue(bookingArtists.stream().anyMatch(artist -> expectedNumberOfPeople.equals(artist.getNumberOfPeople())));
		assertTrue(bookingArtists.stream().anyMatch(artist -> expectedOtherNumberOfPeople.equals(artist.getNumberOfPeople())));
	}

	@Test
	void buildAll_shouldBuildArtistsWithCorrectMusicStyle() {
		String expectedMusicStyle = "aMusicStyle";
		Artist aArtist = mockArtist("aName", 100, 1, expectedMusicStyle, 1);

		List<BookingArtist> bookingArtists = artistFactory.buildAll(Collections.singletonList(aArtist));
		BookingArtist bookingArtist = bookingArtists.get(0);

		assertEquals(expectedMusicStyle, bookingArtist.getMusicStyle());
	}

	@Test
	void buildAll_shouldBuildArtistsWithCorrectMusicStyle_whenThereAreMultipleArtists() {
		String expectedMusicStyle = "aMusicStyle";
		String expectedOtherMusicStyle = "anotherMusicStyle";
		Artist aArtist = mockArtist("aName", 100, 1, expectedMusicStyle, 1);
		Artist anotherArtist = mockArtist("aName", 100, 1, expectedOtherMusicStyle, 1);

		List<BookingArtist> bookingArtists = artistFactory.buildAll(Arrays.asList(aArtist, anotherArtist));

		assertTrue(bookingArtists.stream().anyMatch(artist -> expectedMusicStyle.equals(artist.getMusicStyle())));
		assertTrue(bookingArtists.stream().anyMatch(artist -> expectedOtherMusicStyle.equals(artist.getMusicStyle())));
	}

	@Test
	void buildAll_shouldBuildArtistsWithCorrectPopularityRank() {
		Integer expectedPopularityRank = 1;
		Artist aArtist = mockArtist("aName", 100, 1, "aMusicStyle", expectedPopularityRank);

		List<BookingArtist> bookingArtists = artistFactory.buildAll(Collections.singletonList(aArtist));
		BookingArtist bookingArtist = bookingArtists.get(0);

		assertEquals(expectedPopularityRank, bookingArtist.getPopularityRank());
	}

	@Test
	void buildAll_shouldBuildArtistsWithCorrectPopularityRank_whenThereAreMultipleArtists() {
		Integer expectedPopularityRank = 1;
		Integer expectedOtherPopularityRank = 2;
		Artist aArtist = mockArtist("aName", 100, 1, "aMusicStyle", expectedPopularityRank);
		Artist anotherArtist = mockArtist("aName", 100, 1, "aMusicStyle", expectedOtherPopularityRank);

		List<BookingArtist> bookingArtists = artistFactory.buildAll(Arrays.asList(aArtist, anotherArtist));

		assertTrue(bookingArtists.stream().anyMatch(artist -> expectedPopularityRank.equals(artist.getPopularityRank())));
		assertTrue(bookingArtists.stream().anyMatch(artist -> expectedOtherPopularityRank.equals(artist.getPopularityRank())));
	}

	private Artist mockArtist(String name, Integer price, Integer nbPeople, String musicStyle, Integer popularityRank) {
	    Artist mockedArtist = mock(Artist.class);

	    when(mockedArtist.getName()).thenReturn(name);
		when(mockedArtist.getPrice()).thenReturn(price);
		when(mockedArtist.getNbPeople()).thenReturn(nbPeople);
		when(mockedArtist.getMusicStyle()).thenReturn(musicStyle);
		when(mockedArtist.getPopularityRank()).thenReturn(popularityRank);

		return mockedArtist;
	}
}
