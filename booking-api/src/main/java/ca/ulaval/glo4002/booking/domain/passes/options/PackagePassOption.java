package ca.ulaval.glo4002.booking.domain.passes.options;

import ca.ulaval.glo4002.booking.domain.passes.money.Money;

public class PackagePassOption extends PassOption {

    public PackagePassOption(Money price) {
        super(price);
    }

    public Money calculatePrice(int passQuantity) {
        return price;
    }
}
