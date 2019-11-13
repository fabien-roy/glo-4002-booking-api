package ca.ulaval.glo4002.booking.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.domain.artist.BookingArtist;
import ca.ulaval.glo4002.booking.domain.artist.ExternalArtist;
import ca.ulaval.glo4002.booking.domain.artist.ExternalArtistAvailability;
import ca.ulaval.glo4002.booking.domain.events.EventDate;
import ca.ulaval.glo4002.booking.repositories.ArtistRepository;

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
		artistRepository = mock(ArtistRepository.class);
		artistConverter = new ArtistConverter(artistRepository);
	}
	
	@Test
	@Ignore("WIP")
	public void convert_convertedArtistsShouldAllBeConverted() {
		artistConverter.convert(externalArtists);

	}
	
	@Test
	@Ignore("WIP")
	public void convert_convertedArtistsShouldHaveRightName() {
		artistConverter.convert(externalArtists);

	}
	
	@Test
	@Ignore("WIP")
	public void convert_convertedArtistsShouldHaveNoAvailabilitiesWhenExternalHasNone() {
		artistConverter.convert(externalArtists);

	}
	
	@Test
	@Ignore("WIP")
	public void convert_convertedArtistsShouldHaveAvailabilitiesWhenExternalHasSome() {
		when(anExternalArtist.getAvailabilities()).thenReturn(new ArrayList<>());
		ExternalArtistAvailability anExternalArtistAvailability = mock(ExternalArtistAvailability.class);
		when(anExternalArtistAvailability.getDate()).thenReturn("2050-07-19");
		EventDate anExternalDate = new EventDate(LocalDate.parse("2050-07-19"));
		
		anExternalArtist.getAvailabilities().add(anExternalArtistAvailability);
		
		artistConverter.convert(externalArtists);

	}

}
