package ca.ulaval.glo4002.booking.exceptions.shuttles;

import ca.ulaval.glo4002.booking.exceptions.BookingException;

public class ShuttleAlreadyCreatedException extends BookingException {

    public ShuttleAlreadyCreatedException(String shuttleNumber) {
        super("SHUTTLE_ALREADY_CREATED");

        description = "Shuttle with number " + shuttleNumber + " already created";
    }
}
