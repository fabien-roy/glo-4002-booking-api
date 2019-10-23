package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.domain.passes.PassList;
import ca.ulaval.glo4002.booking.enums.PassCategories;
import ca.ulaval.glo4002.booking.enums.PassOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PassFactoryTest {

    private PassFactory subject;

    @BeforeEach
    void setUpSubject() {
        subject = new PassFactory();
    }

    @Test
    void build_shouldSetSamePriceCalculationStrategyForOptionAndCategory() {
        /*
        PassCategories category = PassCategories.SUPERNOVA;
        PassOptions option = PassOptions.PACKAGE;

        PassList passList = subject.build(category, option);

        assertEquals(passList.getCategory().getPriceCalculationStrategy(), passList.getOption().getPriceCalculationStrategy());
        */
    }
}