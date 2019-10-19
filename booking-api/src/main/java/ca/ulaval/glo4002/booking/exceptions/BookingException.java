package ca.ulaval.glo4002.booking.exceptions;

import org.springframework.http.HttpStatus;

public abstract class BookingException extends RuntimeException {

    protected String description;

    public BookingException(){
        super();
    }

    public BookingException(String message) {
        super(message);
    }

    public BookingException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookingException(Throwable cause) {
        super(cause);
    }

    public BookingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BookingException(String message, String description) {
        super(message);

        this.description = description;
    }
}
