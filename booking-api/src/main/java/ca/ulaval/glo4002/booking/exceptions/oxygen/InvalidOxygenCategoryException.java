package ca.ulaval.glo4002.booking.exceptions.oxygen;

import ca.ulaval.glo4002.booking.exceptions.BookingException;

public class InvalidOxygenCategoryException extends BookingException {

    public InvalidOxygenCategoryException() {
        super("INVALID_OXYGEN_CATEGORY");

        description = "Invalid oxygen category";
    }
}
