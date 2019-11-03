package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.domain.orders.Order;
import ca.ulaval.glo4002.booking.domain.orders.OrderNumber;
import ca.ulaval.glo4002.booking.domain.passes.Pass;
import ca.ulaval.glo4002.booking.domain.passes.PassCategory;
import ca.ulaval.glo4002.booking.domain.passes.PassBundle;
import ca.ulaval.glo4002.booking.domain.passes.PassOption;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsEventDatesDto;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsPassesDto;
import ca.ulaval.glo4002.booking.dto.PassBundleDto;
import ca.ulaval.glo4002.booking.enums.PassCategories;
import ca.ulaval.glo4002.booking.enums.PassOptions;
import ca.ulaval.glo4002.booking.factories.OrderFactory;
import ca.ulaval.glo4002.booking.mappers.OrderMapper;
import ca.ulaval.glo4002.booking.mappers.PassBundleMapper;
import ca.ulaval.glo4002.booking.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderServiceTest {

    private OrderService subject;
    private OrderRepository repository;
    private OrderFactory factory;

    @BeforeEach
    void setUpSubject() {
        repository = mock(OrderRepository.class);
        factory = mock(OrderFactory.class);
        OrderMapper mapper = new OrderMapper(new PassBundleMapper());

        subject = new OrderService(repository, factory, mapper);
    }

    @Test
    void order_shouldAddOrder() {
        String aVendorCode = "aVendorCode";
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                "aOrderDate",
                aVendorCode,
                mock(PassBundleDto.class)
        );
        Order order = mock(Order.class);
        when(order.getOrderNumber()).thenReturn(new OrderNumber(new Number(1L), aVendorCode));
        when(factory.build(any())).thenReturn(order);

        subject.order(orderDto);

        verify(repository).addOrder(any());
    }

    @Test
    void getByOrderNumber_shouldGetOrder() {
        OrderNumber aOrderNumber = new OrderNumber(new Number(1L), "Vendor");
        Pass pass = mock(Pass.class);
        when(pass.getPassNumber()).thenReturn(new Number(1L));
        when(pass.getPrice()).thenReturn(new Money(new BigDecimal(0.0)));
        PassBundle passBundle = new PassBundle(
                Collections.singletonList(pass),
                new PassCategory(PassCategories.SUPERNOVA.toString(), null),
                new PassOption(PassOptions.PACKAGE.toString())
        );
        Order order = new Order(aOrderNumber, OrderFactory.START_DATE_TIME, passBundle);
        when(repository.getByOrderNumber(aOrderNumber)).thenReturn(Optional.of(order));

        OrderWithPassesAsPassesDto orderDto = subject.getByOrderNumber(aOrderNumber.toString());

        assertEquals(order.getPrice().getValue().doubleValue(), orderDto.getOrderPrice());
    }
}