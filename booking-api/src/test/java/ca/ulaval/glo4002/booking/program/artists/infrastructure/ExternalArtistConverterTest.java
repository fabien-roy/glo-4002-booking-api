package ca.ulaval.glo4002.booking.program.artists.infrastructure;

import ca.ulaval.glo4002.booking.program.artists.domain.Artist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

		assertEquals(expectedId, artists.get(0).getId());
	}

	@Test
	void convert_shouldSetId_whenThereIsAreMultipleArtists() {
		int expectedId = 1;
		int otherExpectedId = 2;
		ExternalArtist externalArtist = mock(ExternalArtist.class);
		ExternalArtist otherExternalArtist = mock(ExternalArtist.class);
		when(externalArtist.getId()).thenReturn(expectedId);
		when(otherExternalArtist.getId()).thenReturn(otherExpectedId);
		List<ExternalArtist> externalArtists = Arrays.asList(externalArtist, otherExternalArtist);

		List<Artist> artists = converter.convert(externalArtists);

		assertEquals(expectedId, artists.get(0).getId());
		assertEquals(otherExpectedId, artists.get(1).getId());
	}

	// TODO : Add rest of tests
}
