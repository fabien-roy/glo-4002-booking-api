package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.dto.events.ArtistListDto;
import ca.ulaval.glo4002.booking.exceptions.InvalidFormatException;
import ca.ulaval.glo4002.organisation.domain.Artist;
import ca.ulaval.glo4002.organisation.repositories.ArtistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ArtistServiceTest {

    ArtistService service;
    ArtistRepository artistRepository;

    @BeforeEach
    void setUpService() {
        List<Artist> artists = getArtists();
        artistRepository = mock(ArtistRepository.class);
        when(artistRepository.findAll()).thenReturn(artists);

        service = new ArtistService(artistRepository);
    }

    @Test
    void getAll_shouldThrowInvalidFormatException_whenOrderByIsInvalid() {
        String anInvalidOrderBy = "anInvalidOrderBy";

        assertThrows(InvalidFormatException.class, () -> service.getAll(anInvalidOrderBy));
    }

    @Test
    void getAll_shouldReturnAllArtistNames_whenOrderByIsNull() {
        ArtistListDto artistListDto = service.getAll(null);

        assertFalse(artistListDto.getArtists().isEmpty());
    }

    @Test
    void getAll_shouldReturnAllArtistNamesOrderedByCost_whenOrderByIsByCost() {
        ArtistListDto artistListDto = service.getAll(null);

        assertEquals(getArtists().get(1).getName(), artistListDto.getArtists().get(0));
        assertEquals(getArtists().get(0).getName(), artistListDto.getArtists().get(1));
        assertEquals(getArtists().get(2).getName(), artistListDto.getArtists().get(2));
        assertEquals(getArtists().get(3).getName(), artistListDto.getArtists().get(3));
        assertEquals(getArtists().get(4).getName(), artistListDto.getArtists().get(4));
    }

    @Test
    void getAll_shouldReturnAllArtistNamesOrderedByPopularity_whenOrderByIsByPopularity() {
        ArtistListDto artistListDto = service.getAll(null);

        assertEquals(getArtists().get(1).getName(), artistListDto.getArtists().get(0));
        assertEquals(getArtists().get(3).getName(), artistListDto.getArtists().get(1));
        assertEquals(getArtists().get(0).getName(), artistListDto.getArtists().get(2));
        assertEquals(getArtists().get(2).getName(), artistListDto.getArtists().get(3));
        assertEquals(getArtists().get(4).getName(), artistListDto.getArtists().get(4));
    }

    private List<Artist> getArtists() {
        List<Artist> artists = new ArrayList<>();

        artists.add(getArtist("a", 500, 2));
        artists.add(getArtist("b", 200, 1));
        artists.add(getArtist("c", 100, 3));
        artists.add(getArtist("d", 200, 4));
        artists.add(getArtist("e", 100, 5));

        return artists;
    }

    private Artist getArtist(String name, Integer price, Integer popularityRank) {
        Artist artist = mock(Artist.class);

        when(artist.getName()).thenReturn(name);
        when(artist.getPrice()).thenReturn(price);
        when(artist.getPopularityRank()).thenReturn(popularityRank);

        return artist;
    }
}