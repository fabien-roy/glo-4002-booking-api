package ca.ulaval.glo4002.booking.passes.domain;

import ca.ulaval.glo4002.booking.interfaces.rest.exceptions.InvalidFormatException;

public class PassNumber {

    private Long value;

    public PassNumber(Long value) {
        this.value = value;
    }

    // TODO : Parse String to Number should not be in constructor
    public PassNumber(String value) {
        try {
            this.value = Long.parseLong(value);
        } catch (Exception exception) {
            throw new InvalidFormatException();
        }
    }

    public Long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof PassNumber)) return false;

        PassNumber otherNumber = (PassNumber) other;

        return this.value.equals(otherNumber.getValue());
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
