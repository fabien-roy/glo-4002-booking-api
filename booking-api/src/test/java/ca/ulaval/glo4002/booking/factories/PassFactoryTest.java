package ca.ulaval.glo4002.booking.factories;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PassFactoryTest {

    private PassFactory subject;

    @BeforeEach
    void setUpSubject() {
        subject = new PassFactory();
    }

    @Test
    void build_shouldBuildCategory_whenCategoryIsSupernova() {
        PassCategories category = PassCategories.SUPERNOVA;
        PassOptions option = PassOptions.PACKAGE;

        PassList passList = subject.build(category, option);

        assertEquals(passList.getCategory().getName(), PassCategories.SUPERNOVA.toString());
    }

    @Test
    void build_shouldBuildCategory_whenCategoryIsSupergiant() {
        PassCategories category = PassCategories.SUPERGIANT;
        PassOptions option = PassOptions.PACKAGE;

        PassList passList = subject.build(category, option);

        assertEquals(passList.getCategory().getName(), PassCategories.SUPERGIANT.toString());
    }

    @Test
    void build_shouldBuildCategory_whenCategoryIsNebula() {
        PassCategories category = PassCategories.NEBULA;
        PassOptions option = PassOptions.PACKAGE;

        PassList passList = subject.build(category, option);

        assertEquals(passList.getCategory().getName(), PassCategories.NEBULA.toString());
    }

    @Test
    void build_shouldBuildOption_whenOptionIsSinglePass() {
        PassCategories category = PassCategories.SUPERNOVA;
        PassOptions option = PassOptions.SINGLE_PASS;

        PassList passList = subject.build(category, option);

        assertEquals(passList.getOption().getName(), PassOptions.SINGLE_PASS.toString());
    }

    @Test
    void build_shouldBuildOption_whenOptionIsPackage() {
        PassCategories category = PassCategories.SUPERNOVA;
        PassOptions option = PassOptions.SINGLE_PASS;

        PassList passList = subject.build(category, option);

        assertEquals(passList.getOption().getName(), PassOptions.SINGLE_PASS.toString());
    }

    @Test
    void build_shouldBuildNoDiscountPriceCalculationStrategy_whenPassOptionIsSinglePassAndPassCategoryIsSupernova() {
        PassCategories category = PassCategories.SUPERNOVA;
        PassOptions option = PassOptions.SINGLE_PASS;

        PassList passList = subject.build(category, option);

        assertTrue(passList.getPriceCalculationStrategy() instanceof NoDiscountPriceCalculationStrategy);
    }

    @Test
    void build_shouldBuildSupergiantPriceCalculationStrategy_whenPassOptionIsSinglePassAndPassCategoryIsSupergiant() {
        PassCategories category = PassCategories.SUPERGIANT;
        PassOptions option = PassOptions.SINGLE_PASS;

        PassList passList = subject.build(category, option);

        assertTrue(passList.getPriceCalculationStrategy() instanceof SupergiantPriceCalculationStrategy);
    }

    @Test
    void build_shouldBuildNebulaPriceCalculationStrategy_whenPassOptionIsSinglePassAndPassCategoryIsNebula() {
        PassCategories category = PassCategories.NEBULA;
        PassOptions option = PassOptions.SINGLE_PASS;

        PassList passList = subject.build(category, option);

        assertTrue(passList.getPriceCalculationStrategy() instanceof NebulaPriceCalculationStrategy);
    }

    @ParameterizedTest
    @EnumSource(PassCategories.class)
    void build_shouldBuildNoDiscountPriceCalculationStrategy_whenPassOptionIsPackage(PassCategories category) {
        PassOptions option = PassOptions.PACKAGE;

        PassList passList = subject.build(category, option);

        assertTrue(passList.getPriceCalculationStrategy() instanceof NoDiscountPriceCalculationStrategy);
    }
}