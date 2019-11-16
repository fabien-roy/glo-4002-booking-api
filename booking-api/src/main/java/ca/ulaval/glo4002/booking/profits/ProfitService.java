package ca.ulaval.glo4002.booking.profits;

import ca.ulaval.glo4002.booking.events.EventRepository;
import ca.ulaval.glo4002.booking.orders.OrderRepository;
import ca.ulaval.glo4002.booking.oxygen.inventory.OxygenInventoryRepository;
import ca.ulaval.glo4002.booking.shuttles.trips.TripRepository;

import javax.inject.Inject;

public class ProfitService {

    private final OrderRepository orderRepository;
    private final OxygenInventoryRepository oxygenTankInventoryRepository;
    private final TripRepository tripRepository;
    private final EventRepository eventRepository;
    private final ProfitMapper mapper;
    
    private Profit profit;

    @Inject
    public ProfitService(OrderRepository orderRepository,
                         OxygenInventoryRepository oxygenTankInventoryRepository,
                         TripRepository tripRepository,
                         EventRepository eventRepository,
                         ProfitMapper mapper) {
        this.orderRepository = orderRepository;
        this.oxygenTankInventoryRepository = oxygenTankInventoryRepository;
        this.tripRepository = tripRepository;
        this.eventRepository = eventRepository;
        this.mapper = mapper;

        profit = new Profit();
    }
    
    public void calculateProfit() {
    	orderRepository.findAll().forEach(order -> order.updateProfit(profit));
    	tripRepository.getDepartures().forEach(trip -> trip.updateProfit(profit));
        oxygenTankInventoryRepository.findall().forEach(tank -> tank.updateProfit(profit));
    	eventRepository.findAll().forEach(event -> event.updateProfit(profit));

    	profit.calculateProfit();
    }

    public ProfitsDto get() {
        return mapper.toDto(profit);
    }
}
