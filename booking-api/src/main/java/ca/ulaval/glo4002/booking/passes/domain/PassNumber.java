package ca.ulaval.glo4002.booking.passes.domain;

public class PassNumber {

    private Long value;

    public PassNumber(Long value) {
        this.value = value;
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
