package ca.ulaval.glo4002.booking.domain;

import ca.ulaval.glo4002.booking.exceptions.InvalidIdFormatException;

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
}
