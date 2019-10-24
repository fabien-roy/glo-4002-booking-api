package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.orders.Order;
import ca.ulaval.glo4002.booking.domain.orders.OrderDate;
import ca.ulaval.glo4002.booking.domain.orders.OrderNumber;
import ca.ulaval.glo4002.booking.domain.passes.PassList;
import ca.ulaval.glo4002.booking.exceptions.orders.OrderAlreadyCreatedException;
import ca.ulaval.glo4002.booking.exceptions.orders.OrderNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderRepositoryTest {

    private OrderRepository subject;

    @BeforeEach
    void setUpSubject() {
        subject = new OrderRepository();
    }

    @Test
    void getOrderNumber_shouldThrowOrderNotFoundException_whenThereIsNoOrder() {
        OrderNumber aNonExistentOrderNumber = new OrderNumber(new Number(1L), "VENDOR");

        assertThrows(OrderNotFoundException.class, () -> subject.getByOrderNumber(aNonExistentOrderNumber));
    }

    @Test
    void getOrderNumber_shouldThrowOrderNotFoundException_whenOrderDoesNotExist() {
        OrderNumber aNonExistentOrderNumber = new OrderNumber(new Number(1L), "VENDOR");
        OrderNumber aOrderNumber = new OrderNumber(new Number(2L), "VENDOR");
        OrderDate aOrderDate = mock(OrderDate.class);
        PassList aPassList = mock(PassList.class);
        Order aOrder = new Order(aOrderNumber, aOrderDate, aPassList);
        subject.addOrder(aOrder);

        assertThrows(OrderNotFoundException.class, () -> subject.getByOrderNumber(aNonExistentOrderNumber));
    }

    @Test
    void getByOrderNumber_shouldReturnOrder() {
        OrderNumber aOrderNumber = new OrderNumber(new Number(1L), "VENDOR");
        OrderDate aOrderDate = mock(OrderDate.class);
        PassList aPassList = mock(PassList.class);
        Order aOrder = new Order(aOrderNumber, aOrderDate, aPassList);
        subject.addOrder(aOrder);

        Optional<Order> foundOrder = subject.getByOrderNumber(aOrderNumber);

        assertTrue(foundOrder.isPresent());
        assertEquals(aOrderNumber, foundOrder.get().getOrderNumber());
    }

    @Test
    void getByOrderNumber_shouldReturnOrders_whenThereAreMultipleOrders() {
        OrderNumber aOrderNumber = new OrderNumber(new Number(1L), "VENDOR");
        OrderNumber anotherOrderNumber = new OrderNumber(new Number(2L), "VENDOR");
        OrderDate aOrderDate = mock(OrderDate.class);
        PassList aPassList = mock(PassList.class);
        Order aOrder = new Order(aOrderNumber, aOrderDate, aPassList);
        Order anotherOrder = new Order(anotherOrderNumber, aOrderDate, aPassList);
        subject.addOrder(aOrder);
        subject.addOrder(anotherOrder);

        Optional<Order> foundOrder = subject.getByOrderNumber(aOrderNumber);
        Optional<Order> otherFoundOrder = subject.getByOrderNumber(anotherOrderNumber);

        assertTrue(foundOrder.isPresent());
        assertTrue(otherFoundOrder.isPresent());
        assertEquals(aOrderNumber, foundOrder.get().getOrderNumber());
        assertEquals(anotherOrderNumber, otherFoundOrder.get().getOrderNumber());
    }

    @Test
    void addOrder_shouldThrowOrderAlreadyCreatedException_whenOrderAlreadyExists() {
        OrderNumber aOrderNumber = new OrderNumber(new Number(1L), "VENDOR");
        OrderDate aOrderDate = mock(OrderDate.class);
        PassList aPassList = mock(PassList.class);
        Order aOrder = new Order(aOrderNumber, aOrderDate, aPassList);
        subject.addOrder(aOrder);

        assertThrows(OrderAlreadyCreatedException.class, () -> subject.addOrder(aOrder));
    }
}