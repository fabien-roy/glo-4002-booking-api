package ca.ulaval.glo4002.booking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ca.ulaval.glo4002.booking.errors.ErrorDto;

public class ExceptionMapper {

	public ResponseEntity mapError(Exception exception) {
		if (exception instanceof BookingException) {
			BookingException bookingException = (BookingException) exception;
			ErrorDto errorDto = bookingException.toErrorDto(); // TODO : OPT : ErrorDto should be built by ExceptionMapper
			HttpStatus status = bookingException.getStatus(); // TODO : OPT : Status should be handled by ExceptionMapper, not Exception

			return ResponseEntity.status(status).body(errorDto);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
}
