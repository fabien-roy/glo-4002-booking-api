package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domain.Profit;
import ca.ulaval.glo4002.booking.dto.ProfitsDto;
import ca.ulaval.glo4002.booking.repositories.EventRepository;
import ca.ulaval.glo4002.booking.repositories.OrderRepository;
import ca.ulaval.glo4002.booking.repositories.OxygenTankInventoryRepository;
import ca.ulaval.glo4002.booking.repositories.TripRepository;

import javax.inject.Inject;

public class ProfitService {

    private final OrderRepository orderRepository;
    private final OxygenTankInventoryRepository oxygenTankInventoryRepository;
    private final TripRepository tripRepository;
    private final EventRepository eventRepository;
    
    Profit profit;

    @Inject
    public ProfitService(OrderRepository orderRepository, OxygenTankInventoryRepository oxygenTankInventoryRepository, TripRepository tripRepository, EventRepository eventRepository) {
        this.orderRepository = orderRepository;
        this.oxygenTankInventoryRepository = oxygenTankInventoryRepository;
        this.tripRepository = tripRepository;
        this.eventRepository = eventRepository;
        profit = new Profit();
    }
    
    public void calculateProfit() {
    	orderRepository.findAll().forEach(order -> order.updateProfit(profit));
    	tripRepository.getDepartures().forEach(trip -> trip.updateProfit(profit));
    	eventRepository.findAll().forEach(event -> event.updateProfit(profit));
    	// TODO : OXY
    	profit.calculateProfit();
    }

    public ProfitsDto get() {
        // TODO

        return null;
    }
}
