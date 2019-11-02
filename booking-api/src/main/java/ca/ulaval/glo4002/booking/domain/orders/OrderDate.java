package ca.ulaval.glo4002.booking.domain.orders;

import ca.ulaval.glo4002.booking.exceptions.InvalidFormatException;
import ca.ulaval.glo4002.booking.exceptions.InvalidOrderDateException;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class OrderDate {

    public static final LocalDateTime START_DATE_TIME = LocalDateTime.of(2050, 1, 1, 0, 0);
    public static final LocalDateTime END_DATE_TIME = LocalDateTime.of(2050, 7, 17, 0, 0);
    private LocalDateTime value;

    public OrderDate(String value) {
        LocalDateTime newValue;

        try {
            newValue = ZonedDateTime.parse(value, DateTimeFormatter.ISO_DATE_TIME).toLocalDateTime();
        } catch (Exception exception) {
            throw new InvalidFormatException();
        }

        validateOrderDate(newValue);

        this.value = newValue;
    }

    public LocalDateTime getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    private void validateOrderDate(LocalDateTime value) {
        if (value.isBefore(START_DATE_TIME) || value.isAfter(END_DATE_TIME)) {
            throw new InvalidOrderDateException();
        }
    }
}
