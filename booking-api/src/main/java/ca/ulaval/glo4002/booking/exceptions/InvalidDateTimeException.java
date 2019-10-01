package ca.ulaval.glo4002.booking.exceptions;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidDateTimeException extends FestivalException {

    public InvalidDateTimeException() {
        super(ExceptionConstants.INVALID_DATE_TIME_MESSAGE);
    }
}
