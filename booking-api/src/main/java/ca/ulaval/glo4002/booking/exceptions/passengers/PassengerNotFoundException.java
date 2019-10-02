package ca.ulaval.glo4002.booking.exceptions.passengers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.ControllerException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PassengerNotFoundException extends ControllerException {

	public PassengerNotFoundException() {
		super(ExceptionConstants.Passenger.NOT_FOUND_ERROR);
		
		error = ExceptionConstants.Passenger.NOT_FOUND_ERROR;
		description = ExceptionConstants.Passenger.NOT_FOUND_DESCRIPTION;
		httpStatus = HttpStatus.NOT_FOUND;
	}
}
