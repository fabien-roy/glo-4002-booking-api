package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domain.Order;
import ca.ulaval.glo4002.booking.exceptions.OrderNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderRepositoryTest {

    private OrderRepository subject;

    @BeforeEach
    public void setUpSubject() {
        subject = new OrderRepository();
    }

    @Test
    public void getByOrderNumber_shouldThrowOrderNotFoundException_whenOrderDoesNotExist() {
        assertThrows(
                OrderNotFoundException.class,
                () -> subject.getByOrderNumber(A_NON_EXISTANT_ORDER_NUMBER)
        );
    }

    @Test
    public void getByOrderNumber_shouldReturnCorrectOrder() {
        Order order = subject.getByOrderNumber(A_ORDER_NUMBER);

        assertEquals(A_ORDER_NUMBER, order.getOrderNumber());
    }
}