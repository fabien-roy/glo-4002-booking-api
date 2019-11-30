package ca.ulaval.glo4002.booking.program.artists.infrastructure;

import ca.ulaval.glo4002.booking.program.rest.exceptions.InvalidProgramException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ExternalArtistRepositoryTest {

    private ExternalArtistRepository repository;
    private ExternalArtistClient externalArtistClient;
    private ExternalArtistConverter externalArtistConverter;

    @BeforeEach
    void setUpRepository() {
        externalArtistConverter = mock(ExternalArtistConverter.class);

        repository = new ExternalArtistRepository(externalArtistClient, externalArtistConverter);
    }

    @BeforeEach
    void setUpClient() {
        externalArtistClient = mock(ExternalArtistClient.class);

        // TODO : Mock behaviour of client
    }

    @Test
    void findAll_shouldCallClient() {
        // TODO
    }

    @Test
    void findAll_shouldCallConverter() {
        // TODO
    }

    @Test
    void findAll_shouldReturnArtists() {
        // TODO
    }

    @Test
    void findByName_shouldReturnArtistWithRequestedName() {
        // TODO
    }

    @Test
    void findByName_shouldThrowInvalidProgramException_whenNameDoesNotExist() {
        String anInvalidArtistName = "anInvalidArtistName";

        assertThrows(InvalidProgramException.class, () -> repository.findByName(anInvalidArtistName));
    }
}