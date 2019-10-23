package ca.ulaval.glo4002.booking.exceptions.passes;

import ca.ulaval.glo4002.booking.exceptions.BookingException;

public class PackagePassWithEventDateException extends BookingException {

    public PackagePassWithEventDateException() {
        super("PACKAGE_PASS_WITH_EVENT_DATE");

        description = "Package pass with event date";
    }
}

