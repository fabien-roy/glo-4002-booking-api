package ca.ulaval.glo4002.booking.endToEnd;

import ca.ulaval.glo4002.booking.dto.OrderDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderEndToEndTest {

    private OrderEndToEndContext context;

    @BeforeEach
    public void setUp() {
        context = new OrderEndToEndContext();
    }

    @Test
    public void getOrderController_shouldReturnHttpErrorPageNotFound_whenOrderNumberIsNonExistent() {
        context.setUp();

        ResponseEntity response = context.orderController.getOrderById(context.anOrderId);

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatusCodeValue());
    }

    @Test
    public void getOrderController_shouldReturnHttpErrorPageNotFound_whenOrderNumberIsInvalid() {
        context.setUp();

        ResponseEntity response = context.orderController.getOrderById(OrderEndToEndContext.AN_INVALID_ORDER_ID);

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatusCodeValue());
    }

    @Test
    public void getOrderController_shouldReturnCorrectOrderDot_whenOrderNumberIsExistent() {
        context.setUp().withAnOrder();

        ResponseEntity<OrderDto> response = (ResponseEntity<OrderDto>) context.orderController.getOrderById(context.anOrderId);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatusCodeValue());
        assertEquals(context.anOrderId, response.getBody().orderNumber);
    }

    @Test
    public void getOrderController_shouldReturnCorrectOrderDot_whenManyOrderNumberAreExistent() {
        context.setUp().withAnOrder().withAnotherOrder();

        ResponseEntity<OrderDto> aResponse = (ResponseEntity<OrderDto>) context.orderController.getOrderById(context.anOrderId);
        ResponseEntity<OrderDto> anotherResponse = (ResponseEntity<OrderDto>) context.orderController.getOrderById(context.anotherOrderId);

        assertEquals(Response.Status.OK.getStatusCode(), aResponse.getStatusCodeValue());
        assertEquals(Response.Status.OK.getStatusCode(), anotherResponse.getStatusCodeValue());
        assertEquals(context.anOrderId, aResponse.getBody().orderNumber);
        assertEquals(context.anotherOrderId, anotherResponse.getBody().orderNumber);
    }

    // TODO : Get all tests

    // TODO : Post tests
}
