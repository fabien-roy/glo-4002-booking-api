package ca.ulaval.glo4002.booking.endToEnd;

import ca.ulaval.glo4002.booking.dto.OrderDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    public void getAllOrderController_shouldReturnAnEmptyList_whenThereIsNoOrder() {
        context.setUp();

        ResponseEntity<List<OrderDto>> response = context.orderController.getOrders();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatusCodeValue());
        assertTrue(response.getBody().isEmpty());
    }

    @Test
    public void getAllOrderController_shouldReturnCorrectOrderDto_whenOrderIsExistent() {
        context.setUp().withAnOrder();

        ResponseEntity<List<OrderDto>> response = context.orderController.getOrders();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatusCodeValue());
        assertEquals(context.anOrderId, response.getBody().get(0).orderNumber);
        assertEquals(response.getBody().size(), 1);
    }

    @Test
    public void getAllOrderController_shouldReturnCorrectOrderDtos_whenManyOrdersAreExistent() {
        context.setUp().withAnOrder().withAnotherOrder();

        ResponseEntity<List<OrderDto>> response = context.orderController.getOrders();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatusCodeValue());
        assertTrue(response.getBody().stream().anyMatch(orderDto -> orderDto.orderNumber.equals(context.anOrderId)));
        assertTrue(response.getBody().stream().anyMatch(orderDto -> orderDto.orderNumber.equals(context.anotherOrderId)));
        assertEquals(response.getBody().size(), 2);
    }

    @Test
    public void postOrderController_shouldReturnHttpBadRequest_whenOrderIsInvalid() {
        OrderDto orderDto = context.orderParser.toDto(context.orderParser.parseEntity(context.anOrder));
        orderDto.orderNumber = OrderEndToEndContext.AN_INVALID_ORDER_ID;
        context.setUp();

        ResponseEntity<OrderDto> response = (ResponseEntity<OrderDto>) context.orderController.addOrder(orderDto);

        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatusCodeValue());
    }

    @Test
    public void postOrderController_shouldReturnHttpBadRequest_whenOrderAlreadyExists() {
        OrderDto orderDto = context.orderParser.toDto(context.orderParser.parseEntity(context.anOrder));
        context.setUp().withAnOrder();
        orderDto.orderNumber = context.anOrderId;

        ResponseEntity<OrderDto> response = (ResponseEntity<OrderDto>) context.orderController.addOrder(orderDto);

        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatusCodeValue());
    }

    @Test
    public void postOrderController_shouldReturnHttpCreated_whenOrderIsValid() {
        OrderDto orderDto = context.orderParser.toDto(context.orderParser.parseEntity(context.anOrder));
        context.setUp();

        ResponseEntity<OrderDto> response = (ResponseEntity<OrderDto>) context.orderController.addOrder(orderDto);

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    public void postOrderController_shouldReturnCreated_whenManyOrdersAreValid() {
        OrderDto anOrderDto = context.orderParser.toDto(context.orderParser.parseEntity(context.anOrder));
        OrderDto anotherOrderDto = context.orderParser.toDto(context.orderParser.parseEntity(context.anotherOrder));
        context.setUp();

        ResponseEntity<OrderDto> aResponse = (ResponseEntity<OrderDto>) context.orderController.addOrder(anOrderDto);
        ResponseEntity<OrderDto> anotherResponse = (ResponseEntity<OrderDto>) context.orderController.addOrder(anotherOrderDto);

        assertEquals(Response.Status.CREATED.getStatusCode(), aResponse.getStatusCodeValue());
        assertEquals(Response.Status.CREATED.getStatusCode(), anotherResponse.getStatusCodeValue());
        assertNotNull(aResponse.getBody());
        assertNotNull(anotherResponse.getBody());
    }

    // TODO : ACP-COS 1 : Id should not be null
}
