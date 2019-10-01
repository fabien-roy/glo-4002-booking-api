package ca.ulaval.glo4002.booking.exceptions.shuttles;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.FestivalException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ShuttleAlreadyCreatedException extends FestivalException {
	
	public ShuttleAlreadyCreatedException() {
		super(ExceptionConstants.Shuttle.ALREADY_CREATED_MESSAGE);
	}

}
