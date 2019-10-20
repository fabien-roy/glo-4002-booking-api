package ca.ulaval.glo4002.booking.exceptions;

public class InvalidOrderDateFormatException extends BookingException {

    public InvalidOrderDateFormatException() {
        super("INVALID_ORDER_DATE_FORMAT");

        description = "Invalid order date format";
    }
}
