package ca.ulaval.glo4002.booking.exceptions.orders;

import ca.ulaval.glo4002.booking.exceptions.BookingException;
import org.springframework.http.HttpStatus;

public class OutOfBoundsOrderDateException extends BookingException {

    public OutOfBoundsOrderDateException() {
        super("OUT_OF_BOUNDS_ORDER_DATE_EXCEPTION");

        description = "Out of bounds order date exception";
        status = HttpStatus.BAD_REQUEST;
    }
}
