package ca.ulaval.glo4002.booking.converters;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ca.ulaval.glo4002.booking.domain.artist.ExternalArtist;
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
	public void convert_convertedArtistsShouldAllBeConverted() {
		artistConverter.convert(externalArtists);
		
		Mockito.verify(artistRepository).saveAll(Mockito.anyList());

	}

}
