package ca.ulaval.glo4002.booking.endToEnd;

import ca.ulaval.glo4002.booking.constants.ControllerConstants;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsEventDatesDto;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsPassesDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

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
    public void getOrderController_shouldReturnCorrectOrderDto_whenOrderNumberIsExistent() {
        context.setUp().withAnOrder();

        ResponseEntity<OrderWithPassesAsPassesDto> response = (ResponseEntity<OrderWithPassesAsPassesDto>) context.orderController.getOrderById(context.anOrderId);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatusCodeValue());
        assertEquals(context.anOrderId, response.getBody().orderNumber);
    }

    @Test
    public void getOrderController_shouldReturnCorrectOrderDto_whenManyOrderNumberAreExistent() {
        context.setUp().withAnOrder().withAnotherOrder();

        ResponseEntity<OrderWithPassesAsPassesDto> aResponse = (ResponseEntity<OrderWithPassesAsPassesDto>) context.orderController.getOrderById(context.anOrderId);
        ResponseEntity<OrderWithPassesAsPassesDto> anotherResponse = (ResponseEntity<OrderWithPassesAsPassesDto>) context.orderController.getOrderById(context.anotherOrderId);

        assertEquals(Response.Status.OK.getStatusCode(), aResponse.getStatusCodeValue());
        assertEquals(Response.Status.OK.getStatusCode(), anotherResponse.getStatusCodeValue());
        assertEquals(context.anOrderId, aResponse.getBody().orderNumber);
        assertEquals(context.anotherOrderId, anotherResponse.getBody().orderNumber);
    }

    @Test
    public void postOrderController_shouldReturnHttpBadRequest_whenOrderIsInvalid() {
        OrderWithPassesAsEventDatesDto orderDto = context.anOrderDto;
        orderDto.orderNumber = OrderEndToEndContext.AN_INVALID_ORDER_ID;
        context.setUp();

        ResponseEntity<OrderWithPassesAsPassesDto> response = (ResponseEntity<OrderWithPassesAsPassesDto>) context.orderController.addOrder(orderDto);

        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatusCodeValue());
    }

    @Test
    public void postOrderController_shouldReturnHttpBadRequest_whenOrderAlreadyExists() {
        OrderWithPassesAsEventDatesDto orderDto = context.anOrderDto;
        context.setUp().withAnOrder();
        orderDto.orderNumber = context.anOrderId;

        ResponseEntity<OrderWithPassesAsPassesDto> response = (ResponseEntity<OrderWithPassesAsPassesDto>) context.orderController.addOrder(orderDto);

        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatusCodeValue());
    }

    @Test
    public void postOrderController_shouldReturnCorrectHeaders() {
        OrderWithPassesAsEventDatesDto orderDto = context.anOrderDto;
        context.setUp();

        ResponseEntity<OrderWithPassesAsPassesDto> response = (ResponseEntity<OrderWithPassesAsPassesDto>) context.orderController.addOrder(orderDto);

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatusCodeValue());
        assertEquals( "/orders/" + context.anOrderId, response.getHeaders().get(ControllerConstants.LOCATION_HEADER_NAME).get(0));
    }

    @Test
    public void postOrderController_shouldReturnHttpCreated_whenOrderIsValid() {
        OrderWithPassesAsEventDatesDto orderDto = context.anOrderDto;
        context.setUp();

        ResponseEntity<OrderWithPassesAsPassesDto> response = (ResponseEntity<OrderWithPassesAsPassesDto>) context.orderController.addOrder(orderDto);

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    public void postOrderController_shouldReturnCreated_whenManyOrdersAreValid() {
        OrderWithPassesAsEventDatesDto anOrderDto = context.anOrderDto;
        OrderWithPassesAsEventDatesDto anotherOrderDto = context.anotherOrderDto;
        context.setUp();

        ResponseEntity<OrderWithPassesAsPassesDto> aResponse = (ResponseEntity<OrderWithPassesAsPassesDto>) context.orderController.addOrder(anOrderDto);
        ResponseEntity<OrderWithPassesAsPassesDto> anotherResponse = (ResponseEntity<OrderWithPassesAsPassesDto>) context.orderController.addOrder(anotherOrderDto);

        assertEquals(Response.Status.CREATED.getStatusCode(), aResponse.getStatusCodeValue());
        assertEquals(Response.Status.CREATED.getStatusCode(), anotherResponse.getStatusCodeValue());
        assertNotNull(aResponse.getBody());
        assertNotNull(anotherResponse.getBody());
    }

    @Test
    public void postOrderController_shouldReturnUniqueOrderNumbers() {
        OrderWithPassesAsEventDatesDto anOrderDto = context.anOrderDto;
        OrderWithPassesAsEventDatesDto anotherOrderDto = context.anotherOrderDto;
        context.setUp();

        ResponseEntity<OrderWithPassesAsPassesDto> aResponse = (ResponseEntity<OrderWithPassesAsPassesDto>) context.orderController.addOrder(anOrderDto);
        ResponseEntity<OrderWithPassesAsPassesDto> anotherResponse = (ResponseEntity<OrderWithPassesAsPassesDto>) context.orderController.addOrder(anotherOrderDto);

        assertEquals(Response.Status.CREATED.getStatusCode(), aResponse.getStatusCodeValue());
        assertEquals(Response.Status.CREATED.getStatusCode(), anotherResponse.getStatusCodeValue());
        assertNotNull(aResponse.getBody().orderNumber);
        assertNotNull(anotherResponse.getBody().orderNumber);
        assertNotEquals(aResponse.getBody().orderNumber, anotherResponse.getBody().orderNumber);
    }
}
