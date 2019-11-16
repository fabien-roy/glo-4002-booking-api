package ca.ulaval.glo4002.booking.orders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ca.ulaval.glo4002.booking.exceptions.ExceptionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ca.ulaval.glo4002.booking.exceptions.InvalidFormatException;

class OrderControllerTest {

	private OrderController controller;
	private OrderService service;

	@BeforeEach
	void setUpController() {
		ExceptionMapper exceptionMapper = new ExceptionMapper();
		service = mock(OrderService.class);

		controller = new OrderController(exceptionMapper, service);
	}

	@Test
	void getByOrderNumber_shouldReturnBadRequest_whenBadRequest() {
		String anOrderNumber = "anOrderNumber";
		when(service.getByOrderNumber(anOrderNumber)).thenThrow(new InvalidFormatException());

		ResponseEntity<?> response = controller.getByOrderNumber(anOrderNumber);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test
	void getByOrderNumber_shouldReturnNotFound_whenOrderIsNotFound() {
		String anOrderNumber = "anOrderNumber";
		when(service.getByOrderNumber(anOrderNumber)).thenThrow(new OrderNotFoundException(anOrderNumber));

		ResponseEntity<?> response = controller.getByOrderNumber(anOrderNumber);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	void getByOrderNumber_shouldReturnOk() {
		String anOrderNumber = "VENDOR-123";
		when(service.getByOrderNumber(anOrderNumber)).thenReturn(mock(OrderWithPassesAsPassesDto.class));

		ResponseEntity<?> response = controller.getByOrderNumber(anOrderNumber);

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void addOrder_shouldReturnBadRequest_whenBadRequest() {
		OrderWithPassesAsEventDatesDto anOrderDto = mock(OrderWithPassesAsEventDatesDto.class);
		when(service.order(anOrderDto)).thenThrow(new InvalidFormatException());

		ResponseEntity<?> response = controller.addOrder(anOrderDto);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test
	void addOrder_shouldReturnCreated() {
		OrderWithPassesAsEventDatesDto anOrderDto = mock(OrderWithPassesAsEventDatesDto.class);
		when(service.order(anOrderDto)).thenReturn("anOrderNumber");

		ResponseEntity<?> response = controller.addOrder(anOrderDto);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	void addOrder_shouldReturnLocationHeaders() {
		OrderWithPassesAsEventDatesDto anOrderDto = mock(OrderWithPassesAsEventDatesDto.class);
		String expectedOrderNumber = "expectedOrderNumber";
		when(service.order(anOrderDto)).thenReturn(expectedOrderNumber);

		ResponseEntity<?> response = controller.addOrder(anOrderDto);

		assertNotNull(response.getHeaders());
		assertEquals(1, response.getHeaders().get(HttpHeaders.LOCATION).size());
		assertEquals("/orders/" + expectedOrderNumber, response.getHeaders().get(HttpHeaders.LOCATION).get(0));
	}
}