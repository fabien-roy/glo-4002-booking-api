package ca.ulaval.glo4002.booking.exceptions.shuttles;

import ca.ulaval.glo4002.booking.exceptions.BookingException;

public class InvalidShuttleCategoryException extends BookingException {

    public InvalidShuttleCategoryException() {
        super("INVALID_SHUTTLE_CATEGORY");

        description = "Invalid shuttle category";
    }
}
