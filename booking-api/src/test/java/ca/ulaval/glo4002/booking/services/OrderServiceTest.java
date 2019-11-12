package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.domain.orders.Order;
import ca.ulaval.glo4002.booking.domain.orders.OrderNumber;
import ca.ulaval.glo4002.booking.domain.passes.Pass;
import ca.ulaval.glo4002.booking.domain.passes.PassCategory;
import ca.ulaval.glo4002.booking.domain.passes.PassBundle;
import ca.ulaval.glo4002.booking.dto.orders.OrderWithPassesAsEventDatesDto;
import ca.ulaval.glo4002.booking.dto.orders.OrderWithPassesAsPassesDto;
import ca.ulaval.glo4002.booking.dto.passes.PassBundleDto;
import ca.ulaval.glo4002.booking.enums.PassCategories;
import ca.ulaval.glo4002.booking.enums.PassOptions;
import ca.ulaval.glo4002.booking.factories.OrderFactory;
import ca.ulaval.glo4002.booking.mappers.OrderMapper;
import ca.ulaval.glo4002.booking.mappers.PassBundleMapper;
import ca.ulaval.glo4002.booking.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderServiceTest {

    private OrderService service;
    private OrderRepository repository;
    private OrderFactory factory;
    private TripService tripService;
    private OxygenInventoryService oxygenInventoryService;

    @BeforeEach
    void setUpService() {
        repository = mock(OrderRepository.class);
        factory = mock(OrderFactory.class);
        OrderMapper mapper = new OrderMapper(new PassBundleMapper());
        tripService = mock(TripService.class);
        oxygenInventoryService = mock(OxygenInventoryService.class);

        service = new OrderService(repository, factory, mapper, tripService, oxygenInventoryService);
    }

    @Test
    void order_shouldAddOrder() {
        String aVendorCode = "aVendorCode";
        Order order = mockOrder(aVendorCode, PassCategories.SUPERNOVA);
        OrderWithPassesAsEventDatesDto orderDto = mockOrderDto(aVendorCode);

        service.order(orderDto);

        verify(repository).addOrder(eq(order));
    }

    @Test
    void order_shouldOrderTripsWithCorrectPassCategory() {
        String aVendorCode = "aVendorCode";
        OrderWithPassesAsEventDatesDto orderDto = mockOrderDto(aVendorCode);
        PassCategories expectedPassCategory = PassCategories.SUPERNOVA;
        mockOrder(aVendorCode, expectedPassCategory);

        service.order(orderDto);

        verify(tripService).orderAll(eq(expectedPassCategory), any());
    }

    @Test
    void order_shouldOrderTripsWithCorrectPasses() {
        String aVendorCode = "aVendorCode";
        OrderWithPassesAsEventDatesDto orderDto = mockOrderDto(aVendorCode);
        List<Pass> expectedPasses = new ArrayList<>();
        mockOrder(aVendorCode, PassCategories.SUPERNOVA, expectedPasses);

        service.order(orderDto);

        verify(tripService).orderAll(any(), eq(expectedPasses));
    }

    // TODO : Call to OxygenTankInventoryService tests

    @Test
    void getByOrderNumber_shouldGetOrder() {
        OrderNumber aOrderNumber = new OrderNumber(new Number(1L), "Vendor");
        Pass pass = mock(Pass.class);
        when(pass.getPassNumber()).thenReturn(new Number(1L));
        when(pass.getPrice()).thenReturn(new Money(new BigDecimal(0.0)));
        PassBundle passBundle = new PassBundle(
                Collections.singletonList(pass),
                new PassCategory(PassCategories.SUPERNOVA, null),
                PassOptions.PACKAGE
        );
        Order order = new Order(aOrderNumber, OrderFactory.START_DATE_TIME, passBundle);
        when(repository.getByOrderNumber(aOrderNumber)).thenReturn(order);

        OrderWithPassesAsPassesDto orderDto = service.getByOrderNumber(aOrderNumber.toString());

        assertEquals(order.getPrice().getValue().doubleValue(), orderDto.getOrderPrice());
    }

    private Order mockOrder(String vendorCode, PassCategories passCategory) {
        return mockOrder(vendorCode, passCategory, new ArrayList<>());
    }

    private Order mockOrder(String vendorCode, PassCategories passCategory, List<Pass> passes) {
        Order order = mock(Order.class);
        when(order.getOrderNumber()).thenReturn(new OrderNumber(new Number(1L), vendorCode));
        PassBundle passBundle = mock(PassBundle.class);
        when(passBundle.getCategory()).thenReturn(passCategory);
        when(passBundle.getPasses()).thenReturn(passes);
        when(order.getPassBundle()).thenReturn(passBundle);
        when(factory.build(any())).thenReturn(order);

        return order;
    }

    private OrderWithPassesAsEventDatesDto mockOrderDto(String vendorCode) {
        return new OrderWithPassesAsEventDatesDto(
                "aOrderDate",
                vendorCode,
                mock(PassBundleDto.class)
        );
    }
}