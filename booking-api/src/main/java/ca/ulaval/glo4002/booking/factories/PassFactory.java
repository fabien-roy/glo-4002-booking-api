package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.domain.passes.PassCategory;
import ca.ulaval.glo4002.booking.domain.passes.PassList;
import ca.ulaval.glo4002.booking.domain.passes.PassOption;
import ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy.NebulaPriceCalculationStrategy;
import ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy.NoDiscountPriceCalculationStrategy;
import ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy.PriceCalculationStrategy;
import ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy.SupergiantPriceCalculationStrategy;
import ca.ulaval.glo4002.booking.enums.PassCategories;
import ca.ulaval.glo4002.booking.enums.PassOptions;

public class PassFactory {

    public PassList build(PassCategories category, PassOptions option) {
        PassCategory passCategory = buildCategory(category);
        PassOption passOption = buildOption(option);
        PriceCalculationStrategy priceCalculationStrategy = buildPriceCalculationStrategy(category, option);

        return new PassList(passCategory, passOption, priceCalculationStrategy);
    }

    private PassCategory buildCategory(PassCategories category) {
        switch(category) {
            case SUPERNOVA:
                return new PassCategory(PassCategories.SUPERNOVA.toString());
            case SUPERGIANT:
                return new PassCategory(PassCategories.SUPERGIANT.toString());
            default:
            case NEBULA:
                return new PassCategory(PassCategories.NEBULA.toString());
        }
    }

    private PassOption buildOption(PassOptions option) {
        switch (option) {
            case PACKAGE:
                return new PassOption(PassOptions.PACKAGE.toString());
            default:
            case SINGLE_PASS:
                return new PassOption(PassOptions.SINGLE_PASS.toString());
        }
    }

    private PriceCalculationStrategy buildPriceCalculationStrategy(PassCategories category, PassOptions option) {
        switch (option) {
            case PACKAGE:
                return new NoDiscountPriceCalculationStrategy();
            default:
            case SINGLE_PASS:
                switch(category) {
                    case SUPERNOVA:
                        return new NoDiscountPriceCalculationStrategy();
                    case SUPERGIANT:
                        return new SupergiantPriceCalculationStrategy();
                    default:
                    case NEBULA:
                        return new NebulaPriceCalculationStrategy();
                }
        }
    }
}
