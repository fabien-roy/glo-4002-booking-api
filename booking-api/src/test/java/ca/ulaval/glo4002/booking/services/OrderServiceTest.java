package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsEventDatesDto;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsPassesDto;
import ca.ulaval.glo4002.booking.dto.PassListDto;
import ca.ulaval.glo4002.booking.factories.OrderFactory;
import ca.ulaval.glo4002.booking.mappers.OrderMapper;
import ca.ulaval.glo4002.booking.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderServiceTest {

    private OrderService subject;
    private OrderRepository repository;

    @BeforeEach
    void setUpSubject() {
        repository = mock(OrderRepository.class);
        OrderFactory factory = mock(OrderFactory.class);
        OrderMapper mapper = mock(OrderMapper.class);

        subject = new OrderService(repository, factory, mapper);
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

    @Test
    void getByOrderNumber_shouldGetOrder() {
        /*
        String aOrderNumber = "aOrderNumber";
        OrderWithPassesAsPassesDto orderDto
        when(repository.getByOrderNumber(aOrderNumber)).thenReturn(

        OrderWithPassesAsPassesDto orderDto =
        */
    }
}