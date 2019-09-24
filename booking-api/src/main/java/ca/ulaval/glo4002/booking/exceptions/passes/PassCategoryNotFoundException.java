package ca.ulaval.glo4002.booking.exceptions.passes;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PassCategoryNotFoundException extends RuntimeException {

    public PassCategoryNotFoundException() {
        super(ExceptionConstants.PASS_CATEGORY_NOT_FOUND_MESSAGE);
    }
}
