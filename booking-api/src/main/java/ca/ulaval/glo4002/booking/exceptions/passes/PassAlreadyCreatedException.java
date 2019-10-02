package ca.ulaval.glo4002.booking.exceptions.passes;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.AlreadyCreatedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PassAlreadyCreatedException extends AlreadyCreatedException {

    public PassAlreadyCreatedException() {
        super(ExceptionConstants.Pass.ALREADY_CREATED_MESSAGE);
    }
}
