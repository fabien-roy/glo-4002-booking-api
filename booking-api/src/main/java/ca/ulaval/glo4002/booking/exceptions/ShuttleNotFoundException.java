package ca.ulaval.glo4002.booking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShuttleNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 2019_09_23_0947l;
}
