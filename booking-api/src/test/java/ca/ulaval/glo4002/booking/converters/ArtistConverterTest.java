package ca.ulaval.glo4002.booking.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.domain.artist.BookingArtist;
import ca.ulaval.glo4002.booking.domain.artist.ExternalArtist;
import ca.ulaval.glo4002.booking.domain.artist.ExternalArtistAvailability;

public class ArtistConverterTest {
	
	private ArtistConverter artistConverter;
	private static ExternalArtist anExternalArtist;
	private static ExternalArtist anotherExternalArtist;
	private static List<ExternalArtist> externalArtists;
	
	@BeforeAll
	public static void artistsSetUp() {
		externalArtists = new ArrayList<>();
		anExternalArtist = mock(ExternalArtist.class);
		anotherExternalArtist = mock(ExternalArtist.class);
		when(anExternalArtist.getName()).thenReturn("A Name");
		when(anotherExternalArtist.getName()).thenReturn("Another Name");
		externalArtists.add(anExternalArtist);
		externalArtists.add(anotherExternalArtist);
	}
	
	@BeforeEach
	public void converterSetUp() {
		artistConverter = new ArtistConverter();
	}
	
	@Test
	public void convert_convertedArtistsShouldAllBeConverted() {
		List<BookingArtist> convertedArtists = artistConverter.convert(externalArtists);
		
		assertEquals(externalArtists.size(), convertedArtists.size());
	}
	
	@Test
	public void convert_convertedArtistsShouldHaveRightName() {
		List<BookingArtist> convertedArtists = artistConverter.convert(externalArtists);
		
		assertEquals(anExternalArtist.getName(), convertedArtists.get(0).getName());
		assertEquals(anotherExternalArtist.getName(), convertedArtists.get(1).getName());
	}
	
	@Test
	public void convert_convertedArtistsShouldHaveNoAvailabilitiesWhenExternalHasNone() {
		List<BookingArtist> convertedArtists = artistConverter.convert(externalArtists);
		
		assertTrue(convertedArtists.get(0).getAvailabilities().isEmpty());
	}
	
	@Test
	public void convert_convertedArtistsShouldHaveAvailabilitiesWhenExternalHasSome() {
		ExternalArtistAvailability anExternalArtistAvailability = mock(ExternalArtistAvailability.class);
		anExternalArtist.getAvailabilities().add(anExternalArtistAvailability);
		
		List<BookingArtist> convertedArtists = artistConverter.convert(externalArtists);
		
		assertEquals(1, convertedArtists.get(0).getAvailabilities().size());
	}

}
