package ca.ulaval.glo4002.booking.exceptions.passes;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.FestivalException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PassOptionNotFoundException extends FestivalException {

    public PassOptionNotFoundException() {
        super(ExceptionConstants.Pass.OPTION_NOT_FOUND_MESSAGE);
    }
}
