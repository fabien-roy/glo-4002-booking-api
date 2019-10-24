package ca.ulaval.glo4002.booking.dao;

import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.orders.Order;
import ca.ulaval.glo4002.booking.domain.orders.OrderDate;
import ca.ulaval.glo4002.booking.domain.passes.PassList;
import ca.ulaval.glo4002.booking.exceptions.orders.OrderAlreadyCreatedException;
import ca.ulaval.glo4002.booking.exceptions.orders.OrderNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class OrderDaoTest {

    private static final Long A_ID = 0L;
    private static final Long ANOTHER_ID = 1L;
    private static final Long A_NON_EXISTENT_ID = -1L;

    private OrderDao subject;

    @BeforeEach
    void setUpSubject() {
        this.subject = new OrderDao();
    }

    @Test
    void get_shouldThrowOrderNotFoundException_whenThereIsNoOrder() {
        Number aNonExistentOrderId = new Number(A_NON_EXISTENT_ID);

        assertThrows(OrderNotFoundException.class, () -> subject.get(aNonExistentOrderId));
    }

    @Test
    void get_shouldThrowOrderNotFoundException_whenOrderDoesNotExist() {
        Number aNonExistentOrderId = new Number(A_NON_EXISTENT_ID);
        Number aOrderId = new Number(A_ID);
        Order aOrder = new Order(aOrderId);
        subject.save(aOrder);

        assertThrows(OrderNotFoundException.class, () -> subject.get(aNonExistentOrderId));
    }

    @Test
    void get_shouldReturnOrder() {
        Number aOrderId = new Number(A_ID);
        Order aOrder = new Order(aOrderId);
        subject.save(aOrder);

        Optional<Order> foundOrder = subject.get(aOrderId);

        assertTrue(foundOrder.isPresent());
        assertEquals(aOrder.getOrderNumber(), foundOrder.get().getOrderNumber());
    }

    @Test
    void get_shouldReturnOrder_whenThereAreMultipleOrders() {
        Number aOrderId = new Number(A_ID);
        Number anotherOrderId = new Number(ANOTHER_ID);
        Order aOrder = new Order(aOrderId);
        Order anotherOrder = new Order(anotherOrderId);
        subject.save(aOrder);
        subject.save(anotherOrder);

        Optional<Order> foundOrder = subject.get(aOrderId);

        assertTrue(foundOrder.isPresent());
        assertEquals(aOrder.getOrderNumber(), foundOrder.get().getOrderNumber());
    }

    @Test
    void getAll_shouldReturnAllOrders() {
        Number aOrderId = new Number(A_ID);
        Number anotherOrderId = new Number(ANOTHER_ID);
        Order aOrder = new Order(aOrderId);
        Order anotherOrder = new Order(anotherOrderId);
        subject.save(aOrder);
        subject.save(anotherOrder);

        List<Order> orders = subject.getAll();

        assertEquals(2, orders.size());
        assertTrue(orders.stream().anyMatch(order -> order.getOrderNumber().equals(aOrder.getOrderNumber())));
        assertTrue(orders.stream().anyMatch(order -> order.getOrderNumber().equals(anotherOrder.getOrderNumber())));
    }

    @Test
    void getAll_shouldReturnEmptyList_whenThereIsNoOrder() {
        List<Order> orders = subject.getAll();

        assertTrue(orders.isEmpty());
    }

    @Test
    void save_shouldThrowOrderAlreadyCreatedException_whenOrderAlreadyExists() {
        OrderDate orderDate = new OrderDate("2050-05-21T15:23:20.142Z");
        Order aOrder = new Order("VENDOR", orderDate, mock(PassList.class));
        subject.save(aOrder);

        assertThrows(OrderAlreadyCreatedException.class, () -> subject.save(aOrder));
    }

    @Test
    void save_shouldReturnUniqueIds() {
        OrderDate orderDate = new OrderDate("2050-05-21T15:23:20.142Z");
        Order aOrder = new Order("VENDOR", orderDate, mock(PassList.class));
        Order anotherOrder = new Order("VENDOR", orderDate, mock(PassList.class));

        subject.save(aOrder);
        subject.save(anotherOrder);

        assertNotEquals(aOrder.getOrderNumber(), anotherOrder.getOrderNumber());
    }
}