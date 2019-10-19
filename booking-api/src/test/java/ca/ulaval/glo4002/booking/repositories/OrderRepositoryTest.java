package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.dao.OrderDao;
import ca.ulaval.glo4002.booking.domain.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderRepositoryTest {

    private OrderRepository subject;
    private OrderDao dao;

    @BeforeEach
    public void setUpSubject() {
        dao = mock(OrderDao.class);
        subject = new OrderRepository(dao);
    }

    @Test
    public void getByOrderNumber_shouldReturnCorrectOrder() {
        Order expectedOrder = new Order("aOrderNumber");
        when(dao.get(expectedOrder.getOrderNumber())).thenReturn(Optional.of(expectedOrder));

        Optional<Order> order = subject.getByOrderNumber(expectedOrder.getOrderNumber());

        assertTrue(order.isPresent());
        assertEquals(expectedOrder.getOrderNumber(), order.get().getOrderNumber());
    }
}