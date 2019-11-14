package ca.ulaval.glo4002.booking.shuttles;

import ca.ulaval.glo4002.booking.exceptions.BookingException;

// TODO : Should use InvalidFormatException
public class InvalidShuttleCategoryException extends BookingException {

    public InvalidShuttleCategoryException() {
        super("INVALID_SHUTTLE_CATEGORY");

        description = "Invalid shuttle category";
    }
}
