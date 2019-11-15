package ca.ulaval.glo4002.booking.passes;

import ca.ulaval.glo4002.booking.profits.Money;

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
