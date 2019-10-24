package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.dao.OrderDao;
import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.orders.Order;
import ca.ulaval.glo4002.booking.domain.passes.Pass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderRepositoryTest {

    private static final Long A_ID = 1L;
    private OrderDao dao;
    private PassRepository passRepository;

    private OrderRepository subject;

    @BeforeEach
    void setUpSubject() {
        dao = mock(OrderDao.class);
        passRepository = mock(PassRepository.class);

        subject = new OrderRepository(dao, passRepository);
    }

    @Test
    void getByOrderNumber_shouldReturnCorrectOrder() {
        /*
        Number expectedOrderId = new Number(A_ID);
        Order expectedOrder = new Order(expectedOrderId);
        when(dao.get(expectedOrder.getId())).thenReturn(Optional.of(expectedOrder));

        Optional<Order> order = subject.getById(expectedOrder.getId());

        assertTrue(order.isPresent());
        assertEquals(expectedOrderId, order.get().getId());
        */
    }

    @Test
    void addOrder_shouldSaveOrder() {
        Order order = new Order(mock(Number.class));

        subject.addOrder(order);

        verify(dao).save(order);
    }

    @Test
    void addOrder_shouldAddEachPasses() {
        int numberOfPasses = 2;
        Order order = mock(Order.class);
        when(order.getPasses()).thenReturn(new ArrayList<>(Collections.nCopies(numberOfPasses, new Pass())));

        subject.addOrder(order);

        verify(passRepository, times(numberOfPasses)).addPass(any(Pass.class));
    }
}