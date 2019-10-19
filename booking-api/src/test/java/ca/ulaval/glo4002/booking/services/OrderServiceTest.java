package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domain.Order;
import ca.ulaval.glo4002.booking.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class OrderServiceTest {

    OrderService subject;
    OrderRepository repository;

    @BeforeEach
    public void setUpSubject() {
        repository = mock(OrderRepository.class);
        subject = new OrderService(repository);
    }

    @Test
    public void order_shouldAddOrder() {
        Order sentOrder = new Order("aOrderNumber");

        Order resultOrder = subject.order(sentOrder);

        verify(repository).addOrder(resultOrder);
    }
}