package ca.ulaval.glo4002.booking.program.artists.services;

import ca.ulaval.glo4002.booking.profits.domain.Money;
import ca.ulaval.glo4002.booking.program.artists.domain.Artist;
import ca.ulaval.glo4002.booking.program.artists.domain.ArtistOrderings;
import ca.ulaval.glo4002.booking.program.artists.infrastructure.ArtistRepository;
import ca.ulaval.glo4002.booking.program.artists.rest.ArtistListResponse;
import ca.ulaval.glo4002.booking.program.rest.exceptions.InvalidProgramException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ArtistServiceTest {

    private ArtistService service;
    private ArtistRepository artistRepository;

    private Artist firstPopularAndThirdCostArtist = buildArtist("firstPopularAndThirdCostArtist", 200, 1);
    private Artist secondPopularAndFirstCostArtist = buildArtist("secondPopularAndFirstCostArtist", 500, 2);
    private Artist thirdPopularAndEqualFourthCostArtist = buildArtist("thirdPopularAndEqualFourthCostArtist", 100, 3);
    private Artist fourthPopularAndSecondCostArtist = buildArtist("fourthPopularAndSecondCostArtist", 300, 4);
    private Artist fifthPopularAndEqualFourthCostArtist = buildArtist("fifthPopularAndEqualFourthCostArtist", 100, 5);

    @BeforeEach
    void setUpService() {
        service = new ArtistService(artistRepository);
    }

    @BeforeEach
    void setUpRepository() {
        artistRepository = mock(ArtistRepository.class);

        when(artistRepository.findAll()).thenReturn(Arrays.asList(
                secondPopularAndFirstCostArtist,
                firstPopularAndThirdCostArtist,
                thirdPopularAndEqualFourthCostArtist,
                fourthPopularAndSecondCostArtist,
                fifthPopularAndEqualFourthCostArtist
        ));
    }

    @Test
    void getByName_shouldReturnArtist() {
	    String artistName = firstPopularAndThirdCostArtist.getName();
	    when(artistRepository.findByName(artistName)).thenReturn(firstPopularAndThirdCostArtist);

	    Artist artist = service.getByName(artistName);

	    assertEquals(firstPopularAndThirdCostArtist.getName(), artist.getName());
    }

    @Test
    void getAll_shouldReturnAllArtistNames_whenOrderByIsNull() {
        ArtistListResponse artistListResponse = service.getAllUnordered();

        assertFalse(artistListResponse.getArtists().isEmpty());
    }

    @Test
    void getAll_shouldReturnAllArtistNamesUnordered_whenOrderByIsNull() {
        ArtistListResponse artistListResponse = service.getAllUnordered();
        
        assertEquals(secondPopularAndFirstCostArtist.getName(), artistListResponse.getArtists().get(0));
        assertEquals(firstPopularAndThirdCostArtist.getName(), artistListResponse.getArtists().get(1));
        assertEquals(thirdPopularAndEqualFourthCostArtist.getName(), artistListResponse.getArtists().get(2));
        assertEquals(fourthPopularAndSecondCostArtist.getName(), artistListResponse.getArtists().get(3));
        assertEquals(fifthPopularAndEqualFourthCostArtist.getName(), artistListResponse.getArtists().get(4));
    }

    @Test
    void getAll_shouldReturnAllArtistNamesOrderedByPopularity_whenOrderByIsByMostPopular() {
        ArtistListResponse artistListResponse = service.getAllOrdered(ArtistOrderings.MOST_POPULAR.toString());

        assertEquals(firstPopularAndThirdCostArtist.getName(), artistListResponse.getArtists().get(0));
        assertEquals(secondPopularAndFirstCostArtist.getName(), artistListResponse.getArtists().get(1));
        assertEquals(thirdPopularAndEqualFourthCostArtist.getName(), artistListResponse.getArtists().get(2));
        assertEquals(fourthPopularAndSecondCostArtist.getName(), artistListResponse.getArtists().get(3));
        assertEquals(fifthPopularAndEqualFourthCostArtist.getName(), artistListResponse.getArtists().get(4));
    }

    @Test
    void getAll_shouldReturnAllArtistNamesOrderedByCostAndByPopularity_whenOrderByIsLowCosts() {
        ArtistListResponse artistListResponse = service.getAllOrdered(ArtistOrderings.LOW_COSTS.toString());

        assertEquals(thirdPopularAndEqualFourthCostArtist.getName(), artistListResponse.getArtists().get(0));
        assertEquals(fifthPopularAndEqualFourthCostArtist.getName(), artistListResponse.getArtists().get(1));
        assertEquals(firstPopularAndThirdCostArtist.getName(), artistListResponse.getArtists().get(2));
        assertEquals(fourthPopularAndSecondCostArtist.getName(), artistListResponse.getArtists().get(3));
        assertEquals(secondPopularAndFirstCostArtist.getName(), artistListResponse.getArtists().get(4));
    }


    private Artist buildArtist(String name, Integer price, Integer popularityRank) {
        Money cost = new Money(new BigDecimal(price));
        Integer aNumberOfPeople = 1;
        String aMusicStyle = "aMusicStyle";

        return new Artist(1, name, cost, aNumberOfPeople, aMusicStyle, popularityRank, new ArrayList<>());
    }
} 