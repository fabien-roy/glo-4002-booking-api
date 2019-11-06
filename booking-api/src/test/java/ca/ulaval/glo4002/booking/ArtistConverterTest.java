package ca.ulaval.glo4002.booking;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.organisation.repositories.ArtistRepository;

public class ArtistConverterTest {

	private static ArtistRepository artistRepository;
	private static ArtistConverter artistConverter;
	
	@BeforeAll
	public static void converterSetUp() {
		artistRepository = mock(ArtistRepository.class);
		artistConverter = new ArtistConverter(artistRepository);
	}
	
	@Test
	public void convertExternalArtists_repositoryShouldCallFindAll() {
		artistConverter.convertExternalArtists();
		
		verify(artistRepository, times(1)).findAll();
	}
	
	
	
}
