package ca.ulaval.glo4002.booking.domain.orders;

import ca.ulaval.glo4002.booking.exceptions.InvalidOrderDateFormatException;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class OrderDate {

    private LocalDateTime value;

    public OrderDate(String value) {
        try {
            this.value = ZonedDateTime.parse(value, DateTimeFormatter.ISO_DATE_TIME).toLocalDateTime();
        } catch (Exception exception) {
            throw new InvalidOrderDateFormatException();
        }
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
