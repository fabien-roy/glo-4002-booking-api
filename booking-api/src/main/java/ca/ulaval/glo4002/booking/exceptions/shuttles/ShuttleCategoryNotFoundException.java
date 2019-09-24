package ca.ulaval.glo4002.booking.exceptions.shuttles;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShuttleCategoryNotFoundException extends RuntimeException {

	public ShuttleCategoryNotFoundException() {
		super(ExceptionConstants.SHUTTLE_CATEGORY_NOT_FOUND_MESSAGE);
	}
}
