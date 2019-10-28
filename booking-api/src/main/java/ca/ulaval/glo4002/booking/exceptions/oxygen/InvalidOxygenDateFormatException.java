package ca.ulaval.glo4002.booking.exceptions.oxygen;

import ca.ulaval.glo4002.booking.exceptions.BookingException;

public class InvalidOxygenDateFormatException extends BookingException {

    public InvalidOxygenDateFormatException() {
        super("INVALID_OXYGEN_DATE_FORMAT");

        description = "Invalid oxygen date format";
    }
}
