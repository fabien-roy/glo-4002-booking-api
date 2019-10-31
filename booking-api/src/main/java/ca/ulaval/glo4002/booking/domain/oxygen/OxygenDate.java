package ca.ulaval.glo4002.booking.domain.oxygen;

import ca.ulaval.glo4002.booking.exceptions.oxygen.InvalidOxygenDateFormatException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class OxygenDate {

    // TODO : OXY Maybe just a LocalDate time not needed for a oxygenTanks
    private LocalDateTime value;

    public OxygenDate(String value) {
        // TODO : OXY : This parsing must be in a Factory, not in the constructor
        // TODO : OXY : Not sure if actually needed OxyDate should receive a LocalDate not a string since not in the front of the JSON order
        try {
            this.value = ZonedDateTime.parse(value, DateTimeFormatter.ISO_DATE_TIME).toLocalDateTime();
        } catch (Exception exception) {
            throw new InvalidOxygenDateFormatException();
        }
    }

    // TODO : OXY : Refactor
    public OxygenDate(LocalDate value) {
        this.value = value.atStartOfDay();
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public void addDays(int numberOfDays) {
        this.value = value.plusDays(numberOfDays);
    }

    public LocalDateTime getValue() {
        return value;
    }
}
