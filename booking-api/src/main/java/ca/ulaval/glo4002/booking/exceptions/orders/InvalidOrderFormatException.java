package ca.ulaval.glo4002.booking.exceptions.orders;

import ca.ulaval.glo4002.booking.exceptions.BookingException;

public class InvalidOrderFormatException extends BookingException {

    public InvalidOrderFormatException() {
        super("INVALID_ORDER_FORMAT");

        description = "Invalid order format";
    }
}
