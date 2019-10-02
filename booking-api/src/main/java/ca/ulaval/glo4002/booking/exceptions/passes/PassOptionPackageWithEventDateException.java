package ca.ulaval.glo4002.booking.exceptions.passes;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.ControllerException;
import org.springframework.http.HttpStatus;

public class PassOptionPackageWithEventDateException extends ControllerException {

    public PassOptionPackageWithEventDateException() {
        super(ExceptionConstants.Pass.OPTION_PACKAGE_WITH_EVENT_DATE_ERROR);

        error = ExceptionConstants.Pass.OPTION_PACKAGE_WITH_EVENT_DATE_ERROR;
        description = ExceptionConstants.Pass.OPTION_PACKAGE_WITH_EVENT_DATE_DESCRIPTION;
        httpStatus = HttpStatus.NOT_FOUND;
    }
}
