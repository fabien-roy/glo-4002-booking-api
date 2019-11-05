package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domain.Artist;
import ca.ulaval.glo4002.booking.dto.events.ArtistListDto;
import ca.ulaval.glo4002.booking.repositories.ArtistRepository;
import ca.ulaval.glo4002.booking.repositories.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    void getAll_shouldReturnAllArtistNames_whenOrderByIsNull() {
        String expectedArtistName = "expectedArtistName";
        Artist artist = mock(Artist.class);
        when(artist.getName()).thenReturn(expectedArtistName);
        when(artistRepository.getAll()).thenReturn(Collections.singletonList(artist));

        ArtistListDto artistListDto = service.getAll(null);

        assertEquals(expectedArtistName, artistListDto.getArtists().get(0));
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