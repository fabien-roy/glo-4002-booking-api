package ca.ulaval.glo4002.booking.passes.domain;

public class PassNumberGenerator {

    private PassNumber nextNumber; // TODO : Make this thread safe

    public PassNumberGenerator() {
        this.nextNumber = new PassNumber(100000L);
    }

    public PassNumber generate() {
        PassNumber number = nextNumber;

        nextNumber = new PassNumber(nextNumber.getValue() + 1);

        return number;
    }
}
