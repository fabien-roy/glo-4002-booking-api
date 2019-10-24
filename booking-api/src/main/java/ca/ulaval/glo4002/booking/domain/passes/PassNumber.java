package ca.ulaval.glo4002.booking.domain.passes;

import ca.ulaval.glo4002.booking.domain.Number;

// TODO : This should simply be a Number in Pass
public class PassNumber {

    private Number id;

    public PassNumber() {

    }

    public PassNumber(Number id) {
        this.id = id;
    }

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }
}

