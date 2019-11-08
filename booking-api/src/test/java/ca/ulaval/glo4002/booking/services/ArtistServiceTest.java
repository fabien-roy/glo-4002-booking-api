package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.exceptions.InvalidFormatException;
import ca.ulaval.glo4002.booking.repositories.ArtistRepository;
import ca.ulaval.glo4002.booking.repositories.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class ArtistServiceTest {

    ArtistService service;
    ArtistRepository artistRepository;

    @BeforeEach
    void setUpService() {
        artistRepository = mock(ArtistRepository.class);
        EventRepository eventRepository = mock(EventRepository.class);

        service = new ArtistService(artistRepository, eventRepository);
    }

    @Test
    void getAll_shouldThrowInvalidFormatException_whenOrderByIsInvalid() {
        String anInvalidOrderBy = "anInvalidOrderBy";

        assertThrows(InvalidFormatException.class, () -> service.getAll(anInvalidOrderBy));
    }
/*
    @Test
    void getAll_shouldReturnAllArtistNames_whenOrderByIsNull() {
        String expectedArtistName = "expectedArtistName";
        BookingArtist artist = new BookingArtist(expectedArtistName, mock(Money.class), 1);
        when(artistRepository.getAll()).thenReturn(Collections.singletonList(artist));

        ArtistListDto artistListDto = service.getAll(null);

        assertEquals(expectedArtistName, artistListDto.getArtists().get(0));
    }
*/
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