package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domain.Artist;
import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.exceptions.InvalidProgramException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class InMemoryArtistRepositoryTest {

    private ArtistRepository repository;

    @BeforeEach
    void setUpRepository() {
        repository = new InMemoryArtistRepository();
    }

    @Test
    void getByName_shouldReturnArtist() {
        String artistName = "aArtist";
        Artist expectedArtist = new Artist(artistName, mock(Money.class), 1);
        repository.addAll(Collections.singletonList(expectedArtist));

        Artist artists = repository.getByName(artistName);

        assertEquals(expectedArtist, artists);
    }

    @Test
    void getByName_shouldReturnArtist_whenThereAreMultipleArtists() {
        String artistName = "aArtist";
        Artist expectedArtist = new Artist(artistName, mock(Money.class), 1);
        Artist anotherArtist = new Artist("anotherArtist", mock(Money.class), 1);
        repository.addAll(Arrays.asList(expectedArtist, anotherArtist));

        Artist artists = repository.getByName(artistName);

        assertEquals(expectedArtist, artists);
    }

    @Test
    void getByName_shouldThrowInvalidProgramException_whenThereIsNoArtist() {
        String aNonExistentArtistName = "aNonExistentArtistName";

        assertThrows(InvalidProgramException.class, () -> repository.getByName(aNonExistentArtistName));
    }

    @Test
    void getByName_shouldThrowInvalidProgramException_whenArtistDoesNotExist() {
        String aNonExistentArtistName = "aNonExistentArtistName";
        Artist anArtist = new Artist("aArtist", mock(Money.class), 1);
        repository.addAll(Collections.singletonList(anArtist));

        assertThrows(InvalidProgramException.class, () -> repository.getByName(aNonExistentArtistName));
    }
}