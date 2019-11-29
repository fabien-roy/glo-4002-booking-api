package ca.ulaval.glo4002.booking.numbers;

import ca.ulaval.glo4002.booking.errors.InvalidFormatException;

public class Number {

    private Long value;

    public Number(Long value) {
        this.value = value;
    }

    public Number(String value) {
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
        if (!(other instanceof Number)) return false;

        Number otherNumber = (Number) other;

        return this.value.equals(otherNumber.getValue());
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
