package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domain.Number;
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
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OxygenInventoryServiceTest {

    private OxygenInventoryService service;
    private OxygenFactory factory;
    private OxygenTankProducer producer;

    @BeforeEach
    void setUpService() {
        factory = mock(OxygenFactory.class);
        doReturn(OxygenCategories.E).when(factory).buildCategory(any());
        producer = mock(OxygenTankProducer.class);

        service = new OxygenInventoryService(factory, producer);
    }

    @Test
    void orderForPasses_shouldOrderForFullFestival_whenEventDatesIsNull() {
        Pass aFullFestivalPass = new Pass(new Number(1L), mock(Money.class));

        service.orderForPasses(PassCategories.SUPERNOVA, Collections.singletonList(aFullFestivalPass));

        // verify(producer).
    }

    @Test
    void orderForPasses_shouldOrderForEventDate() {
        // TODO
    }

    @Test
    void orderForPasses_shouldOrderForEventDates_whenThereAreMultiplePasses() {
        // TODO
    }

    @ParameterizedTest
    @EnumSource(PassCategories.class)
    void orderForPasses_shouldOrderWithCorrectOxygenCategory(PassCategories passCategory) {
        // TODO
    }
}
