package ca.ulaval.glo4002.booking.domain.passes;

import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.enums.PassOptions;

import java.util.Map;

public class PassCategory {

    private String name;
    private Map<PassOptions, Money> pricePerOption;

    public PassCategory(String name, Map<PassOptions, Money> pricePerOption) {
        this.name = name;
        this.pricePerOption = pricePerOption;
    }

    public String getName() {
        return name;
    }

    public Money getPricePerOption(PassOptions option) {
        return pricePerOption.get(option);
    }
}
