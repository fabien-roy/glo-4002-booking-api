package ca.ulaval.glo4002.booking.oxygen;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OxygenDate {

    private LocalDate value;

    public OxygenDate(String value) {
        // TODO : OXY : This parsing must be in a Factory, not in the constructor
        // TODO : OXY : Not sure if actually needed OxyDate should receive a LocalDate not a string since not in the front of the JSON order
        try {
            this.value = LocalDate.parse(value, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception exception) {
            throw new InvalidOxygenDateFormatException();
        }
    }

    // TODO : OXY : Refactor
    public OxygenDate(LocalDate value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public void addDays(int numberOfDays) {
        this.value = value.plusDays(numberOfDays);
    }

    public LocalDate getValue() {
        return value;
    }
}
