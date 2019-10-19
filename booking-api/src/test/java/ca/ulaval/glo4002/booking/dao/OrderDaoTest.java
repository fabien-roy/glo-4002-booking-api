package ca.ulaval.glo4002.booking.dao;

import ca.ulaval.glo4002.booking.domain.Order;
import ca.ulaval.glo4002.booking.exceptions.OrderAlreadyCreatedException;
import ca.ulaval.glo4002.booking.exceptions.OrderNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OrderDaoTest {

    private OrderDao subject;

    @BeforeEach
    public void setUpSubject() {
        this.subject = new OrderDao();
    }

    @Test
    public void get_shouldThrowOrderNotFoundException_whenThereIsNoOrder() {
        assertThrows(
                OrderNotFoundException.class,
                () -> subject.get("aNonExistentOrderNumber")
        );
    }

    @Test
    public void get_shouldThrowOrderNotFoundException_whenOrderDoesNotExist() {
        Order aOrder = new Order("aOrderNumber");
        subject.save(aOrder);

        assertThrows(
                OrderNotFoundException.class,
                () -> subject.get("aNonExistentOrderNumber")
        );
    }

    @Test
    public void get_shouldReturnOrder() {
        String aOrderNumber = "aOrderNumber";
        Order aOrder = new Order(aOrderNumber);
        subject.save(aOrder);

        Optional<Order> foundOrder = subject.get(aOrderNumber);

        assertTrue(foundOrder.isPresent());
        assertEquals(aOrder.getOrderNumber(), foundOrder.get().getOrderNumber());
    }

    @Test
    public void get_shouldReturnOrder_whenThereAreMultipleOrders() {
        String aOrderNumber = "aOrderNumber";
        Order aOrder = new Order(aOrderNumber);
        Order anotherOrder = new Order("anotherOrderNumber");
        subject.save(aOrder);
        subject.save(anotherOrder);

        Optional<Order> foundOrder = subject.get(aOrderNumber);

        assertTrue(foundOrder.isPresent());
        assertEquals(aOrder.getOrderNumber(), foundOrder.get().getOrderNumber());
    }

    @Test
    public void getAll_shouldReturnAllOrders() {
        Order aOrder = new Order("aOrderNumber");
        Order anotherOrder = new Order("anotherOrderNumber");
        subject.save(aOrder);
        subject.save(anotherOrder);

        List<Order> orders = subject.getAll();

        assertEquals(2, orders.size());
        assertTrue(orders.stream().anyMatch(order -> order.getOrderNumber().equals(aOrder.getOrderNumber())));
        assertTrue(orders.stream().anyMatch(order -> order.getOrderNumber().equals(anotherOrder.getOrderNumber())));
    }

    @Test
    public void getAll_shouldReturnEmptyList_whenThereIsNoOrder() {
        List<Order> orders = subject.getAll();

        assertTrue(orders.isEmpty());
    }

    @Test
    public void save_shouldThrowOrderAlreadyCreatedException_whenOrderAlreadyExists() {
        Order aOrder = new Order("aOrderNumber");
        subject.save(aOrder);

        assertThrows(
                OrderAlreadyCreatedException.class,
                () -> subject.save(aOrder)
        );
    }
}