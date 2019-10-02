package ca.ulaval.glo4002.booking.exceptions.oxygen;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.FestivalException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OxygenTankNotFoundException extends FestivalException {

    public OxygenTankNotFoundException() {
        super(ExceptionConstants.Oxygen.TANK_NOT_FOUND_ERROR);

        error = ExceptionConstants.Oxygen.TANK_NOT_FOUND_ERROR;
        description = ExceptionConstants.Oxygen.TANK_NOT_FOUND_DESCRIPTION;
    }
}
