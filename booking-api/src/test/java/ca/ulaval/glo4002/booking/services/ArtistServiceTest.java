package ca.ulaval.glo4002.booking.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.domain.artist.BookingArtist;
import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.dto.events.ArtistListDto;
import ca.ulaval.glo4002.booking.enums.ArtistOrderings;
import ca.ulaval.glo4002.booking.repositories.ArtistRepository;
import ca.ulaval.glo4002.booking.repositories.InMemoryArtistRepository;

class ArtistServiceTest {

    private ArtistService service;
    private ArtistRepository artistRepository;
    private BookingArtist firstPopularAndThirdCostArtist = buildArtist("firstPopularAndThirdCostArtist", 200, 1);
    private BookingArtist secondPopularAndFirstCostArtist = buildArtist("secondPopularAndFirstCostArtist ", 500, 2);
    private BookingArtist thirdPopularAndEqualFourthCostArtist = buildArtist("thirdPopularAndEqualFourthCostArtist ", 100, 3);
    private BookingArtist fourthPopularAndSecondCostArtist = buildArtist("fourthPopularAndSecondCostArtist ", 300, 4);
    private BookingArtist fifthPopularAndEqualFourthCostArtist = buildArtist("fifthPopularAndEqualFourthCostArtist ", 100, 5);

    @BeforeEach
    void setUpService() {
    	artistRepository = new InMemoryArtistRepository();
    	
        service = new ArtistService(artistRepository);
        
        mockArtists();

    }

    @Test
    void getAll_shouldReturnAllArtistNames_whenOrderByIsNull() {
        ArtistListDto artistListDto = service.getAllUnordered();

        assertFalse(artistListDto.getArtists().isEmpty());
    }

    @Test
    void getAll_shouldReturnAllArtistNamesUnordered_whenOrderByIsNull() {
        ArtistListDto artistListDto = service.getAllUnordered();
        
        assertEquals(secondPopularAndFirstCostArtist.getName(), artistListDto.getArtists().get(0));
        assertEquals(firstPopularAndThirdCostArtist.getName(), artistListDto.getArtists().get(1));
        assertEquals(thirdPopularAndEqualFourthCostArtist.getName(), artistListDto.getArtists().get(2));
        assertEquals(fourthPopularAndSecondCostArtist.getName(), artistListDto.getArtists().get(3));
        assertEquals(fifthPopularAndEqualFourthCostArtist.getName(), artistListDto.getArtists().get(4));
    }

    @Test
    void getAll_shouldReturnAllArtistNamesOrderedByPopularity_whenOrderByIsByMostPopular() {
        ArtistListDto artistListDto = service.getAllOrdered(ArtistOrderings.MOST_POPULAR.toString());

        assertEquals(firstPopularAndThirdCostArtist.getName(), artistListDto.getArtists().get(0));
        assertEquals(secondPopularAndFirstCostArtist.getName(), artistListDto.getArtists().get(1));
        assertEquals(thirdPopularAndEqualFourthCostArtist.getName(), artistListDto.getArtists().get(2));
        assertEquals(fourthPopularAndSecondCostArtist.getName(), artistListDto.getArtists().get(3));
        assertEquals(fifthPopularAndEqualFourthCostArtist.getName(), artistListDto.getArtists().get(4));
    }

    @Test
    void getAll_shouldReturnAllArtistNamesOrderedByCostAndByPopularity_whenOrderByIsLowCosts() {
        ArtistListDto artistListDto = service.getAllOrdered(ArtistOrderings.LOW_COSTS.toString());

        assertEquals(secondPopularAndFirstCostArtist.getName(), artistListDto.getArtists().get(0));
        assertEquals(fourthPopularAndSecondCostArtist.getName(), artistListDto.getArtists().get(1));
        assertEquals(firstPopularAndThirdCostArtist.getName(), artistListDto.getArtists().get(2));
        assertEquals(thirdPopularAndEqualFourthCostArtist.getName(), artistListDto.getArtists().get(3));
        assertEquals(fifthPopularAndEqualFourthCostArtist.getName(), artistListDto.getArtists().get(4));
    }

    private void mockArtists() {
        List<BookingArtist> artists = new ArrayList<>();

        artists.add(secondPopularAndFirstCostArtist);
        artists.add(firstPopularAndThirdCostArtist);
        artists.add(thirdPopularAndEqualFourthCostArtist);
        artists.add(fourthPopularAndSecondCostArtist);
        artists.add(fifthPopularAndEqualFourthCostArtist);

        artistRepository.saveAll(artists);
    }

    private BookingArtist buildArtist(String name, Integer price, Integer popularityRank) {
        Money cost = new Money(new BigDecimal(price));
        Integer aNumberOfPeople = 1;
        String aMusicStyle = "aMusicStyle";

        return new BookingArtist(name, cost, aNumberOfPeople, aMusicStyle, popularityRank, new ArrayList<>());
    }
} 