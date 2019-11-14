package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.events.Event;
import ca.ulaval.glo4002.booking.domain.events.EventDate;
import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.domain.passes.Pass;
import ca.ulaval.glo4002.booking.enums.PassCategories;
import ca.ulaval.glo4002.booking.factories.OxygenFactory;
import ca.ulaval.glo4002.booking.producers.OxygenTankProducer;
import ca.ulaval.glo4002.booking.enums.OxygenCategories;
import ca.ulaval.glo4002.booking.mappers.OxygenInventoryMapper;
import ca.ulaval.glo4002.booking.repositories.OxygenInventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OxygenInventoryServiceTest {

    private OxygenInventoryService service;
    private OxygenFactory factory;
    private OxygenTankProducer producer;

    @BeforeEach
    void setUpService() {
        factory = new OxygenFactory();
        producer = mock(OxygenTankProducer.class);

        service = new OxygenInventoryService(factory, producer);
    }

    @Test
    void orderForPasses_shouldOrderForFullFestival_whenEventDatesIsNull() {
        Pass aFullFestivalPass = new Pass(new Number(1L), mock(Money.class));
        List<EventDate> fullFestivalEventDates = EventDate.getFullFestivalEventDates();

        service.orderForPasses(PassCategories.SUPERNOVA, Collections.singletonList(aFullFestivalPass));

        fullFestivalEventDates.forEach(eventDate -> verify(producer).produce(any(), eq(eventDate.getValue())));
    }

    @Test
    void orderForPasses_shouldOrderForEventDate() {
        EventDate aEventDate = new EventDate(EventDate.START_DATE);
        Pass aPass = new Pass(new Number(1L), mock(Money.class), aEventDate);

        service.orderForPasses(PassCategories.SUPERNOVA, Collections.singletonList(aPass));

        verify(producer).produce(any(), eq(aEventDate.getValue()));
    }

    @Test
    void orderForPasses_shouldOrderForEventDates_whenThereAreMultiplePasses() {
        EventDate aEventDate = new EventDate(EventDate.START_DATE);
        EventDate anotherEventDate = new EventDate(EventDate.START_DATE.plusDays(1));
        Pass aPass = new Pass(new Number(1L), mock(Money.class), aEventDate);
        Pass anotherPass = new Pass(new Number(1L), mock(Money.class), anotherEventDate);

        service.orderForPasses(PassCategories.SUPERNOVA, Arrays.asList(aPass, anotherPass));

        verify(producer).produce(any(), eq(aEventDate.getValue()));
        verify(producer).produce(any(), eq(anotherEventDate.getValue()));
    }

    @ParameterizedTest
    @EnumSource(PassCategories.class)
    void orderForPasses_shouldOrderWithCorrectOxygenCategory(PassCategories passCategory) {
        OxygenCategories expectedOxygenCategory = factory.buildCategory(passCategory);
        EventDate aEventDate = new EventDate(EventDate.START_DATE);
        Pass aPass = new Pass(new Number(1L), mock(Money.class), aEventDate);

        service.orderForPasses(passCategory, Collections.singletonList(aPass));

        verify(producer).produce(eq(expectedOxygenCategory), any());
    }
}
