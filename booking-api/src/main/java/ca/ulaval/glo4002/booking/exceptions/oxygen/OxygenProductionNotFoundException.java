package ca.ulaval.glo4002.booking.exceptions.oxygen;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OxygenProductionNotFoundException extends RuntimeException {

    public OxygenProductionNotFoundException() {
        super(ExceptionConstants.OXYGEN_PRODUCTION_NOT_FOUND_MESSAGE);
    }
}
