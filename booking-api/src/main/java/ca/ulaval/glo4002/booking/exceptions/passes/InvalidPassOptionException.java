package ca.ulaval.glo4002.booking.exceptions.passes;

import ca.ulaval.glo4002.booking.exceptions.BookingException;

public class InvalidPassOptionException extends BookingException {

    public InvalidPassOptionException() {
        super("INVALID_PASS_OPTION");

        description = "Invalid pass option";
    }
}

