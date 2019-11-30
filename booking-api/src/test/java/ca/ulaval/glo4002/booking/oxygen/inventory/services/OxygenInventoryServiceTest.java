package ca.ulaval.glo4002.booking.oxygen.inventory.services;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.orders.domain.OrderDate;
import ca.ulaval.glo4002.booking.program.artists.domain.Artist;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.numbers.Number;
import ca.ulaval.glo4002.booking.oxygen.domain.OxygenCategories;
import ca.ulaval.glo4002.booking.oxygen.domain.OxygenFactory;
import ca.ulaval.glo4002.booking.oxygen.domain.OxygenTankProducer;
import ca.ulaval.glo4002.booking.passes.domain.Pass;
import ca.ulaval.glo4002.booking.passes.domain.PassCategories;
import ca.ulaval.glo4002.booking.profits.domain.Money;
import org.junit.jupiter.params.provider.ValueSource;

import static ca.ulaval.glo4002.booking.oxygen.inventory.services.OxygenInventoryService.OXYGEN_CATEGORY_FOR_ARTIST;
import static ca.ulaval.glo4002.booking.oxygen.inventory.services.OxygenInventoryService.OXYGEN_TANKS_NEEDED_PER_ARTIST;

class OxygenInventoryServiceTest {

	private static final OrderDate AN_ORDER_DATE = new OrderDate(FestivalConfiguration.getDefaultStartEventDate().toLocalDateTime());

	private OxygenInventoryService service;
	private FestivalConfiguration festivalConfiguration;
	private OxygenFactory factory;
	private OxygenTankProducer producer;

	@BeforeEach
	void setUpService() {
		factory = new OxygenFactory(festivalConfiguration);
		producer = mock(OxygenTankProducer.class);

		service = new OxygenInventoryService(festivalConfiguration, factory, producer);
	}

	@BeforeEach
	void setUpConfiguration() {
		festivalConfiguration = mock(FestivalConfiguration.class);

		when(festivalConfiguration.getAllEventDates()).thenReturn(Arrays.asList(
				FestivalConfiguration.getDefaultStartEventDate(),
				FestivalConfiguration.getDefaultEndEventDate())
		);
	}

	@Test
	void orderForPasses_shouldOrderForFullFestival_whenEventDatesIsNull() {
		Pass aFullFestivalPass = new Pass(new Number(1L), mock(Money.class));
		int numberOfFestivalDays = festivalConfiguration.getAllEventDates().size();

		service.orderForPasses(PassCategories.SUPERNOVA, Collections.singletonList(aFullFestivalPass), AN_ORDER_DATE);

		verify(producer, times(numberOfFestivalDays)).produceOxygenForOrder(any(), any());
	}

	@Test
	void orderForPasses_shouldOrderForEventDate() {
		EventDate aEventDate = FestivalConfiguration.getDefaultStartEventDate();
		Pass aPass = new Pass(new Number(1L), mock(Money.class), aEventDate);

		service.orderForPasses(PassCategories.SUPERNOVA, Collections.singletonList(aPass), AN_ORDER_DATE);

		verify(producer).produceOxygenForOrder(any(), any());
	}

	@Test
	void orderForPasses_shouldOrderForEventDates_whenThereAreMultiplePasses() {
		EventDate aEventDate = FestivalConfiguration.getDefaultStartEventDate();
		EventDate anotherEventDate = FestivalConfiguration.getDefaultStartEventDate().plusDays(1);
		Pass aPass = new Pass(new Number(1L), mock(Money.class), aEventDate);
		Pass anotherPass = new Pass(new Number(1L), mock(Money.class), anotherEventDate);

		service.orderForPasses(PassCategories.SUPERNOVA, Arrays.asList(aPass, anotherPass), AN_ORDER_DATE);

		verify(producer, times(2)).produceOxygenForOrder(any(), any());
	}

	@ParameterizedTest
	@EnumSource(PassCategories.class)
	void orderForPasses_shouldOrderWithCorrectOxygenCategory(PassCategories passCategory) {
		OxygenCategories expectedOxygenCategory = factory.createCategory(passCategory).getCategory();
		EventDate aEventDate = FestivalConfiguration.getDefaultStartEventDate();
		Pass aPass = new Pass(new Number(1L), mock(Money.class), aEventDate);

		service.orderForPasses(passCategory, Collections.singletonList(aPass), AN_ORDER_DATE);

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
