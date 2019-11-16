package ca.ulaval.glo4002.booking.profits;

import ca.ulaval.glo4002.booking.events.Event;
import ca.ulaval.glo4002.booking.events.EventRepository;
import ca.ulaval.glo4002.booking.orders.Order;
import ca.ulaval.glo4002.booking.orders.OrderRepository;
import ca.ulaval.glo4002.booking.shuttles.trips.Trip;
import ca.ulaval.glo4002.booking.shuttles.trips.TripRepository;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

class ProfitServiceTest {
	
	private static final Money tripPrice = new Money(new BigDecimal(10));

	ProfitService profitService;
	
	// TODO : ADD OXY, and finish setup
    @BeforeEach
    void setupProfitService() {
    	Trip mockedTrip = mock(Trip.class);
    	Event mockedEvent = mock(Event.class);
    	Order mockedOrder = mock(Order.class);
    	
    	List<Trip> tripList = Collections.nCopies(10, mockedTrip);
    	List<Event> eventList = Collections.nCopies(8, mockedEvent);
    	List<Order> orderList = Collections.nCopies(200, mockedOrder);
    	
    	TripRepository mockedTripRepository = mock(TripRepository.class);
    	OrderRepository mockedOrderRepository = mock(OrderRepository.class);
    	EventRepository mockedEventRepository = mock(EventRepository.class);
    	
    	when(mockedTripRepository.getDepartures()).thenReturn(tripList);
    	when(mockedEventRepository.findAll()).thenReturn(eventList);
    	when(mockedOrderRepository.findAll()).thenReturn(orderList);
    }

    void calculateProfit_shouldCalculateTheRevenueExpenseAndProfit() {
    	// TODO
    }
}