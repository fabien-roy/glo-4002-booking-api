package ca.ulaval.glo4002.booking.program.artists.infrastructure;

import ca.ulaval.glo4002.booking.profits.domain.Money;
import ca.ulaval.glo4002.booking.program.artists.domain.Artist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExternalArtistConverterTest {

	private ExternalArtistConverter converter;

	@BeforeEach
	void setUpConverter() {
		converter = new ExternalArtistConverter();
	}

	@Test
	void convert_shouldSetId_whenThereIsASingleArtist() {
		int expectedId = 1;
		ExternalArtist externalArtist = mock(ExternalArtist.class);
		when(externalArtist.getId()).thenReturn(expectedId);
		List<ExternalArtist> externalArtists = Collections.singletonList(externalArtist);

		List<Artist> artists = converter.convert(externalArtists);

		assertEquals(expectedId, artists.get(0).getId().getValue());
	}

	@Test
	void convert_shouldSetIds_whenThereIsAreMultipleArtists() {
		int expectedId = 1;
		int otherExpectedId = 2;
		ExternalArtist externalArtist = mock(ExternalArtist.class);
		ExternalArtist otherExternalArtist = mock(ExternalArtist.class);
		when(externalArtist.getId()).thenReturn(expectedId);
		when(otherExternalArtist.getId()).thenReturn(otherExpectedId);
		List<ExternalArtist> externalArtists = Arrays.asList(externalArtist, otherExternalArtist);

		List<Artist> artists = converter.convert(externalArtists);

		assertEquals(expectedId, artists.get(0).getId().getValue());
		assertEquals(otherExpectedId, artists.get(1).getId().getValue());
	}

	@Test
	void convert_shouldSetName_whenThereIsASingleArtist() {
	    String expectedName = "expectedName";
		ExternalArtist externalArtist = mock(ExternalArtist.class);
		when(externalArtist.getName()).thenReturn(expectedName);
		List<ExternalArtist> externalArtists = Collections.singletonList(externalArtist);

		List<Artist> artists = converter.convert(externalArtists);

		assertEquals(expectedName, artists.get(0).getName());
	}

	@Test
	void convert_shouldSetNames_whenThereIsAreMultipleArtists() {
		String expectedName = "expectedName";
		String otherExpectedName = "otherExpectedName";
		ExternalArtist externalArtist = mock(ExternalArtist.class);
		ExternalArtist otherExternalArtist = mock(ExternalArtist.class);
		when(externalArtist.getName()).thenReturn(expectedName);
		when(otherExternalArtist.getName()).thenReturn(otherExpectedName);
		List<ExternalArtist> externalArtists = Arrays.asList(externalArtist, otherExternalArtist);

		List<Artist> artists = converter.convert(externalArtists);

		assertEquals(expectedName, artists.get(0).getName());
		assertEquals(otherExpectedName, artists.get(1).getName());
	}

	@Test
	void convert_shouldSetCost_whenThereIsASingleArtist() {
		int artistCost = 100;
		Money expectedCost = new Money(BigDecimal.valueOf(artistCost));
		ExternalArtist externalArtist = mock(ExternalArtist.class);
		when(externalArtist.getPrice()).thenReturn(artistCost);
		List<ExternalArtist> externalArtists = Collections.singletonList(externalArtist);

		List<Artist> artists = converter.convert(externalArtists);

		assertEquals(expectedCost, artists.get(0).getCost());
	}

	@Test
	void convert_shouldSetCosts_whenThereIsAreMultipleArtists() {
		int artistCost = 100;
		int otherArtistCost = 200;
		Money expectedCost = new Money(BigDecimal.valueOf(artistCost));
		Money otherExpectedCost = new Money(BigDecimal.valueOf(otherArtistCost));
		ExternalArtist externalArtist = mock(ExternalArtist.class);
		ExternalArtist otherExternalArtist = mock(ExternalArtist.class);
		when(externalArtist.getPrice()).thenReturn(artistCost);
		when(otherExternalArtist.getPrice()).thenReturn(otherArtistCost);
		List<ExternalArtist> externalArtists = Arrays.asList(externalArtist, otherExternalArtist);

		List<Artist> artists = converter.convert(externalArtists);

		assertEquals(expectedCost, artists.get(0).getCost());
		assertEquals(otherExpectedCost, artists.get(1).getCost());
	}

	@Test
	void convert_shouldSetNumberOfPeople_whenThereIsASingleArtist() {
		int expectedNumberOfPeople = 1;
		ExternalArtist externalArtist = mock(ExternalArtist.class);
		when(externalArtist.getNbPeople()).thenReturn(expectedNumberOfPeople);
		List<ExternalArtist> externalArtists = Collections.singletonList(externalArtist);

		List<Artist> artists = converter.convert(externalArtists);

		assertEquals(expectedNumberOfPeople, artists.get(0).getNumberOfPeople());
	}

	@Test
	void convert_shouldSetNumbersOfPeople_whenThereIsAreMultipleArtists() {
		int expectedNumberOfPeople = 1;
		int otherExpectedNumberOfPeople = 2;
		ExternalArtist externalArtist = mock(ExternalArtist.class);
		ExternalArtist otherExternalArtist = mock(ExternalArtist.class);
		when(externalArtist.getNbPeople()).thenReturn(expectedNumberOfPeople);
		when(otherExternalArtist.getNbPeople()).thenReturn(otherExpectedNumberOfPeople);
		List<ExternalArtist> externalArtists = Arrays.asList(externalArtist, otherExternalArtist);

		List<Artist> artists = converter.convert(externalArtists);

		assertEquals(expectedNumberOfPeople, artists.get(0).getNumberOfPeople());
		assertEquals(otherExpectedNumberOfPeople, artists.get(1).getNumberOfPeople());
	}

	@Test
	void convert_shouldSetPopularityRank_whenThereIsASingleArtist() {
		int expectedPopularityRank = 1;
		ExternalArtist externalArtist = mock(ExternalArtist.class);
		when(externalArtist.getPopularityRank()).thenReturn(expectedPopularityRank);
		List<ExternalArtist> externalArtists = Collections.singletonList(externalArtist);

		List<Artist> artists = converter.convert(externalArtists);

		assertEquals(expectedPopularityRank, artists.get(0).getPopularityRank());
	}

	@Test
	void convert_shouldSetPopularityRanks_whenThereIsAreMultipleArtists() {
		int expectedPopularityRank = 1;
		int otherExpectedPopularityRank = 2;
		ExternalArtist externalArtist = mock(ExternalArtist.class);
		ExternalArtist otherExternalArtist = mock(ExternalArtist.class);
		when(externalArtist.getPopularityRank()).thenReturn(expectedPopularityRank);
		when(otherExternalArtist.getPopularityRank()).thenReturn(otherExpectedPopularityRank);
		List<ExternalArtist> externalArtists = Arrays.asList(externalArtist, otherExternalArtist);

		List<Artist> artists = converter.convert(externalArtists);

		assertEquals(expectedPopularityRank, artists.get(0).getPopularityRank());
		assertEquals(otherExpectedPopularityRank, artists.get(1).getPopularityRank());
	}
}
