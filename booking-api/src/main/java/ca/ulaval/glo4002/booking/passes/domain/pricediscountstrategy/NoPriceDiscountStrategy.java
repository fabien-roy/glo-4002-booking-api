package ca.ulaval.glo4002.booking.passes.domain.pricediscountstrategy;

import ca.ulaval.glo4002.booking.profits.domain.Money;

public class NoPriceDiscountStrategy implements PriceDiscountStrategy {

    @Override
    public Money calculateDiscount(int passQuantity, Money price) {
        return price;
    }
}
