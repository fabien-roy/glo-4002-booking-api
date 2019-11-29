package ca.ulaval.glo4002.booking.orders.rest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ca.ulaval.glo4002.booking.orders.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

import javax.ws.rs.core.Response;


class OrderResourceTest {

	private OrderResource controller;
	private OrderService service;

	@BeforeEach
	void setUpResource() {
		service = mock(OrderService.class);

		controller = new OrderResource(service);
	}

	@Test
	void getByOrderNumber_shouldReturnOk() {
		String anOrderNumber = "VENDOR-123";
		when(service.getByOrderNumber(anOrderNumber)).thenReturn(mock(OrderResponse.class));

		Response response = controller.getByOrderNumber(anOrderNumber);

		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	void addOrder_shouldReturnCreated() {
		OrderRequest anOrderRequest = mock(OrderRequest.class);
		when(service.order(anOrderRequest)).thenReturn("anOrderNumber");

		Response response = controller.addOrder(anOrderRequest);

		assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
	}

	@Test
	void addOrder_shouldReturnLocationHeaders() {
		OrderRequest anOrderRequest = mock(OrderRequest.class);
		String expectedOrderNumber = "expectedOrderNumber";
		when(service.order(anOrderRequest)).thenReturn(expectedOrderNumber);

		Response response = controller.addOrder(anOrderRequest);

		assertNotNull(response.getHeaders());
		assertEquals(1, response.getHeaders().get(HttpHeaders.LOCATION).size());
		assertEquals("/orders/" + expectedOrderNumber, response.getHeaders().get(HttpHeaders.LOCATION).get(0).toString());
	}
}