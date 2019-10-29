package ca.ulaval.glo4002.booking.exceptions.shuttles;

import ca.ulaval.glo4002.booking.exceptions.BookingException;

public class ShuttleNotFoundException extends BookingException {

    public ShuttleNotFoundException(String shuttleNumber) {
        super("SHUTTLE_NOT_FOUND");

        description = "Shuttle with number " + shuttleNumber + " doesn't exist";
    }
}
