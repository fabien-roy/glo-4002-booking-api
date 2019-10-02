package ca.ulaval.glo4002.booking.exceptions.shuttles;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.AlreadyCreatedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ShuttleAlreadyCreatedException extends AlreadyCreatedException {
	
	public ShuttleAlreadyCreatedException() {
		super(ExceptionConstants.Shuttle.ALREADY_CREATED_MESSAGE);
	}

}
