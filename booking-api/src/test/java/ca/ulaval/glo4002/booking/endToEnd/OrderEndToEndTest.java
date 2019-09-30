package ca.ulaval.glo4002.booking.endToEnd;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.dto.OrderDto;
import ca.ulaval.glo4002.booking.exceptions.orders.OrderNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

        OrderNotFoundException thrown = assertThrows(
                OrderNotFoundException.class,
                () -> context.orderController.getOrderById(context.anOrderId)
        );

        assertEquals(ExceptionConstants.ORDER_NOT_FOUND_MESSAGE, thrown.getMessage());
    }

    @Test
    public void getOrderController_shouldReturnHttpErrorPageNotFound_whenOrderNumberIsInvalid() {
        context.setUp();

        OrderNotFoundException thrown = assertThrows(
                OrderNotFoundException.class,
                () -> context.orderController.getOrderById(OrderEndToEndContext.AN_INVALID_ORDER_ID)
        );

        assertEquals(ExceptionConstants.ORDER_NOT_FOUND_MESSAGE, thrown.getMessage());
    }

    @Test
    public void getOrderController_shouldReturnCorrectOrderDot_whenOrderNumberIsExistent() {
        context.setUp().withAnOrder();

        OrderDto aDto = context.orderController.getOrderById(context.anOrderId);
        OrderNotFoundException thrown = assertThrows(
                OrderNotFoundException.class,
                () -> context.orderController.getOrderById(context.anotherOrderId)
        );

        assertNotNull(aDto);
        assertEquals(ExceptionConstants.ORDER_NOT_FOUND_MESSAGE, thrown.getMessage());
    }

    @Test
    public void getOrderController_shouldReturnCorrectOrderDot_whenManyOrderNumberAreExistent() {
        context.setUp().withAnOrder().withAnotherOrder();

        OrderDto aDto = context.orderController.getOrderById(context.anOrderId);
        OrderDto anotherDto = context.orderController.getOrderById(context.anotherOrderId);

        assertNotNull(aDto);
        assertNotNull(anotherDto);
    }

    // TODO : Get all tests

    // TODO : Post tests
}
