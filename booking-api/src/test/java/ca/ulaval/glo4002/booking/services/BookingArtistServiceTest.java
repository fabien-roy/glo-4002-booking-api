package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.exceptions.InvalidFormatException;
import ca.ulaval.glo4002.booking.repositories.EventRepository;
import ca.ulaval.glo4002.organisation.repositories.ArtistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class BookingArtistServiceTest {

    ArtistService service;
    ArtistRepository artistRepository;

    @BeforeEach
    void setUpService() {
        artistRepository = mock(ArtistRepository.class);

        service = new ArtistService(artistRepository);
    }

    @Test
    void getAll_shouldThrowInvalidFormatException_whenOrderByIsInvalid() {
        String anInvalidOrderBy = "anInvalidOrderBy";

        assertThrows(InvalidFormatException.class, () -> service.getAll(anInvalidOrderBy));
    }

    @Test
    void getAll_shouldReturnAllArtistNames_whenOrderByIsNull() {
        // TODO
    }

    @Test
    void getAll_shouldReturnAllArtistNamesOrderedByPopularity_whenOrderByIsNull() {
        // TODO
    }

    @Test
    void getAll_shouldReturnAllArtistNamesOrderedByPopularityAndByCost_whenOrderByIsNull() {
        // TODO
    }

    @Test
    void getAll_shouldReturnAllArtistNamesOrderedByCost_whenOrderByIsByCost() {
        // TODO
    }

    @Test
    void getAll_shouldReturnAllArtistNamesOrderedByPopularity_whenOrderByIsByPopularity() {
        // TODO
    }
}