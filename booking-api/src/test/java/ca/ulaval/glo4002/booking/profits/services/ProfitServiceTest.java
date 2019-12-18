package ca.ulaval.glo4002.booking.profits.services;

import ca.ulaval.glo4002.booking.orders.domain.Order;
import ca.ulaval.glo4002.booking.orders.domain.OrderRepository;
import ca.ulaval.glo4002.booking.oxygen.domain.OxygenTank;
import ca.ulaval.glo4002.booking.oxygen.inventory.domain.OxygenInventoryRepository;
import ca.ulaval.glo4002.booking.profits.domain.Money;
import ca.ulaval.glo4002.booking.profits.rest.mappers.ProfitMapper;
import ca.ulaval.glo4002.booking.program.events.domain.Event;
import ca.ulaval.glo4002.booking.program.events.domain.EventRepository;
import ca.ulaval.glo4002.booking.shuttles.trips.domain.Trip;
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
    void getProfit_shouldAddExpenseForTrip_whenThereIsASingleTrip() {
        Money expectedExpense = new Money(BigDecimal.valueOf(100));
        Trip trip = mock(Trip.class);
        when(trip.getPrice()).thenReturn(expectedExpense);
        when(tripRepository.getArrivals()).thenReturn(Collections.singletonList(trip));

        profitService.getReport();

        verify(mapper).toResponse(argThat(profitReport -> profitReport.getExpense().equals(expectedExpense)));
    }

    @Test
    void getProfit_shouldAddExpenseForTrips_whenThereAreMultipleTrips() {
        int tripQuantity = 2;
        Money tripPrice = new Money(BigDecimal.valueOf(100));
        Money expectedExpense = tripPrice.multiply(BigDecimal.valueOf(tripQuantity));
        Trip trip = mock(Trip.class);
        when(trip.getPrice()).thenReturn(tripPrice);
        when(tripRepository.getArrivals()).thenReturn(Collections.nCopies(tripQuantity, trip));

        profitService.getReport();

        verify(mapper).toResponse(argThat(profitReport -> profitReport.getExpense().equals(expectedExpense)));
    }

    @Test
    void getProfit_shouldAddExpenseForOxygenTanks_whenThereIsASingleOxygenTank() {
        Money expectedExpense = new Money(BigDecimal.valueOf(100));
        OxygenTank tank = mock(OxygenTank.class);
        when(tank.getPrice()).thenReturn(expectedExpense);
        when(oxygenInventoryRepository.findAll()).thenReturn(Collections.singletonList(tank));

        profitService.getReport();

        verify(mapper).toResponse(argThat(profitReport -> profitReport.getExpense().equals(expectedExpense)));
    }

    @Test
    void getProfit_shouldAddExpenseForOxygenTanks_whenThereAreMultipleOxygenTanks() {
        int tankQuantity = 2;
        Money tankPrice = new Money(BigDecimal.valueOf(100));
        Money expectedExpense = tankPrice.multiply(BigDecimal.valueOf(tankQuantity));
        OxygenTank tank = mock(OxygenTank.class);
        when(tank.getPrice()).thenReturn(tankPrice);
        when(oxygenInventoryRepository.findAll()).thenReturn(Collections.nCopies(tankQuantity, tank));

        profitService.getReport();

        verify(mapper).toResponse(argThat(profitReport -> profitReport.getExpense().equals(expectedExpense)));
    }

    @Test
    void getProfit_shouldAddExpenseForEvent_whenThereIsASingleEvent() {
        Money expectedExpense = new Money(BigDecimal.valueOf(100));
        Event event = mock(Event.class);
        when(event.getPrice()).thenReturn(expectedExpense);
        when(eventRepository.findAll()).thenReturn(Collections.singletonList(event));

        profitService.getReport();

        verify(mapper).toResponse(argThat(profitReport -> profitReport.getExpense().equals(expectedExpense)));
    }

    @Test
    void getProfit_shouldAddExpenseForEvents_whenThereAreMultipleEvents() {
        int eventQuantity = 2;
        Money eventPrice = new Money(BigDecimal.valueOf(100));
        Money expectedExpense = eventPrice.multiply(BigDecimal.valueOf(eventQuantity));
        Event event = mock(Event.class);
        when(event.getPrice()).thenReturn(eventPrice);
        when(eventRepository.findAll()).thenReturn(Collections.nCopies(eventQuantity, event));

        profitService.getReport();

        verify(mapper).toResponse(argThat(profitReport -> profitReport.getExpense().equals(expectedExpense)));
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
    void getProfit_shouldAddRevenueForOrders_whenThereAreMultipleOrders() {
        int orderQuantity = 2;
        Money orderPrice = new Money(BigDecimal.valueOf(100));
        Money expectedRevenue = orderPrice.multiply(BigDecimal.valueOf(orderQuantity));
        Order order = mock(Order.class);
        when(order.getPrice()).thenReturn(orderPrice);
        when(orderRepository.findAll()).thenReturn(Collections.nCopies(orderQuantity, order));

        profitService.getReport();

        verify(mapper).toResponse(argThat(profitReport -> profitReport.getRevenue().equals(expectedRevenue)));
    }
}