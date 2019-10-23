package ca.ulaval.glo4002.booking.dao;

import ca.ulaval.glo4002.booking.domain.Id;
import ca.ulaval.glo4002.booking.domain.orders.Order;
import ca.ulaval.glo4002.booking.domain.orders.OrderDate;
import ca.ulaval.glo4002.booking.domain.passes.Passes;
import ca.ulaval.glo4002.booking.exceptions.OrderAlreadyCreatedException;
import ca.ulaval.glo4002.booking.exceptions.OrderNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class OrderDaoTest {

    private static final Long A_ID = 0L;
    private static final Long ANOTHER_ID = 1L;
    private static final Long A_NON_EXISTENT_ID = -1L;

    private OrderDao subject;

    @BeforeEach
    public void setUpSubject() {
        this.subject = new OrderDao();
    }

    @Test
    public void get_shouldThrowOrderNotFoundException_whenThereIsNoOrder() {
        Id aNonExistentOrderId = new Id(A_NON_EXISTENT_ID);

        assertThrows(
                OrderNotFoundException.class,
                () -> subject.get(aNonExistentOrderId)
        );
    }

    @Test
    public void get_shouldThrowOrderNotFoundException_whenOrderDoesNotExist() {
        Id aNonExistentOrderId = new Id(A_NON_EXISTENT_ID);
        Id aOrderId = new Id(A_ID);
        Order aOrder = new Order(aOrderId);
        subject.save(aOrder);

        assertThrows(
                OrderNotFoundException.class,
                () -> subject.get(aNonExistentOrderId)
        );
    }

    @Test
    public void get_shouldReturnOrder() {
        Id aOrderId = new Id(A_ID);
        Order aOrder = new Order(aOrderId);
        subject.save(aOrder);

        Optional<Order> foundOrder = subject.get(aOrderId);

        assertTrue(foundOrder.isPresent());
        assertEquals(aOrder.getId(), foundOrder.get().getId());
    }

    @Test
    public void get_shouldReturnOrder_whenThereAreMultipleOrders() {
        Id aOrderId = new Id(A_ID);
        Id anotherOrderId = new Id(ANOTHER_ID);
        Order aOrder = new Order(aOrderId);
        Order anotherOrder = new Order(anotherOrderId);
        subject.save(aOrder);
        subject.save(anotherOrder);

        Optional<Order> foundOrder = subject.get(aOrderId);

        assertTrue(foundOrder.isPresent());
        assertEquals(aOrder.getId(), foundOrder.get().getId());
    }

    @Test
    public void getAll_shouldReturnAllOrders() {
        Id aOrderId = new Id(A_ID);
        Id anotherOrderId = new Id(ANOTHER_ID);
        Order aOrder = new Order(aOrderId);
        Order anotherOrder = new Order(anotherOrderId);
        subject.save(aOrder);
        subject.save(anotherOrder);

        List<Order> orders = subject.getAll();

        assertEquals(2, orders.size());
        assertTrue(orders.stream().anyMatch(order -> order.getId().equals(aOrder.getId())));
        assertTrue(orders.stream().anyMatch(order -> order.getId().equals(anotherOrder.getId())));
    }

    @Test
    public void getAll_shouldReturnEmptyList_whenThereIsNoOrder() {
        List<Order> orders = subject.getAll();

        assertTrue(orders.isEmpty());
    }

    @Test
    public void save_shouldThrowOrderAlreadyCreatedException_whenOrderAlreadyExists() {
        OrderDate orderDate = new OrderDate("2050-05-21T15:23:20.142Z");
        Order aOrder = new Order("VENDOR", orderDate, mock(Passes.class));
        subject.save(aOrder);

        assertThrows(
                OrderAlreadyCreatedException.class,
                () -> subject.save(aOrder)
        );
    }

    @Test
    void save_shouldReturnUniqueIds() {
        OrderDate orderDate = new OrderDate("2050-05-21T15:23:20.142Z");
        Order aOrder = new Order("VENDOR", orderDate, mock(Passes.class));
        Order anotherOrder = new Order("VENDOR", orderDate, mock(Passes.class));

        subject.save(aOrder);
        subject.save(anotherOrder);
        List<Order> savedOrders = subject.getAll();
        List<Order> distinctSavedOrders = savedOrders.stream().distinct().collect(Collectors.toList());

        assertEquals(2, distinctSavedOrders.size());
    }
}