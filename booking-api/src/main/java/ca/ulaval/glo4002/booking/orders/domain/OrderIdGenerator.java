package ca.ulaval.glo4002.booking.orders.domain;

import ca.ulaval.glo4002.booking.numbers.Number;

public class OrderIdGenerator {

    private Number nextNumber; // TODO : Make this thread safe

    public OrderIdGenerator() {
        this.nextNumber = new Number(100000L);
    }

    public Number generate() {
        Number number = nextNumber;
        nextNumber = new Number(nextNumber.getValue() + 1);

        return number;
    }
}
