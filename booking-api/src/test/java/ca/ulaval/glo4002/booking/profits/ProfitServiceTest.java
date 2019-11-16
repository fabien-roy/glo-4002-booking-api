package ca.ulaval.glo4002.booking.profits;

import ca.ulaval.glo4002.booking.events.Event;
import ca.ulaval.glo4002.booking.events.EventRepository;
import ca.ulaval.glo4002.booking.orders.Order;
import ca.ulaval.glo4002.booking.orders.OrderRepository;
import ca.ulaval.glo4002.booking.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.oxygen.inventory.OxygenInventoryRepository;
import ca.ulaval.glo4002.booking.shuttles.trips.Trip;
import ca.ulaval.glo4002.booking.shuttles.trips.TripRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProfitServiceTest {

	private List<Trip> tripList;
	private List<Event> eventList;
	private List<Order> orderList;
	private List<OxygenTank> tanksList;

	private static final Integer NUMBER_OF_TRIP = 10;
	private static final Integer NUMBER_OF_EVENT = 8;
	private static final Integer NUMBER_OF_ORDER = 200;
	private static final Integer NUMBER_OF_TANK = 50;

	ProfitService profitService;

    @BeforeEach
    void setupProfitService() {
    	ProfitMapper mapper = new ProfitMapper();
    	Trip mockedTrip = mock(Trip.class);
    	Event mockedEvent = mock(Event.class);
    	Order mockedOrder = mock(Order.class);
    	OxygenTank mockedTank = mock(OxygenTank.class);
    	
    	tripList = Collections.nCopies(NUMBER_OF_TRIP, mockedTrip);
    	eventList = Collections.nCopies(NUMBER_OF_EVENT, mockedEvent);
    	orderList = Collections.nCopies(NUMBER_OF_ORDER, mockedOrder);
    	tanksList = Collections.nCopies(NUMBER_OF_TANK, mockedTank);
    	
    	TripRepository mockedTripRepository = mock(TripRepository.class);
    	OrderRepository mockedOrderRepository = mock(OrderRepository.class);
    	EventRepository mockedEventRepository = mock(EventRepository.class);
		OxygenInventoryRepository mockedInventoryRepository = mock(OxygenInventoryRepository.class);
    	
    	when(mockedTripRepository.getDepartures()).thenReturn(tripList);
    	when(mockedEventRepository.findAll()).thenReturn(eventList);
    	when(mockedOrderRepository.findAll()).thenReturn(orderList);
    	when(mockedInventoryRepository.findall()).thenReturn(tanksList);

    	profitService = new ProfitService(mockedOrderRepository, mockedInventoryRepository, mockedTripRepository, mockedEventRepository, mapper);
    }

    @Test
    void calculateProfit_shouldUpdateProfitValueForEachObjectThatHaveAPrice() {
		profitService.calculateProfit();

		tripList.forEach(trip -> verify(trip, times(NUMBER_OF_TRIP)).updateProfit(any()));
		eventList.forEach(event -> verify(event, times(NUMBER_OF_EVENT)).updateProfit(any()));
		orderList.forEach(order -> verify(order, times(NUMBER_OF_ORDER)).updateProfit(any()));
		tanksList.forEach(tank -> verify(tank, times(NUMBER_OF_TANK)).updateProfit(any()));
    }
}