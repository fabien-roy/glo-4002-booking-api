package ca.ulaval.glo4002.booking.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import ca.ulaval.glo4002.booking.domain.events.EventDate;
import ca.ulaval.glo4002.booking.repositories.ArtistRepository;
import ca.ulaval.glo4002.booking.repositories.InMemoryArtistRepository;

public class ArtistConverterTest {
	
	private ArtistConverter artistConverter;
	private ArtistRepository artistRepository;
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
		artistRepository = new InMemoryArtistRepository();
		artistConverter = new ArtistConverter(artistRepository);
	}
	
	@Test
	public void convert_convertedArtistsShouldAllBeConverted() {
		artistConverter.convert(externalArtists);
		
		List<BookingArtist> artists = artistRepository.findAll();
		
		assertEquals(externalArtists.size(), artists.size());

	}
	
	@Test
	public void convert_convertArtistsShouldHaveRightNames() {
		artistConverter.convert(externalArtists);
		
		List<BookingArtist> artists = artistRepository.findAll();
		
		assertEquals(externalArtists.get(0).getName(), artists.get(0).getName());
		assertEquals(externalArtists.get(1).getName(), artists.get(1).getName());
	}
	
	@Test
	public void convert_convertArtistsShouldHaveAvailabilities_whenExternalHasSome() {
		when(anExternalArtist.getAvailabilities()).thenReturn(new ArrayList<>());
		ExternalArtistAvailability externalArtistAvailability = mock(ExternalArtistAvailability.class);
		when(externalArtistAvailability.getDate()).thenReturn(EventDate.START_DATE.toString());
		externalArtists.get(0).getAvailabilities().add(externalArtistAvailability);
		
		artistConverter.convert(externalArtists);
		
		List<BookingArtist> artists = artistRepository.findAll();
		
		assertFalse(artists.get(0).getAvailabilities().isEmpty());
		assertEquals(externalArtists.get(0).getAvailabilities().size(),
				artists.get(0).getAvailabilities().size());
		
	}
	
	@Test
	public void convert_convertArtistsShouldNotHaveAvailabilities_whenExternalHasSomeOutsideFestival() {
		when(anExternalArtist.getAvailabilities()).thenReturn(new ArrayList<>());
		ExternalArtistAvailability externalArtistAvailability = mock(ExternalArtistAvailability.class);
		when(externalArtistAvailability.getDate()).thenReturn(EventDate.START_DATE.minusDays(1).toString());
		externalArtists.get(0).getAvailabilities().add(externalArtistAvailability);
		
		artistConverter.convert(externalArtists);
		
		List<BookingArtist> artists = artistRepository.findAll();
		
		assertTrue(artists.get(0).getAvailabilities().isEmpty());
		
	}
	
	

}
