package ca.ulaval.glo4002.booking.domain;

import ca.ulaval.glo4002.booking.exceptions.InvalidFormatException;

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

        Number otherId = (Number) other;

        return this.value.equals(otherId.getValue());
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
