package ca.ulaval.glo4002.booking.profits.services;

import ca.ulaval.glo4002.booking.profits.domain.ProfitReport;
import ca.ulaval.glo4002.booking.profits.rest.mappers.ProfitMapper;
import ca.ulaval.glo4002.booking.profits.rest.ProfitResponse;
import ca.ulaval.glo4002.booking.program.events.domain.EventRepository;
import ca.ulaval.glo4002.booking.orders.domain.OrderRepository;
import ca.ulaval.glo4002.booking.oxygen.inventory.domain.OxygenInventoryRepository;
import ca.ulaval.glo4002.booking.shuttles.trips.domain.TripRepository;

import javax.inject.Inject;

public class ProfitService {

    private final OrderRepository orderRepository;
    private final OxygenInventoryRepository oxygenTankInventoryRepository;
    private final TripRepository tripRepository;
    private final EventRepository eventRepository;
    private final ProfitMapper mapper;
    
    @Inject
    public ProfitService(OrderRepository orderRepository, OxygenInventoryRepository oxygenTankInventoryRepository, TripRepository tripRepository, EventRepository eventRepository, ProfitMapper mapper) {
        this.orderRepository = orderRepository;
        this.oxygenTankInventoryRepository = oxygenTankInventoryRepository;
        this.tripRepository = tripRepository;
        this.eventRepository = eventRepository;
        this.mapper = mapper;
    }

    public ProfitResponse getReport() {
        ProfitReport profitReport = new ProfitReport();

        orderRepository.findAll().forEach(order -> order.updateProfit(profitReport));
        tripRepository.getDepartures().forEach(trip -> trip.updateProfit(profitReport));
        oxygenTankInventoryRepository.findAll().forEach(tank -> tank.updateProfit(profitReport));
        eventRepository.findAll().forEach(event -> event.updateProfit(profitReport));

        return mapper.toResponse(profitReport);
    }
}
