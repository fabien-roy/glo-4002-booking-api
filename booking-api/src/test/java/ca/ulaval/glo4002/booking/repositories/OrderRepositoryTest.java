package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.entities.OrderEntity;
import ca.ulaval.glo4002.booking.exceptions.orders.OrderAlreadyCreatedException;
import ca.ulaval.glo4002.booking.exceptions.orders.OrderNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderRepositoryTest {

    private OrderRepository subject;
    private OrderRepositoryContext context;

    @BeforeEach
    public void setUp() {
        context = new OrderRepositoryContext();
        subject = new OrderRepositoryImpl(context.entityManager);
    }

    @Test
    public void findById_shouldThrowOrderNotFoundException_whenOrderDoesNotExist() {
        OrderNotFoundException thrown = assertThrows(
                OrderNotFoundException.class,
                () -> subject.findById(OrderRepositoryContext.A_NON_EXISTANT_ORDER_ID)
        );

        assertEquals(ExceptionConstants.Order.NOT_FOUND_DESCRIPTION, thrown.getMessage());
    }

    @Test
    public void findById_shouldReturnCorrectOrder() {
        OrderEntity order = subject.findById(OrderRepositoryContext.A_ORDER_ID).get();

        assertEquals(context.aOrder, order);
    }

    @Test
    public void findAll_shouldReturnCorrectOrders() {
        List<OrderEntity> orders = new ArrayList<>();

        subject.findAll().forEach(orders::add);

        assertEquals(2, orders.size());
        assertTrue(orders.contains(context.aOrder));
        assertTrue(orders.contains(context.anotherOrder));
    }

    @Test
    public void save_shouldThrowOrderAlreadyCreatedException_whenOrderAlreadyExists() {
        OrderAlreadyCreatedException thrown = assertThrows(
                OrderAlreadyCreatedException.class,
                () -> subject.save(context.aOrder)
        );

        assertEquals(ExceptionConstants.Order.ALREADY_CREATED_DESCRIPTION, thrown.getMessage());
    }

    @Test
    public void save_shouldSaveOrder() {
        context.setUpEntityManagerForSave();

        subject.save(context.aNonExistentOrder);
        OrderEntity order = subject.findById(OrderRepositoryContext.A_NON_EXISTANT_ORDER_ID).get();

        assertEquals(context.aNonExistentOrder, order);
    }
}
