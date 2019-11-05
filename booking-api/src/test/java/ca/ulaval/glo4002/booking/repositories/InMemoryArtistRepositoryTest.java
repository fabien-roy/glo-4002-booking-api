package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domain.Artist;
import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.exceptions.InvalidProgramException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryArtistRepositoryTest {

    private ArtistRepository repository;

    @BeforeEach
    void setUpRepository() {
        repository = new InMemoryArtistRepository();
    }

    @Test
    void constructing_shouldFillArtistsWithPreparedDate() {
        assertFalse(repository.getAll().isEmpty());
    }

    @Test
    void getByName_shouldReturnArtist() {
        String artistName = repository.getAll().get(0).getName();

        Artist artist = repository.getByName(artistName);

        assertEquals(artistName, artist.getName());
    }

    @Test
    void getByName_shouldThrowInvalidProgramException_whenArtistDoesNotExist() {
        String aNonExistentArtistName = "aNonExistentArtistName";

        assertThrows(InvalidProgramException.class, () -> repository.getByName(aNonExistentArtistName));
    }
}