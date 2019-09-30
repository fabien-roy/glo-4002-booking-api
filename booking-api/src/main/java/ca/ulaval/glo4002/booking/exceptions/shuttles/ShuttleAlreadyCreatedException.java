package ca.ulaval.glo4002.booking.exceptions.shuttles;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.FestivalException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShuttleAlreadyCreatedException extends FestivalException {
	
	public ShuttleAlreadyCreatedException() {
		super(ExceptionConstants.SHUTTLE_ALREADY_CREATED_MESSAGE);
	}

}
