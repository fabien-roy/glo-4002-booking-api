package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.domain.EventDate;
import ca.ulaval.glo4002.booking.domain.NumberGenerator;
import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.domain.passes.Pass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class PassFactoryTest {

    private PassFactory factory;

    @BeforeEach
    void setUpFactory() {
        NumberGenerator numberGenerator = new NumberGenerator();

        factory = new PassFactory(numberGenerator);
    }

    @Test
    void buildAll_shouldBuildASinglePass_whenThereNoEventDate() {
        List<Pass> passes = factory.buildAll(null, mock(Money.class));

        assertEquals(1, passes.size());
    }

    @Test
    void buildAll_shouldBuildASinglePass_whenThereIsOnlyOneEventDate() {
        List<String> aEventDate = Collections.singletonList(EventDate.START_DATE.toString());

        List<Pass> passes = factory.buildAll(aEventDate, mock(Money.class));

        assertEquals(1, passes.size());
    }

    @Test
    void buildAll_shouldBuildMultiplePasses_whenThereAreMultipleEventDates() {
        List<String> aEventDate = Arrays.asList(EventDate.START_DATE.toString(), EventDate.START_DATE.plusDays(1).toString());

        List<Pass> passes = factory.buildAll(aEventDate, mock(Money.class));

        assertEquals(2, passes.size());
    }
}