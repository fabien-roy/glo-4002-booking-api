package ca.ulaval.glo4002.booking.passes.domain;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.program.events.domain.EventDateFactory;
import ca.ulaval.glo4002.booking.numbers.NumberGenerator;
import ca.ulaval.glo4002.booking.profits.domain.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class PassFactoryTest {

    private PassFactory factory;

    @BeforeEach
    void setUpFactory() {
        NumberGenerator numberGenerator = new NumberGenerator();
        EventDateFactory eventDateFactory = mock(EventDateFactory.class);

        factory = new PassFactory(numberGenerator, eventDateFactory);
    }

    @Test
    void createAll_shouldCreateASinglePass_whenThereNoEventDate() {
        List<Pass> passes = factory.createAll(null, mock(Money.class));

        assertEquals(1, passes.size());
    }

    @Test
    void createAll_shouldCreateASinglePass_whenThereIsOnlyOneEventDate() {
        List<String> aEventDate = Collections.singletonList(FestivalConfiguration.getDefaultStartEventDate().toString());

        List<Pass> passes = factory.createAll(aEventDate, mock(Money.class));

        assertEquals(1, passes.size());
    }

    @Test
    void createAll_shouldCreateMultiplePasses_whenThereAreMultipleEventDates() {
        List<String> aEventDate = Arrays.asList(
                FestivalConfiguration.getDefaultStartEventDate().toString(),
                FestivalConfiguration.getDefaultStartEventDate().plusDays(1).toString()
        );

        List<Pass> passes = factory.createAll(aEventDate, mock(Money.class));

        assertEquals(2, passes.size());
    }
}