package ca.ulaval.glo4002.booking.exceptions.passes;

import ca.ulaval.glo4002.booking.exceptions.BookingException;

public class InvalidEventDateFormatException extends BookingException {

    public InvalidEventDateFormatException() {
        super("INVALID_EVENT_DATE_FORMAT");

        description = "Invalid event date format";
    }
}
