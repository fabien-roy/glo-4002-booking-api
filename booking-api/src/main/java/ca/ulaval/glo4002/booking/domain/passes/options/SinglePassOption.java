package ca.ulaval.glo4002.booking.domain.passes.options;

import ca.ulaval.glo4002.booking.domain.passes.money.Money;

public class SinglePassOption extends PassOption {

    public SinglePassOption(Money price) {
        super(price);
    }

    public Money calculatePrice(int passQuantity) {
        return priceCalculationStrategy.calculatePassPrice(passQuantity, price);
    }
}
