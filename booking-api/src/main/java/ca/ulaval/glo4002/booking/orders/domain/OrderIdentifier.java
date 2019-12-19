package ca.ulaval.glo4002.booking.orders.domain;

import ca.ulaval.glo4002.booking.interfaces.rest.exceptions.InvalidFormatException;

public class OrderIdentifier {

    private long value;

    public OrderIdentifier(long value) {
        this.value = value;
    }

    public OrderIdentifier(String value) {
        try {
            this.value = Long.parseLong(value);
        } catch (Exception exception) {
            throw new InvalidFormatException();
        }
    }

    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Long.toString(value);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof OrderIdentifier)) return false;

        OrderIdentifier otherNumber = (OrderIdentifier) other;

        return this.value == otherNumber.getValue();
    }

    @Override
    public int hashCode() {
        return Long.hashCode(value);
    }
}
