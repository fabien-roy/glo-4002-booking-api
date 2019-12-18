package ca.ulaval.glo4002.booking.oxygen.inventory.services;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.orders.domain.OrderDate;
import ca.ulaval.glo4002.booking.oxygen.domain.OxygenCategories;
import ca.ulaval.glo4002.booking.oxygen.domain.OxygenFactory;
import ca.ulaval.glo4002.booking.oxygen.domain.OxygenTankProducer;
import ca.ulaval.glo4002.booking.passes.domain.PassCategories;
import ca.ulaval.glo4002.booking.passes.domain.PassRefactored;
import ca.ulaval.glo4002.booking.program.artists.domain.Artist;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static ca.ulaval.glo4002.booking.oxygen.inventory.services.OxygenInventoryService.OXYGEN_CATEGORY_FOR_ARTIST;
import static ca.ulaval.glo4002.booking.oxygen.inventory.services.OxygenInventoryService.OXYGEN_TANKS_NEEDED_PER_ARTIST;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class OxygenInventoryServiceTest {

	private static final OrderDate AN_ORDER_DATE = new OrderDate(FestivalConfiguration.getDefaultStartEventDate().toLocalDateTime());

	private OxygenInventoryService service;
	private OxygenFactory factory;
	private OxygenTankProducer producer;

	@BeforeEach
	void setUpService() {
		factory = mock(OxygenFactory.class);
		producer = mock(OxygenTankProducer.class);

		service = new OxygenInventoryService(factory, producer);
	}

	@Test
	void orderForPasses_shouldOrderForEventDate() {
		OrderDate expectedOrderDate = AN_ORDER_DATE;
		List<PassRefactored> passes = Collections.singletonList(mock(PassRefactored.class));

		service.orderForPasses(passes, expectedOrderDate);

		verify(producer).produceOxygenForOrder(any(), eq(expectedOrderDate.toLocalDate()));
	}

	@Test
	void orderForPasses_shouldOrderForEventDates_whenThereAreMultiplePasses() {
		int passQuantity = 2;
		OrderDate expectedOrderDate = AN_ORDER_DATE;
		List<PassRefactored> passes = Collections.nCopies(passQuantity, mock(PassRefactored.class));

		service.orderForPasses(passes, expectedOrderDate);

		verify(producer, times(passQuantity)).produceOxygenForOrder(any(), eq(expectedOrderDate.toLocalDate()));
	}

	@Test
	void orderForPasses_shouldOrderWithCorrectOxygenCategory() {
	    PassCategories passCategory = PassCategories.SUPERNOVA;
	    OxygenCategories expectedOxygenCategory = OxygenCategories.A;
		List<PassRefactored> passes = Collections.singletonList(mock(PassRefactored.class));
		when(factory.createCategory(passCategory)).thenReturn(expectedOxygenCategory);

		service.orderForPasses(passes, AN_ORDER_DATE);

		verify(producer).produceOxygenForOrder(eq(expectedOxygenCategory), any());
	}

    @Test
    void orderForArtist_shouldProduceWithCorrectCategory() {
        Artist artist = mock(Artist.class);
        EventDate orderDate = mock(EventDate.class);
        OxygenCategories expectedOxygenCategory = OXYGEN_CATEGORY_FOR_ARTIST;

        service.orderForArtist(artist, orderDate);

        verify(producer).produceOxygenByQuantity(argThat(category -> category.getCategory().equals(expectedOxygenCategory)), any(), any());
    }

    @Test
    void orderForArtist_shouldProduceWithCorrectOrderDate() {
        Artist artist = mock(Artist.class);
        EventDate orderDate = mock(EventDate.class);
        LocalDate expectedOrderDateValue = FestivalConfiguration.getDefaultStartEventDate().getValue();
        when(orderDate.getValue()).thenReturn(expectedOrderDateValue);

        service.orderForArtist(artist, orderDate);

        verify(producer).produceOxygenByQuantity(any(), eq(expectedOrderDateValue), any());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void orderForArtist_shouldProduceWithCorrectAmountOfTanks(int numberOfPeople) {
        Artist artist = mock(Artist.class);
        when(artist.getNumberOfPeople()).thenReturn(numberOfPeople);
        EventDate orderDate = mock(EventDate.class);
        Integer expectedAmountOfOxygenTanksNeeded = numberOfPeople * OXYGEN_TANKS_NEEDED_PER_ARTIST;

        service.orderForArtist(artist, orderDate);

        verify(producer).produceOxygenByQuantity(any(), any(), eq(expectedAmountOfOxygenTanksNeeded));
    }
}
