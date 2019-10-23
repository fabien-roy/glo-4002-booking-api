package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.domain.passes.PassCategory;
import ca.ulaval.glo4002.booking.domain.passes.PassList;
import ca.ulaval.glo4002.booking.domain.passes.options.PassOption;
import ca.ulaval.glo4002.booking.enums.PassCategories;
import ca.ulaval.glo4002.booking.enums.PassOptions;

public class PassFactory {

    public PassList build(PassCategories category, PassOptions option) {
        PassCategory passCategory = buildCategory(category);
        PassOption passOption = buildOption(option);

        passOption.setPriceCalculationStrategy(passCategory.getPriceCalculationStrategy());

        return new PassList(passCategory, passOption);
    }

    private PassCategory buildCategory(PassCategories category) {
        return null;
    }

    private PassOption buildOption(PassOptions option) {
        return null;
    }
}
