package ca.ulaval.glo4002.booking.exceptions;

public class FestivalException extends RuntimeException {
    public FestivalException(){
        super();
    }

    public FestivalException(String message) {
        super(message);
    }

    public FestivalException(String message, Throwable cause) {
        super(message, cause);
    }

    public FestivalException(Throwable cause) {
        super(cause);
    }

    public FestivalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
