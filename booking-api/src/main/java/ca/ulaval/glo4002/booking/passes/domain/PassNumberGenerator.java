package ca.ulaval.glo4002.booking.passes.domain;

import ca.ulaval.glo4002.booking.numbers.Number;

public class PassNumberGenerator {

    private Number nextNumber; // TODO : Make this thread safe

    public PassNumberGenerator() {
        this.nextNumber = new Number(100000L);
    }

    public Number generate() {
        Number number = nextNumber;
        nextNumber = new Number(nextNumber.getValue() + 1);

        return number;
    }
}
