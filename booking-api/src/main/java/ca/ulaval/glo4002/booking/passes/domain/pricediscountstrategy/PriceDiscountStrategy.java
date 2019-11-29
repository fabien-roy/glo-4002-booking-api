package ca.ulaval.glo4002.booking.passes.domain.pricediscountstrategy;

import ca.ulaval.glo4002.booking.profits.domain.Money;

public interface PriceDiscountStrategy {

    Money calculateDiscount(int passQuantity, Money price);
}
