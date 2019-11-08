package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domain.BookingArtist;
import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.dto.events.ArtistListDto;
import ca.ulaval.glo4002.booking.enums.ArtistOrderings;
import ca.ulaval.glo4002.booking.exceptions.InvalidFormatException;
import ca.ulaval.glo4002.booking.factories.ArtistFactory;
import ca.ulaval.glo4002.organisation.repositories.ArtistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ArtistServiceTest {

    private ArtistService service;
    private BookingArtist firstPopularAndThirdCostArtist = buildArtist("firstPopularAndThirdCostArtist", 200, 1);
    private BookingArtist secondPopularAndFirstCostArtist = buildArtist("secondPopularAndFirstCostArtist ", 500, 2);
    private BookingArtist thirdPopularAndEqualFourthCostArtist = buildArtist("thirdPopularAndEqualFourthCostArtist ", 100, 3);
    private BookingArtist fourthPopularAndSecondCostArtist = buildArtist("fourthPopularAndSecondCostArtist ", 300, 4);
    private BookingArtist fifthPopularAndEqualFourthCostArtist = buildArtist("fifthPopularAndEqualFourthCostArtist ", 100, 5);

    @BeforeEach
    void setUpService() {
        List<BookingArtist> artists = mockArtists();
        ArtistRepository artistRepository = mock(ArtistRepository.class);
        ArtistFactory artistFactory = mock(ArtistFactory.class);
        when(artistFactory.buildAll(any())).thenReturn(artists);

        service = new ArtistService(artistRepository, artistFactory);
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

        assertEquals(firstPopularAndThirdCostArtist.getName(), artistListDto.getArtists().get(0));
        assertEquals(secondPopularAndFirstCostArtist.getName(), artistListDto.getArtists().get(1));
        assertEquals(thirdPopularAndEqualFourthCostArtist.getName(), artistListDto.getArtists().get(2));
        assertEquals(fourthPopularAndSecondCostArtist.getName(), artistListDto.getArtists().get(3));
        assertEquals(fifthPopularAndEqualFourthCostArtist.getName(), artistListDto.getArtists().get(4));
    }

    @Test
    void getAll_shouldReturnAllArtistNamesOrderedByPopularity_whenOrderByIsByMostPopular() {
        ArtistListDto artistListDto = service.getAll(ArtistOrderings.MOST_POPULAR.toString());

        assertEquals(firstPopularAndThirdCostArtist.getName(), artistListDto.getArtists().get(0));
        assertEquals(secondPopularAndFirstCostArtist.getName(), artistListDto.getArtists().get(1));
        assertEquals(thirdPopularAndEqualFourthCostArtist.getName(), artistListDto.getArtists().get(2));
        assertEquals(fourthPopularAndSecondCostArtist.getName(), artistListDto.getArtists().get(3));
        assertEquals(fifthPopularAndEqualFourthCostArtist.getName(), artistListDto.getArtists().get(4));
    }

    @Test
    void getAll_shouldReturnAllArtistNamesOrderedByCostAndByPopularity_whenOrderByIsLowCosts() {
        ArtistListDto artistListDto = service.getAll(ArtistOrderings.LOW_COSTS.toString());

        assertEquals(secondPopularAndFirstCostArtist.getName(), artistListDto.getArtists().get(0));
        assertEquals(fourthPopularAndSecondCostArtist.getName(), artistListDto.getArtists().get(1));
        assertEquals(firstPopularAndThirdCostArtist.getName(), artistListDto.getArtists().get(2));
        assertEquals(thirdPopularAndEqualFourthCostArtist.getName(), artistListDto.getArtists().get(3));
        assertEquals(fifthPopularAndEqualFourthCostArtist.getName(), artistListDto.getArtists().get(4));
    }

    private List<BookingArtist> mockArtists() {
        List<BookingArtist> artists = new ArrayList<>();

        artists.add(secondPopularAndFirstCostArtist);
        artists.add(firstPopularAndThirdCostArtist);
        artists.add(thirdPopularAndEqualFourthCostArtist);
        artists.add(fourthPopularAndSecondCostArtist);
        artists.add(fifthPopularAndEqualFourthCostArtist);

        return artists;
    }

    private BookingArtist buildArtist(String name, Integer price, Integer popularityRank) {
        Money cost = new Money(new BigDecimal(price));
        Integer aNumberOfPeople = 1;
        String aMusicStyle = "aMusicStyle";

        return new BookingArtist(name, cost, aNumberOfPeople, aMusicStyle, popularityRank);
    }
}