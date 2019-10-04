package ca.ulaval.glo4002.booking.exceptions.passengers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.FestivalException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PassengerAlreadyCreatedException extends FestivalException {
	
	public PassengerAlreadyCreatedException() {
		super(ExceptionConstants.Passenger.ALREADY_CREATED_ERROR);
		
		error = ExceptionConstants.Passenger.ALREADY_CREATED_ERROR;
		description = ExceptionConstants.Passenger.ALREADY_CREATED_DESCRIPTION;
		httpStatus = HttpStatus.BAD_REQUEST;
	}
}
