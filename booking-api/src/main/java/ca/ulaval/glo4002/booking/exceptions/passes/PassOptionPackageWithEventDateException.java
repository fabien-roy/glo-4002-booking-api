package ca.ulaval.glo4002.booking.exceptions.passes;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.ControllerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PassOptionPackageWithEventDateException extends ControllerException {

    public PassOptionPackageWithEventDateException() {
        super(ExceptionConstants.Pass.OPTION_PACKAGE_WITH_EVENT_DATE_ERROR);

        error = ExceptionConstants.Pass.OPTION_PACKAGE_WITH_EVENT_DATE_ERROR;
        description = ExceptionConstants.Pass.OPTION_PACKAGE_WITH_EVENT_DATE_DESCRIPTION;
        httpStatus = HttpStatus.BAD_REQUEST;
    }
}
