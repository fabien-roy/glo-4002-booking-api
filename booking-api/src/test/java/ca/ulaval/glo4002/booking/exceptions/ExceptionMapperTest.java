package ca.ulaval.glo4002.booking.exceptions;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class ExceptionMapperTest {

	private ExceptionMapper mapper;

	@BeforeEach
	void initExceptionMapper() {
		mapper = new ExceptionMapper();
	}

	@Test
	void mapError_should_whenExceptionInstanceOfBookingException() {
		BookingException bookingException = mock(BookingException.class);
		when(bookingException.getStatus()).thenReturn(HttpStatus.NOT_FOUND);
		when(bookingException.getMessage()).thenReturn("aMessage");

		ResponseEntity response = mapper.mapError(bookingException);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	void mapError_shouldReturnBadRequest_whenExceptionNotInstanceOfBookingException() {
		Exception exception = mock(Exception.class);

		ResponseEntity response = mapper.mapError(exception);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

}
