package ca.ulaval.glo4002.booking.exceptions.passes;

import ca.ulaval.glo4002.booking.constants.DateConstants;
import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.ControllerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PassInvalidDateException extends ControllerException {

    // TODO : ACP : Check date format
    public PassInvalidDateException() {
        super(ExceptionConstants.Pass.INVALID_DATE_ERROR);

        error = ExceptionConstants.Pass.INVALID_DATE_ERROR;
        description = ExceptionConstants.Pass.INVALID_DATE_DESCRIPTION
                .replace("{startDate}", DateConstants.START_DATE.toString())
                .replace("{endDate}", DateConstants.END_DATE.toString());
        httpStatus = HttpStatus.BAD_REQUEST;
    }
}
