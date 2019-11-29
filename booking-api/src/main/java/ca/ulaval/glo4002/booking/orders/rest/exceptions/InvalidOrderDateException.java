package ca.ulaval.glo4002.booking.orders.rest.exceptions;

import ca.ulaval.glo4002.booking.errors.BookingException;
import org.springframework.http.HttpStatus;

public class InvalidOrderDateException extends BookingException {

    public static final String MESSAGE = "INVALID_ORDER_DATE";
    // The following start and end order dates are default. This is as required.
    public static final String DESCRIPTION = "Order date should be between January 1 2050 and July 16 2050";

    public InvalidOrderDateException() {
        super(MESSAGE);

        description = DESCRIPTION;
        status = HttpStatus.BAD_REQUEST;
    }
}
