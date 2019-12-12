package ca.ulaval.glo4002.booking.passes.domain;

public class PassNumberGenerator {

    private Long nextNumber; // TODO : Make this thread safe

    public PassNumberGenerator() {
        this.nextNumber = 100000L;
    }

    public Long generate() {
        Long number = nextNumber;

        nextNumber = nextNumber + 1;

        return number;
    }
}
