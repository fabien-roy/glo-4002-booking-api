package ca.ulaval.glo4002.booking.errors;

import ca.ulaval.glo4002.booking.interfaces.rest.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

// TODO : Use correct ExceptionMapper (see CatchallExceptionMapper)
public class ExceptionMapper {

	public ResponseEntity mapError(Exception exception) {
		if (exception instanceof BookingException) {
			BookingException bookingException = (BookingException) exception;
			ErrorDto errorDto = bookingException.toErrorDto();
			HttpStatus status = bookingException.getStatus();

			return ResponseEntity.status(status).body(errorDto);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
}
