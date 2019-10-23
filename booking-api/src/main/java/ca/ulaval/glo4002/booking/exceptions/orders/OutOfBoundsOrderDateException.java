package ca.ulaval.glo4002.booking.exceptions.orders;

import ca.ulaval.glo4002.booking.exceptions.BookingException;

public class OutOfBoundsOrderDateException extends BookingException {

    public OutOfBoundsOrderDateException() {
        super("OUT_OF_BOUNDS_ORDER_DATE_EXCEPTION");

        description = "Out of bounds order date exception";
    }
}
