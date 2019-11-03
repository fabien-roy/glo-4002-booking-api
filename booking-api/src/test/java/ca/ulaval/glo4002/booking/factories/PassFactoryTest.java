package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.domain.NumberGenerator;
import ca.ulaval.glo4002.booking.domain.passes.EventDate;
import ca.ulaval.glo4002.booking.domain.passes.Pass;
import ca.ulaval.glo4002.booking.domain.passes.PassList;
import ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy.NebulaPriceCalculationStrategy;
import ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy.SupergiantPriceCalculationStrategy;
import ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy.NoDiscountPriceCalculationStrategy;
import ca.ulaval.glo4002.booking.enums.PassCategories;
import ca.ulaval.glo4002.booking.enums.PassOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class PassFactoryTest {

    private PassFactory subject;

    @BeforeEach
    void setUpSubject() {
        NumberGenerator numberGenerator = new NumberGenerator();

        subject = new PassFactory(numberGenerator);
    }

    @Test
    void buildAll_shouldBuildASinglePass_whenThereNoEventDate() {
        List<Pass> passes = subject.buildAll(null);

        assertEquals(1, passes.size());
    }

    @Test
    void buildAll_shouldBuildASinglePass_whenThereIsOnlyOneEventDate() {
        List<String> aEventDate = Collections.singletonList(EventDate.START_DATE.toString());

        List<Pass> passes = subject.buildAll(aEventDate);

        assertEquals(1, passes.size());
    }

    @Test
    void buildAll_shouldBuildMultiplePasses_whenThereAreMultipleEventDates() {
        List<String> aEventDate = Arrays.asList(EventDate.START_DATE.toString(), EventDate.START_DATE.plusDays(1).toString());

        List<Pass> passes = subject.buildAll(aEventDate);

        assertEquals(2, passes.size());
    }
}