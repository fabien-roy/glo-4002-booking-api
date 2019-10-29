package ca.ulaval.glo4002.booking.exceptions.shuttles;

import ca.ulaval.glo4002.booking.exceptions.BookingException;

public class ShuttleFullException extends BookingException {

    public ShuttleFullException() {
        super("SHUTTLE_IS_FULL");

        description = "Shuttle is full";
    }
}
