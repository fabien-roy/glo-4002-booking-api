package ca.ulaval.glo4002.booking.domain.passes;

import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.enums.PassCategories;
import ca.ulaval.glo4002.booking.enums.PassOptions;

import java.util.Map;

public class PassCategory {

    private PassCategories category;
    private Map<PassOptions, Money> pricePerOption;

    public PassCategory(PassCategories category, Map<PassOptions, Money> pricePerOption) {
        this.category = category;
        this.pricePerOption = pricePerOption;
    }

    public PassCategories getCategory() {
        return category;
    }

    public Money getPricePerOption(PassOptions option) {
        return pricePerOption.get(option);
    }
}
