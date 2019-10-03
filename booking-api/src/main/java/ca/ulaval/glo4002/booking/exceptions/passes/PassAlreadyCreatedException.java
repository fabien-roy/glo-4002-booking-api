package ca.ulaval.glo4002.booking.exceptions.passes;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.HumanReadableException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PassAlreadyCreatedException extends HumanReadableException {

    public PassAlreadyCreatedException(String passNumber) {
        super(ExceptionConstants.Pass.ALREADY_CREATED_ERROR);

        error = ExceptionConstants.Pass.ALREADY_CREATED_ERROR;
        description = ExceptionConstants.Pass.ALREADY_CREATED_DESCRIPTION.replace("{passNumber}", passNumber);
        httpStatus = HttpStatus.BAD_REQUEST;
    }
}
