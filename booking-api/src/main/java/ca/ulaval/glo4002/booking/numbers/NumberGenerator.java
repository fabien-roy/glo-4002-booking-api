package ca.ulaval.glo4002.booking.numbers;

public class NumberGenerator {

    private Number nextNumber;

    public NumberGenerator() {
        this.nextNumber = new Number(1L);
    }

    public Number generate() {
        Number number = nextNumber;
        nextNumber = new Number(nextNumber.getValue() + 1);

        return number;
    }
}
