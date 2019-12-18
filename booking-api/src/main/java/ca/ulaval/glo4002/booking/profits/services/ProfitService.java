package ca.ulaval.glo4002.booking.profits.services;

import ca.ulaval.glo4002.booking.orders.domain.Order;
import ca.ulaval.glo4002.booking.orders.domain.OrderRepository;
import ca.ulaval.glo4002.booking.oxygen.domain.OxygenTank;
import ca.ulaval.glo4002.booking.oxygen.inventory.domain.OxygenInventoryRepository;
import ca.ulaval.glo4002.booking.profits.domain.Money;
import ca.ulaval.glo4002.booking.profits.domain.ProfitReport;
import ca.ulaval.glo4002.booking.profits.rest.ProfitResponse;
import ca.ulaval.glo4002.booking.profits.rest.mappers.ProfitMapper;
import ca.ulaval.glo4002.booking.program.events.domain.Event;
import ca.ulaval.glo4002.booking.program.events.domain.EventRepository;
import ca.ulaval.glo4002.booking.shuttles.trips.domain.Trip;
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

        for (Trip trip : tripRepository.getArrivals()) {
            expense = expense.add(trip.getPrice());
        }

        for (OxygenTank tank : oxygenTankInventoryRepository.findAll()) {
            expense = expense.add(tank.getPrice());
        }

        for (Event event : eventRepository.findAll()) {
            expense = expense.add(event.getPrice());
        }

        Money revenue = new Money(BigDecimal.ZERO);

        for (Order order : orderRepository.findAll()) {
            revenue = revenue.add(order.getPrice());
        }

        ProfitReport profitReport = new ProfitReport(expense, revenue);

        return mapper.toResponse(profitReport);
    }
}
