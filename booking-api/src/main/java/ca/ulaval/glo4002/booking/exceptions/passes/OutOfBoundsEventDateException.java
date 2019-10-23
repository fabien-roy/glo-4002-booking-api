package ca.ulaval.glo4002.booking.exceptions.passes;

import ca.ulaval.glo4002.booking.exceptions.BookingException;

public class OutOfBoundsEventDateException extends BookingException {

    public OutOfBoundsEventDateException() {
        super("OUT_OF_BOUNDS_EVENT_DATE_EXCEPTION");

        description = "Out of bounds event date exception";
    }
}
