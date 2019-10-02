package ca.ulaval.glo4002.booking.exceptions.passes;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.ControllerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PassNotFoundException extends ControllerException {
    
    public PassNotFoundException(String passNumber) {
        super(ExceptionConstants.Pass.NOT_FOUND_ERROR);

        error = ExceptionConstants.Pass.NOT_FOUND_ERROR;
        description = ExceptionConstants.Pass.NOT_FOUND_DESCRIPTION.replace("{passNumber}", passNumber);
        httpStatus = HttpStatus.NOT_FOUND;
    }
}
