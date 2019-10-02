package ca.ulaval.glo4002.booking.exceptions.oxygen;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.FestivalException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OxygenTankAlreadyCreatedException extends FestivalException {

    public OxygenTankAlreadyCreatedException() {
        super(ExceptionConstants.Oxygen.TANK_ALREADY_CREATED_ERROR);

        error = ExceptionConstants.Oxygen.TANK_ALREADY_CREATED_ERROR;
        description = ExceptionConstants.Oxygen.TANK_ALREADY_CREATED_DESCRIPTION;
        httpStatus = HttpStatus.BAD_REQUEST;
    }
}
