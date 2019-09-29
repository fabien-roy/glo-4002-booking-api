package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.domainObjects.orders.Order;
import ca.ulaval.glo4002.booking.exceptions.orders.OrderNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderServiceTest {

    private OrderServiceImpl subject;
    private OrderServiceContext context;

    @BeforeEach
    public void setUp() {
        context = new OrderServiceContext();
        subject = new OrderServiceImpl(context.repository);
    }

    @Test
    public void findById_shouldThrowOrderNotFoundException_whenOrderDoesNotExist() {
        OrderNotFoundException thrown = assertThrows(
                OrderNotFoundException.class,
                () -> subject.findById(OrderServiceContext.A_NON_EXISTANT_ORDER_ID)
        );

        assertEquals(ExceptionConstants.ORDER_NOT_FOUND_MESSAGE, thrown.getMessage());
    }

    @Test
    public void findById_shouldReturnCorrectOrder() {
        Order order = subject.findById(OrderServiceContext.A_ORDER_ID);

        assertEquals(context.aOrder, order);
    }

    @Test
    public void findAll_shouldReturnCorrectOrders() {
        List<Order> orders = new ArrayList<>();

        subject.findAll().forEach(orders::add);

        assertEquals(2, orders.size());
        assertTrue(orders.contains(context.aOrder));
        assertTrue(orders.contains(context.anotherOrder));
    }

    @Test
    public void save_shouldSaveOrder() {
        context.setUpRepositoryForSave();

        subject.save(context.aNonExistentOrder);
        Order order = subject.findById(OrderServiceContext.A_NON_EXISTANT_ORDER_ID);

        assertEquals(context.aNonExistentOrder, order);
    }
}
