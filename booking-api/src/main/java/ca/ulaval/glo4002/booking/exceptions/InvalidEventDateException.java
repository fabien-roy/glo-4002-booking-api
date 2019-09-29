package ca.ulaval.glo4002.booking.exceptions;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidEventDateException extends FestivalException {

    public InvalidEventDateException() {
        super(ExceptionConstants.INVALID_EVENT_DATE_MESSAGE);
    }
}
