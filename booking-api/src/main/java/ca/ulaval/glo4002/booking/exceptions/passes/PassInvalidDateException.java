package ca.ulaval.glo4002.booking.exceptions.passes;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.FestivalException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PassInvalidDateException extends FestivalException {

    public PassInvalidDateException(String startDate, String endDate) {
        super(ExceptionConstants.Pass.INVALID_DATE_ERROR);

        error = ExceptionConstants.Pass.NOT_FOUND_ERROR;
        description = ExceptionConstants.Pass.INVALID_DATE_DESCRIPTION
                .replace("{startDate}", startDate)
                .replace("{endDate}", endDate);
    }
}
