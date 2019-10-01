package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.domainobjects.orders.Order;
import ca.ulaval.glo4002.booking.exceptions.orders.OrderNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderServiceTest {

    private OrderServiceContext context;

    @BeforeEach
    public void setUp() {
        context = new OrderServiceContext();
    }

    @Test
    public void findById_shouldThrowOrderNotFoundException_whenOrderDoesNotExist() {
        OrderNotFoundException thrown = assertThrows(
                OrderNotFoundException.class,
                () -> context.subject.findById(OrderServiceContext.A_NON_EXISTANT_ORDER_ID)
        );

        assertEquals(ExceptionConstants.Order.NOT_FOUND_MESSAGE, thrown.getMessage());
    }

    @Test
    public void findById_shouldReturnCorrectOrder() {
        Order order = context.subject.findById(OrderServiceContext.A_ORDER_ID);

        assertEquals(context.aOrder.getId(), order.getId());
    }

    @Test
    public void findAll_shouldReturnCorrectOrders() {
        List<Long> ids = new ArrayList<>();

        context.subject.findAll().forEach(order -> ids.add(order.getId()));

        assertEquals(2, ids.size());
        assertTrue(ids.contains(context.aOrder.getId()));
        assertTrue(ids.contains(context.anotherOrder.getId()));
    }

    @Test
    public void save_shouldSaveOrder() {
        context.setUpRepositoryForSave();

        context.subject.order(context.aNonExistentOrder);
        Order order = context.subject.findById(OrderServiceContext.A_NON_EXISTANT_ORDER_ID);

        assertEquals(context.aNonExistentOrder.getId(), order.getId());
    }
}
