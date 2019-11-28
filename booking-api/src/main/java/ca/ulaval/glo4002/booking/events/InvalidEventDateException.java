package ca.ulaval.glo4002.booking.events;

import ca.ulaval.glo4002.booking.exceptions.BookingException;
import org.springframework.http.HttpStatus;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class InvalidEventDateException extends BookingException {

    private static final DateTimeFormatter MESSAGE_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMMM d y").withLocale(Locale.ENGLISH);
    public static final String MESSAGE = "INVALID_EVENT_DATE";
    // TODO : Use EventDate from BookingConfiguration in InvalidEventDateException (or ExceptionMapper?)
    public static final String DESCRIPTION = "Event date should be between x and x";

    public InvalidEventDateException() {
        super(MESSAGE);

        description = DESCRIPTION;
        status = HttpStatus.BAD_REQUEST;
    }
}
