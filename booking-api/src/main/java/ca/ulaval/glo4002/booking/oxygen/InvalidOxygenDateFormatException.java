package ca.ulaval.glo4002.booking.oxygen;

import ca.ulaval.glo4002.booking.exceptions.BookingException;

// TODO : Useless, no?
public class InvalidOxygenDateFormatException extends BookingException {

    public InvalidOxygenDateFormatException() {
        super("INVALID_OXYGEN_DATE_FORMAT");

        description = "Invalid oxygen date format";
    }
}
