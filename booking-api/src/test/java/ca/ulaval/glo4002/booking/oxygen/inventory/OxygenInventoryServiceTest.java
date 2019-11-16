package ca.ulaval.glo4002.booking.oxygen.inventory;

import ca.ulaval.glo4002.booking.artists.BookingArtist;
import ca.ulaval.glo4002.booking.events.Event;
import ca.ulaval.glo4002.booking.events.EventDate;
import ca.ulaval.glo4002.booking.numbers.Number;
import ca.ulaval.glo4002.booking.oxygen.OxygenCategories;
import ca.ulaval.glo4002.booking.oxygen.OxygenCategory;
import ca.ulaval.glo4002.booking.oxygen.OxygenFactory;
import ca.ulaval.glo4002.booking.oxygen.OxygenTankProducer;
import ca.ulaval.glo4002.booking.passes.Pass;
import ca.ulaval.glo4002.booking.passes.PassCategories;
import ca.ulaval.glo4002.booking.profits.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static ca.ulaval.glo4002.booking.oxygen.inventory.OxygenInventoryService.OXYGEN_CATEGORY_FOR_ARTIST;
import static ca.ulaval.glo4002.booking.oxygen.inventory.OxygenInventoryService.OXYGEN_TANKS_NEEDED_PER_ARTIST;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class OxygenInventoryServiceTest {

    private OxygenInventoryService service;
    private OxygenFactory factory;
    private OxygenTankProducer producer;

    private static final LocalDateTime AN_ORDER_DATE = LocalDateTime.of(2050, 5, 20, 0, 0);

    @BeforeEach
    void setUpService() {
        factory = new OxygenFactory();
        producer = mock(OxygenTankProducer.class);

        service = new OxygenInventoryService(factory, producer);
    }

    @Test
    void orderForPasses_shouldOrderForFullFestival_whenEventDatesIsNull() {
        Pass aFullFestivalPass = new Pass(new Number(1L), mock(Money.class));
        Integer numberOfFestivalDays = EventDate.getFullFestivalEventDates().size();

        service.orderForPasses(PassCategories.SUPERNOVA, Collections.singletonList(aFullFestivalPass), AN_ORDER_DATE);

        verify(producer, times(numberOfFestivalDays)).produceOxygenForOrder(any(), any());
    }

    @Test
    void orderForPasses_shouldOrderForEventDate() {
        EventDate aEventDate = new EventDate(EventDate.START_DATE);
        Pass aPass = new Pass(new Number(1L), mock(Money.class), aEventDate);

        service.orderForPasses(PassCategories.SUPERNOVA, Collections.singletonList(aPass), AN_ORDER_DATE);

        verify(producer).produceOxygenForOrder(any(), any());
    }

    @Test
    void orderForPasses_shouldOrderForEventDates_whenThereAreMultiplePasses() {
        EventDate aEventDate = new EventDate(EventDate.START_DATE);
        EventDate anotherEventDate = new EventDate(EventDate.START_DATE.plusDays(1));
        Pass aPass = new Pass(new Number(1L), mock(Money.class), aEventDate);
        Pass anotherPass = new Pass(new Number(1L), mock(Money.class), anotherEventDate);

        service.orderForPasses(PassCategories.SUPERNOVA, Arrays.asList(aPass, anotherPass), AN_ORDER_DATE);

        verify(producer, times(2)).produceOxygenForOrder(any(), any());
    }

    @ParameterizedTest
    @EnumSource(PassCategories.class)
    void orderForPasses_shouldOrderWithCorrectOxygenCategory(PassCategories passCategory) {
        OxygenCategories expectedOxygenCategory = factory.buildCategory(passCategory).getCategory();
        EventDate aEventDate = new EventDate(EventDate.START_DATE);
        Pass aPass = new Pass(new Number(1L), mock(Money.class), aEventDate);

        service.orderForPasses(passCategory, Collections.singletonList(aPass), AN_ORDER_DATE);

        verify(producer).produceOxygenForOrder(eq(expectedOxygenCategory), any());
    }

    @Test
    void orderForArtist_shouldProduceWithCorrectCategory() {
        BookingArtist artist = mock(BookingArtist.class);
        EventDate orderDate = mock(EventDate.class);
        OxygenCategories expectedOxygenCategory = OXYGEN_CATEGORY_FOR_ARTIST;

        service.orderForArtist(artist, orderDate);

        verify(producer).produceOxygenByQuantity(argThat(category -> category.getCategory().equals(expectedOxygenCategory)), any(), any());
    }

    @Test
    void orderForArtist_shouldProduceWithCorrectOrderDate() {
        BookingArtist artist = mock(BookingArtist.class);
        EventDate orderDate = mock(EventDate.class);
        LocalDate expectedOrderDateValue = EventDate.START_DATE;
        when(orderDate.getValue()).thenReturn(expectedOrderDateValue);

        service.orderForArtist(artist, orderDate);

        verify(producer).produceOxygenByQuantity(any(), eq(expectedOrderDateValue), any());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void orderForArtist_shouldProduceWithCorrectAmountOfTanks(int numberOfPeople) {
        BookingArtist artist = mock(BookingArtist.class);
        when(artist.getNumberOfPeople()).thenReturn(numberOfPeople);
        EventDate orderDate = mock(EventDate.class);
        Integer expectedAmountOfOxygenTanksNeeded = numberOfPeople * OXYGEN_TANKS_NEEDED_PER_ARTIST;

        service.orderForArtist(artist, orderDate);

        verify(producer).produceOxygenByQuantity(any(), any(), eq(expectedAmountOfOxygenTanksNeeded));
    }
}