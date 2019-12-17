package ca.ulaval.glo4002.booking.passes.rest.mappers;

import ca.ulaval.glo4002.booking.passes.domain.PassCategories;
import ca.ulaval.glo4002.booking.passes.domain.PassOptions;
import ca.ulaval.glo4002.booking.passes.domain.PassRefactored;
import ca.ulaval.glo4002.booking.passes.rest.PassRefactoredRequest;

import java.util.Collections;

public class PassRefactoredMapper {

    // TODO : Handle price calculation
    // TODO : Use EventDateMapper (is currently EventDateFactory) -> when null, use method to get all event dates
    // TODO : Set arrival and departure dates

    public PassRefactored fromRequest(PassRefactoredRequest request) {
        PassCategories category = parsePassCategory(request.getPassCategory());
        PassOptions option = parsePassOption(request.getPassOption());

        return new PassRefactored(category, option, null, Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
    }

    private PassCategories parsePassCategory(String category) {
        return PassCategories.get(category);
    }

    private PassOptions parsePassOption(String option) {
        return PassOptions.get(option);
    }
}
