package ca.ulaval.glo4002.booking.exceptions.passes;

import ca.ulaval.glo4002.booking.exceptions.BookingException;

public class DuplicatePassEventDateException extends BookingException {

    public DuplicatePassEventDateException() {
        super("DUPLICATE_PASS_EVENT_DATE");

        description = "Duplicate pass event date";
    }
}

