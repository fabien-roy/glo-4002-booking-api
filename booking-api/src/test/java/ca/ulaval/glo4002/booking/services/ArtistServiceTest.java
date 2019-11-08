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

    private ArtistService service;
    private ArtistRepository artistRepository;
    private Artist secondPopularAndFirstCostArtist = mockArtist("a", 500, 2);
    private Artist firstPopularAndThirdCostArtist = mockArtist("b", 200, 1);
    private Artist thirdPopularAndEqualFourthCostArtist = mockArtist("c", 100, 3);
    private Artist fourthPopularAndSecondCostArtist = mockArtist("d", 300, 4);
    private Artist fifthPopularAndEqualFourthCostArtist = mockArtist("e", 100, 5);

    @BeforeEach
    void setUpService() {
        List<Artist> artists = mockArtists();
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

    // TODO : byPopularity and null should be a single test
    @Test
    void getAll_shouldReturnAllArtistNamesOrderedByPopularity_whenOrderByIsNull() {
        ArtistListDto artistListDto = service.getAll(null);

        assertEquals(firstPopularAndThirdCostArtist, artistListDto.getArtists().get(0));
        assertEquals(secondPopularAndFirstCostArtist, artistListDto.getArtists().get(1));
        assertEquals(thirdPopularAndEqualFourthCostArtist, artistListDto.getArtists().get(2));
        assertEquals(fourthPopularAndSecondCostArtist, artistListDto.getArtists().get(3));
        assertEquals(fifthPopularAndEqualFourthCostArtist, artistListDto.getArtists().get(4));
    }

    @Test
    void getAll_shouldReturnAllArtistNamesOrderedByPopularity_whenOrderByIsByPopularity() {
        ArtistListDto artistListDto = service.getAll(null);

        assertEquals(firstPopularAndThirdCostArtist, artistListDto.getArtists().get(0));
        assertEquals(secondPopularAndFirstCostArtist, artistListDto.getArtists().get(1));
        assertEquals(thirdPopularAndEqualFourthCostArtist, artistListDto.getArtists().get(2));
        assertEquals(fourthPopularAndSecondCostArtist, artistListDto.getArtists().get(3));
        assertEquals(fifthPopularAndEqualFourthCostArtist, artistListDto.getArtists().get(4));
    }

    @Test
    void getAll_shouldReturnAllArtistNamesOrderedByCostAndByPopularity_whenOrderByIsByCost() {
        ArtistListDto artistListDto = service.getAll(null);

        assertEquals(secondPopularAndFirstCostArtist, artistListDto.getArtists().get(0));
        assertEquals(fourthPopularAndSecondCostArtist, artistListDto.getArtists().get(1));
        assertEquals(firstPopularAndThirdCostArtist, artistListDto.getArtists().get(2));
        assertEquals(thirdPopularAndEqualFourthCostArtist, artistListDto.getArtists().get(3));
        assertEquals(fifthPopularAndEqualFourthCostArtist, artistListDto.getArtists().get(4));
    }

    private List<Artist> mockArtists() {
        List<Artist> artists = new ArrayList<>();

        artists.add(secondPopularAndFirstCostArtist);
        artists.add(firstPopularAndThirdCostArtist);
        artists.add(thirdPopularAndEqualFourthCostArtist);
        artists.add(fourthPopularAndSecondCostArtist);
        artists.add(fifthPopularAndEqualFourthCostArtist);

        return artists;
    }

    private Artist mockArtist(String name, Integer price, Integer popularityRank) {
        Artist artist = mock(Artist.class);

        when(artist.getName()).thenReturn(name);
        when(artist.getPrice()).thenReturn(price);
        when(artist.getPopularityRank()).thenReturn(popularityRank);

        return artist;
    }
}