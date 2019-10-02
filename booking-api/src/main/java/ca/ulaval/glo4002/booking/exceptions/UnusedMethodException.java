package ca.ulaval.glo4002.booking.exceptions;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UnusedMethodException extends FestivalException{

    public UnusedMethodException(){
        super(ExceptionConstants.UNUSED_METHOD_ERROR);

        error = ExceptionConstants.UNUSED_METHOD_ERROR;
        description = ExceptionConstants.UNUSED_METHOD_DESCRIPTION;
    }
}
