package ca.ulaval.glo4002.booking.exceptions.passes;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.HumanReadableException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PassInvalidFormatException extends HumanReadableException {

    public PassInvalidFormatException(){
        super(ExceptionConstants.Pass.INVALID_FORMAT_DESCRIPTION);

        error = ExceptionConstants.Pass.INVALID_FORMAT_ERROR;
        description = ExceptionConstants.Pass.INVALID_FORMAT_DESCRIPTION;
        httpStatus = HttpStatus.BAD_REQUEST;
    }
}
