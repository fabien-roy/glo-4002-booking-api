package ca.ulaval.glo4002.booking.domain.passes;

import ca.ulaval.glo4002.booking.exceptions.InvalidEventDateException;

import java.time.LocalDate;

public class EventDate {

    // TODO : ACP Those should be EventDate
    public static final LocalDate START_DATE = LocalDate.of(2050, 7, 17);
    public static final LocalDate END_DATE = LocalDate.of(2050, 7, 24);
    private LocalDate value;

    public EventDate(LocalDate value) {
        validateEventDate(value);

        this.value = value;
    }

    public LocalDate getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    private void validateEventDate(LocalDate value) {
        if (value.isBefore(START_DATE) || value.isAfter(END_DATE)) {
            throw new InvalidEventDateException();
        }
    }
}
