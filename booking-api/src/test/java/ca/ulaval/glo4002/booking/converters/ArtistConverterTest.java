package ca.ulaval.glo4002.booking.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.domain.artist.BookingArtist;
import ca.ulaval.glo4002.booking.domain.artist.ExternalArtist;
import ca.ulaval.glo4002.booking.domain.artist.ExternalArtistAvailability;
import ca.ulaval.glo4002.booking.domain.events.EventDate;

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
		
		assertEquals(0, convertedArtists.get(0).getAvailabilities().size());
	}
	
	@Test
	public void convert_convertedArtistsShouldHaveAvailabilitiesWhenExternalHasSome() {
		when(anExternalArtist.getAvailabilities()).thenReturn(new ArrayList<>());
		ExternalArtistAvailability anExternalArtistAvailability = mock(ExternalArtistAvailability.class);
		when(anExternalArtistAvailability.getDate()).thenReturn("2050-07-19");
		EventDate anExternalDate = new EventDate(LocalDate.parse("2050-07-19"));
		
		anExternalArtist.getAvailabilities().add(anExternalArtistAvailability);
		
		List<BookingArtist> convertedArtists = artistConverter.convert(externalArtists);
		
		assertEquals(1, convertedArtists.get(0).getAvailabilities().size());
		assertEquals(anExternalDate, convertedArtists.get(0).getAvailabilities().get(0).getDate());
	}

}
