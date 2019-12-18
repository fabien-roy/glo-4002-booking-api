package ca.ulaval.glo4002.booking.orders.rest;

import ca.ulaval.glo4002.booking.orders.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderResourceTest {

	private OrderResource resource;
	private OrderService service;

	@BeforeEach
	void setUpResource() {
		service = mock(OrderService.class);

		resource = new OrderResource(service);
	}

	@Test
	void getByOrderNumber_shouldReturnOk() {
		String anOrderNumber = "VENDOR-123";
		OrderResponse orderResponse = mock(OrderResponse.class);
		when(service.getByOrderNumber(anOrderNumber)).thenReturn(orderResponse);

		Response response = resource.getByOrderNumber(anOrderNumber);

		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	void addOrder_shouldReturnCreated() {
		OrderRefactoredRequest anOrderRequest = mock(OrderRefactoredRequest.class);
		when(service.order(anOrderRequest)).thenReturn("anOrderNumber");

		Response response = resource.addOrder(anOrderRequest);

		assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
	}

	@Test
	void addOrder_shouldReturnLocationHeaders() {
		OrderRefactoredRequest anOrderRequest = mock(OrderRefactoredRequest.class);
		String expectedOrderNumber = "expectedOrderNumber";
		when(service.order(anOrderRequest)).thenReturn(expectedOrderNumber);

		Response response = resource.addOrder(anOrderRequest);

		assertNotNull(response.getHeaders());
		assertEquals(1, response.getHeaders().get(HttpHeaders.LOCATION).size());
		assertEquals("/orders/" + expectedOrderNumber, response.getHeaders().get(HttpHeaders.LOCATION).get(0).toString());
	}
}