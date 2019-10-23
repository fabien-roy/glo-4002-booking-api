package ca.ulaval.glo4002.booking.exceptions.passes;

import ca.ulaval.glo4002.booking.exceptions.BookingException;

public class SinglePassWithoutEventDateException extends BookingException {

    public SinglePassWithoutEventDateException() {
        super("SINGLE_PASS_WITHOUT_EVENT_DATE");

        description = "Single pass without event date";
    }
}

