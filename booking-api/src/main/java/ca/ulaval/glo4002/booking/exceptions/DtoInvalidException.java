package ca.ulaval.glo4002.booking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public abstract class DtoInvalidException extends FestivalException {

    public DtoInvalidException(String message){ super(message); }
}
