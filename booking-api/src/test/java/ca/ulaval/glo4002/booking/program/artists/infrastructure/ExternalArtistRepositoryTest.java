package ca.ulaval.glo4002.booking.program.artists.infrastructure;

import ca.ulaval.glo4002.booking.program.artists.domain.Artist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ExternalArtistRepositoryTest {

    private ExternalArtistRepository repository;
    private ExternalArtistClient externalArtistClient;
    private ExternalArtistConverter externalArtistConverter;

    @BeforeEach
    void setUpRepository() {
        externalArtistClient = mock(ExternalArtistClient.class);
        externalArtistConverter = mock(ExternalArtistConverter.class);

        repository = new ExternalArtistRepository(externalArtistClient, externalArtistConverter);
    }

    @Test
    void findAll_shouldUseClientToGetArtists() {
        repository.findAll();

        verify(externalArtistClient).getArtists();
    }

    @Test
    void findAll_shouldConvertArtists() {
        repository.findAll();

        verify(externalArtistConverter).convert(any());
    }

    @Test
    void findAll_shouldReturnArtists() {
        List<Artist> expectedArtists = Collections.nCopies(2, mock(Artist.class));
        when(externalArtistConverter.convert(any())).thenReturn(expectedArtists);

        List<Artist> artists = repository.findAll();

        assertEquals(expectedArtists.size(), artists.size());
    }
}