package ca.ulaval.glo4002.booking.services;

import org.junit.jupiter.api.BeforeEach;

import ca.ulaval.glo4002.booking.domain.shuttles.Trip;
import ca.ulaval.glo4002.booking.domain.events.Event;
import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.domain.orders.Order;
import ca.ulaval.glo4002.booking.repositories.EventRepository;
import ca.ulaval.glo4002.booking.repositories.OrderRepository;
import ca.ulaval.glo4002.booking.repositories.TripRepository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

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