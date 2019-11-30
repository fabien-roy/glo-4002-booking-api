package ca.ulaval.glo4002.booking.program.artists.infrastructure;

import ca.ulaval.glo4002.booking.program.artists.domain.Artist;
import ca.ulaval.glo4002.booking.program.rest.exceptions.InvalidProgramException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
    void findAll_shouldCallClient() {
        repository.findAll();

        verify(externalArtistClient).getArtists();
    }

    @Test
    void findAll_shouldCallConverter() {
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

    @Test
    void findByName_shouldReturnArtistWithRequestedName() {
        String requestedName = "requestedName";
        String otherName = "otherName";
        Artist expectedArtist = mock(Artist.class);
        Artist otherArtist = mock(Artist.class);
        when(expectedArtist.getName()).thenReturn(requestedName);
        when(otherArtist.getName()).thenReturn(otherName);
        when(externalArtistConverter.convert(any())).thenReturn(Arrays.asList(expectedArtist, otherArtist));

        Artist artist = repository.findByName(requestedName);

        assertEquals(expectedArtist, artist);
    }

    @Test
    void findByName_shouldThrowInvalidProgramException_whenNameDoesNotExist() {
        Artist aArtist = mock(Artist.class);
        when(aArtist.getName()).thenReturn("aArtistName");
        when(externalArtistConverter.convert(any())).thenReturn(Collections.singletonList(aArtist));
        String anInvalidArtistName = "anInvalidArtistName";

        assertThrows(InvalidProgramException.class, () -> repository.findByName(anInvalidArtistName));
    }
}