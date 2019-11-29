package ca.ulaval.glo4002.booking.orders.rest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ca.ulaval.glo4002.booking.orders.rest.OrderController;
import ca.ulaval.glo4002.booking.orders.rest.OrderWithPassesAsEventDatesDto;
import ca.ulaval.glo4002.booking.orders.rest.OrderWithPassesAsPassesDto;
import ca.ulaval.glo4002.booking.orders.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

import javax.ws.rs.core.Response;


class OrderControllerTest {

	private OrderController controller;
	private OrderService service;

	@BeforeEach
	void setUpController() {
		service = mock(OrderService.class);

		controller = new OrderController(service);
	}

	@Test
	void getByOrderNumber_shouldReturnOk() {
		String anOrderNumber = "VENDOR-123";
		when(service.getByOrderNumber(anOrderNumber)).thenReturn(mock(OrderWithPassesAsPassesDto.class));

		Response response = controller.getByOrderNumber(anOrderNumber);

		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	void addOrder_shouldReturnCreated() {
		OrderWithPassesAsEventDatesDto anOrderDto = mock(OrderWithPassesAsEventDatesDto.class);
		when(service.order(anOrderDto)).thenReturn("anOrderNumber");

		Response response = controller.addOrder(anOrderDto);

		assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
	}

	@Test
	void addOrder_shouldReturnLocationHeaders() {
		OrderWithPassesAsEventDatesDto anOrderDto = mock(OrderWithPassesAsEventDatesDto.class);
		String expectedOrderNumber = "expectedOrderNumber";
		when(service.order(anOrderDto)).thenReturn(expectedOrderNumber);

		Response response = controller.addOrder(anOrderDto);

		assertNotNull(response.getHeaders());
		assertEquals(1, response.getHeaders().get(HttpHeaders.LOCATION).size());
		assertEquals("/orders/" + expectedOrderNumber, response.getHeaders().get(HttpHeaders.LOCATION).get(0).toString());
	}
}