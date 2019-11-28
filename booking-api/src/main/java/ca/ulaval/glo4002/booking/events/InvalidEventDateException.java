package ca.ulaval.glo4002.booking.events;

import ca.ulaval.glo4002.booking.exceptions.BookingException;
import org.springframework.http.HttpStatus;

public class InvalidEventDateException extends BookingException {

    public static final String MESSAGE = "INVALID_EVENT_DATE";
    // The following start and end event dates are default. This is as required.
    public static final String DESCRIPTION = "Event date should be between July 17 2050 and July 24 2050";

    InvalidEventDateException() {
        super(MESSAGE);

        description = DESCRIPTION;
        status = HttpStatus.BAD_REQUEST;
    }
}
