package ca.ulaval.glo4002.booking.exceptions.passes;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PassDtoInvalidException extends RuntimeException {

    public PassDtoInvalidException(){
        super(ExceptionConstants.PASS_DTO_INVALID_MESSAGE);
    }
}
