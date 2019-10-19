package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.exceptions.OrderNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    OrderService subject;
    OrderRepository repository;

    @BeforeEach
    public void setUpSubject() {
        repository = mock(OrderRepository.class);
        subject = new OrderService(repository);
    }

    @Test
    public void order_shouldOrder() {
        subject.order(A_ORDER);

        verify(repository).
    }
}