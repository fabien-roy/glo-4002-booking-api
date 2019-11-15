package ca.ulaval.glo4002.booking.oxygen.inventory;

import ca.ulaval.glo4002.booking.events.EventDate;
import ca.ulaval.glo4002.booking.numbers.Number;
import ca.ulaval.glo4002.booking.oxygen.OxygenCategories;
import ca.ulaval.glo4002.booking.oxygen.OxygenFactory;
import ca.ulaval.glo4002.booking.oxygen.OxygenTankProducer;
import ca.ulaval.glo4002.booking.passes.Pass;
import ca.ulaval.glo4002.booking.passes.PassCategories;
import ca.ulaval.glo4002.booking.profits.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

        verify(producer, times(numberOfFestivalDays)).produceForDay(any(), any());
    }

    @Test
    void orderForPasses_shouldOrderForEventDate() {
        EventDate aEventDate = new EventDate(EventDate.START_DATE);
        Pass aPass = new Pass(new Number(1L), mock(Money.class), aEventDate);

        service.orderForPasses(PassCategories.SUPERNOVA, Collections.singletonList(aPass), AN_ORDER_DATE);

        verify(producer).produceForDay(any(), any());
    }

    @Test
    void orderForPasses_shouldOrderForEventDates_whenThereAreMultiplePasses() {
        EventDate aEventDate = new EventDate(EventDate.START_DATE);
        EventDate anotherEventDate = new EventDate(EventDate.START_DATE.plusDays(1));
        Pass aPass = new Pass(new Number(1L), mock(Money.class), aEventDate);
        Pass anotherPass = new Pass(new Number(1L), mock(Money.class), anotherEventDate);

        service.orderForPasses(PassCategories.SUPERNOVA, Arrays.asList(aPass, anotherPass), AN_ORDER_DATE);

        verify(producer, times(2)).produceForDay(any(), any());
    }

    @ParameterizedTest
    @EnumSource(PassCategories.class)
    void orderForPasses_shouldOrderWithCorrectOxygenCategory(PassCategories passCategory) {
        OxygenCategories expectedOxygenCategory = factory.buildCategory(passCategory).getCategory();
        EventDate aEventDate = new EventDate(EventDate.START_DATE);
        Pass aPass = new Pass(new Number(1L), mock(Money.class), aEventDate);

        service.orderForPasses(passCategory, Collections.singletonList(aPass), AN_ORDER_DATE);

        verify(producer).produceForDay(eq(expectedOxygenCategory), any());
    }
}
