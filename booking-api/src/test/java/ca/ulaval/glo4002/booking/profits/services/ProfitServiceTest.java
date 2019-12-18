package ca.ulaval.glo4002.booking.profits.services;

import ca.ulaval.glo4002.booking.orders.domain.Order;
import ca.ulaval.glo4002.booking.orders.domain.OrderRepository;
import ca.ulaval.glo4002.booking.oxygen.inventory.domain.OxygenInventoryRepository;
import ca.ulaval.glo4002.booking.profits.domain.Money;
import ca.ulaval.glo4002.booking.profits.rest.mappers.ProfitMapper;
import ca.ulaval.glo4002.booking.program.events.domain.EventRepository;
import ca.ulaval.glo4002.booking.shuttles.trips.domain.TripRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;

import static org.mockito.Mockito.*;

class ProfitServiceTest {

	private ProfitService profitService;
	private OrderRepository orderRepository;
	private OxygenInventoryRepository oxygenInventoryRepository;
	private TripRepository tripRepository;
	private EventRepository eventRepository;
	private ProfitMapper mapper;

    @BeforeEach
    void setUpService() {
        orderRepository = mock(OrderRepository.class);
        oxygenInventoryRepository = mock(OxygenInventoryRepository.class);
        tripRepository = mock(TripRepository.class);
        eventRepository = mock(EventRepository.class);
    	mapper = mock(ProfitMapper.class);

    	profitService = new ProfitService(orderRepository, oxygenInventoryRepository, tripRepository, eventRepository, mapper);
    }

    @Test
    void getProfit_shouldAddRevenueForOrder_whenThereIsASingleOrder() {
        Money expectedRevenue = new Money(BigDecimal.valueOf(100));
        Order order = mock(Order.class);
        when(order.getPrice()).thenReturn(expectedRevenue);
        when(orderRepository.findAll()).thenReturn(Collections.singletonList(order));

        profitService.getReport();

        verify(mapper).toResponse(argThat(profitReport -> profitReport.getRevenue().equals(expectedRevenue)));
    }

    @Test
    void getProfit_shouldAddRevenueForOrder_whenThereAreMultipleOrders() {
        int orderQuantity = 2;
        Money orderPrice = new Money(BigDecimal.valueOf(100));
        Money expectedRevenue = orderPrice.multiply(BigDecimal.valueOf(orderQuantity));
        Order order = mock(Order.class);
        when(order.getPrice()).thenReturn(orderPrice);
        when(orderRepository.findAll()).thenReturn(Collections.nCopies(orderQuantity, order));

        profitService.getReport();

        verify(mapper).toResponse(argThat(profitReport -> profitReport.getRevenue().equals(expectedRevenue)));
    }

    // TODO : Rest of tests
}