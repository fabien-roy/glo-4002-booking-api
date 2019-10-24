package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsEventDatesDto;
import ca.ulaval.glo4002.booking.dto.PassListDto;
import ca.ulaval.glo4002.booking.factories.OrderFactory;
import ca.ulaval.glo4002.booking.parsers.OrderParser;
import ca.ulaval.glo4002.booking.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class OrderServiceTest {

    private OrderService subject;
    private OrderRepository repository;
    private OrderFactory factory;
    private OrderParser parser;

    @BeforeEach
    void setUpSubject() {
        repository = mock(OrderRepository.class);
        factory = mock(OrderFactory.class);
        parser = mock(OrderParser.class);
        subject = new OrderService(repository, factory, parser);
    }

    @Test
    void order_shouldAddOrder() {
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                "aOrderDate",
                "aVendorCode",
                mock(PassListDto.class)
        );

        subject.order(orderDto);

        verify(repository).addOrder(any());
    }
}