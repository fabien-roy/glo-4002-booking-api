package ca.ulaval.glo4002.booking.exceptions.passes;

import ca.ulaval.glo4002.booking.exceptions.BookingException;

public class InvalidPassCategoryException extends BookingException {

    public InvalidPassCategoryException() {
        super("INVALID_PASS_CATEGORY");

        description = "Invalid pass category";
    }
}

