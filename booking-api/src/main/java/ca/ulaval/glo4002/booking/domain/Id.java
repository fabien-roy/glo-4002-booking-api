package ca.ulaval.glo4002.booking.domain;

import ca.ulaval.glo4002.booking.exceptions.InvalidIdFormatException;

import java.util.Objects;

public class Id {

    private Long value;

    public Id(Long value) {
        this.value = value;
    }

    public Id(String value) {
        try {
            this.value = Long.parseLong(value);
        } catch (Exception exception) {
            throw new InvalidIdFormatException();
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
    public boolean equals(Object that) {
        if (!(that instanceof Id)) return false;

        Id thatPeople = (Id) that;

        return this.value.equals(thatPeople.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
