package ca.ulaval.glo4002.booking.passes;

import ca.ulaval.glo4002.booking.events.EventDate;
import ca.ulaval.glo4002.booking.events.EventDateFactory;
import ca.ulaval.glo4002.booking.numbers.NumberGenerator;
import ca.ulaval.glo4002.booking.profits.Money;
import ca.ulaval.glo4002.booking.exceptions.InvalidFormatException;
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
    void buildAll_shouldBuildASinglePass_whenThereNoEventDate() {
        List<Pass> passes = factory.buildAll(null, mock(Money.class));

        assertEquals(1, passes.size());
    }

    @Test
    void buildAll_shouldBuildASinglePass_whenThereIsOnlyOneEventDate() {
        List<String> aEventDate = Collections.singletonList(EventDate.getStartEventDate().toString());

        List<Pass> passes = factory.buildAll(aEventDate, mock(Money.class));

        assertEquals(1, passes.size());
    }

    @Test
    void buildAll_shouldBuildMultiplePasses_whenThereAreMultipleEventDates() {
        List<String> aEventDate = Arrays.asList(EventDate.getStartEventDate().toString(), EventDate.getStartEventDate().plusDays(1).toString());

        List<Pass> passes = factory.buildAll(aEventDate, mock(Money.class));

        assertEquals(2, passes.size());
    }
}