package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.orders.Order;
import ca.ulaval.glo4002.booking.domain.orders.OrderNumber;
import ca.ulaval.glo4002.booking.domain.passes.PassBundle;
import ca.ulaval.glo4002.booking.exceptions.OrderNotFoundException;
import ca.ulaval.glo4002.booking.factories.OrderFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InMemoryOrderRepositoryTest {

    private OrderRepository repository;

    @BeforeEach
    void setUpRepository() {
        repository = new InMemoryOrderRepository();
    }

    @Test
    void getOrderNumber_shouldThrowOrderNotFoundException_whenThereIsNoOrder() {
        OrderNumber aNonExistentOrderNumber = new OrderNumber(new Number(1L), "VENDOR");

        assertThrows(OrderNotFoundException.class, () -> repository.getByOrderNumber(aNonExistentOrderNumber));
    }

    @Test
    void getOrderNumber_shouldThrowOrderNotFoundException_whenOrderDoesNotExist() {
        OrderNumber aNonExistentOrderNumber = new OrderNumber(new Number(1L), "VENDOR");
        OrderNumber aOrderNumber = new OrderNumber(new Number(2L), "VENDOR");
        LocalDateTime aOrderDate = OrderFactory.START_DATE_TIME.plusDays(1);
        PassBundle aPassBundle = mock(PassBundle.class);
        Order aOrder = new Order(aOrderNumber, aOrderDate, aPassBundle);
        repository.addOrder(aOrder);

        assertThrows(OrderNotFoundException.class, () -> repository.getByOrderNumber(aNonExistentOrderNumber));
    }

    @Test
    void getByOrderNumber_shouldReturnOrder() {
        OrderNumber anOrderNumber = new OrderNumber(new Number(1L), "VENDOR");
        LocalDateTime aOrderDate = OrderFactory.START_DATE_TIME.plusDays(1);
        PassBundle aPassBundle = mock(PassBundle.class);
        Order aOrder = new Order(anOrderNumber, aOrderDate, aPassBundle);
        repository.addOrder(aOrder);

        Order foundOrder = repository.getByOrderNumber(anOrderNumber);

        assertEquals(anOrderNumber, foundOrder.getOrderNumber());
    }

    @Test
    void getByOrderNumber_shouldReturnOrders_whenThereAreMultipleOrders() {
        OrderNumber anOrderNumber = new OrderNumber(new Number(1L), "VENDOR");
        OrderNumber anotherOrderNumber = new OrderNumber(new Number(2L), "VENDOR");
        LocalDateTime aOrderDate = OrderFactory.START_DATE_TIME.plusDays(1);
        PassBundle aPassBundle = mock(PassBundle.class);
        Order aOrder = new Order(anOrderNumber, aOrderDate, aPassBundle);
        Order anotherOrder = new Order(anotherOrderNumber, aOrderDate, aPassBundle);
        repository.addOrder(aOrder);
        repository.addOrder(anotherOrder);

        Order foundOrder = repository.getByOrderNumber(anOrderNumber);
        Order otherFoundOrder = repository.getByOrderNumber(anotherOrderNumber);

        assertEquals(anOrderNumber, foundOrder.getOrderNumber());
        assertEquals(anotherOrderNumber, otherFoundOrder.getOrderNumber());
    }
}