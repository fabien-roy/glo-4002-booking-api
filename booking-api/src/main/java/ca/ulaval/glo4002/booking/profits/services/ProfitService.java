package ca.ulaval.glo4002.booking.profits.services;

import ca.ulaval.glo4002.booking.orders.domain.OrderRepository;
import ca.ulaval.glo4002.booking.oxygen.inventory.domain.OxygenInventoryRepository;
import ca.ulaval.glo4002.booking.profits.domain.Money;
import ca.ulaval.glo4002.booking.profits.domain.ProfitReport;
import ca.ulaval.glo4002.booking.profits.rest.ProfitResponse;
import ca.ulaval.glo4002.booking.profits.rest.mappers.ProfitMapper;
import ca.ulaval.glo4002.booking.program.events.domain.EventRepository;
import ca.ulaval.glo4002.booking.shuttles.trips.domain.TripRepository;

import javax.inject.Inject;
import java.math.BigDecimal;

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
        Money expense = new Money(BigDecimal.ZERO);
        tripRepository.getArrivals().forEach(trip -> expense.add(trip.getPrice()));
        oxygenTankInventoryRepository.findAll().forEach(tank -> expense.add(tank.getPrice()));
        eventRepository.findAll().forEach(event -> expense.add(event.getPrice()));

        Money revenue = new Money(BigDecimal.ZERO);
        orderRepository.findAll().forEach(order -> revenue.add(order.getPrice()));

        ProfitReport profitReport = new ProfitReport(expense, revenue);

        return mapper.toResponse(profitReport);
    }
}
