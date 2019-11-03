package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.domain.NumberGenerator;
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

import java.util.Collections;

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
    void build_shouldBuildNoDiscountPriceCalculationStrategy_whenPassOptionIsSinglePassAndPassCategoryIsSupernova() {
        PassCategories category = PassCategories.SUPERNOVA;
        PassOptions option = PassOptions.SINGLE_PASS;

        PassList passList = subject.build(Collections.singletonList(mock(Pass.class)), category, option);

        assertTrue(passList.getPriceCalculationStrategy() instanceof NoDiscountPriceCalculationStrategy);
    }

    @Test
    void build_shouldBuildSupergiantPriceCalculationStrategy_whenPassOptionIsSinglePassAndPassCategoryIsSupergiant() {
        PassCategories category = PassCategories.SUPERGIANT;
        PassOptions option = PassOptions.SINGLE_PASS;

        PassList passList = subject.build(Collections.singletonList(mock(Pass.class)), category, option);

        assertTrue(passList.getPriceCalculationStrategy() instanceof SupergiantPriceCalculationStrategy);
    }

    @Test
    void build_shouldBuildNebulaPriceCalculationStrategy_whenPassOptionIsSinglePassAndPassCategoryIsNebula() {
        PassCategories category = PassCategories.NEBULA;
        PassOptions option = PassOptions.SINGLE_PASS;

        PassList passList = subject.build(Collections.singletonList(mock(Pass.class)), category, option);

        assertTrue(passList.getPriceCalculationStrategy() instanceof NebulaPriceCalculationStrategy);
    }

    @ParameterizedTest
    @EnumSource(PassCategories.class)
    void build_shouldBuildNoDiscountPriceCalculationStrategy_whenPassOptionIsPackage(PassCategories category) {
        PassOptions option = PassOptions.PACKAGE;

        PassList passList = subject.build(Collections.singletonList(mock(Pass.class)), category, option);

        assertTrue(passList.getPriceCalculationStrategy() instanceof NoDiscountPriceCalculationStrategy);
    }
}