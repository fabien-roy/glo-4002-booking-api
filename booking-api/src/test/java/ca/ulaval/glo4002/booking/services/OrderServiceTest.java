package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domain.Id;
import ca.ulaval.glo4002.booking.domain.Order;
import ca.ulaval.glo4002.booking.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class OrderServiceTest {

    private static final Long A_ID = 1L;
    private OrderService subject;
    private OrderRepository repository;

    @BeforeEach
    void setUpSubject() {
        repository = mock(OrderRepository.class);
        subject = new OrderService(repository);
    }

    @Test
    void order_shouldAddOrder() {
        Id sentOrderId = new Id(A_ID);
        Order sentOrder = new Order(sentOrderId);

        Order resultOrder = subject.order(sentOrder);

        verify(repository).addOrder(resultOrder);
    }
}