package ca.ulaval.glo4002.booking.profits;

import ca.ulaval.glo4002.booking.events.EventRepository;
import ca.ulaval.glo4002.booking.orders.OrderRepository;
import ca.ulaval.glo4002.booking.oxygen.inventory.OxygenInventoryRepository;
import ca.ulaval.glo4002.booking.shuttles.trips.TripRepository;

import javax.inject.Inject;

public class ProfitService {

    private final OrderRepository orderRepository;
    private final OxygenInventoryRepository oxygenInventoryRepository;
    private final TripRepository tripRepository;
    private final EventRepository eventRepository;

    @Inject
    public ProfitService(OrderRepository orderRepository, OxygenInventoryRepository oxygenInventoryRepository, TripRepository tripRepository, EventRepository eventRepository) {
        this.orderRepository = orderRepository;
        this.oxygenInventoryRepository = oxygenInventoryRepository;
        this.tripRepository = tripRepository;
        this.eventRepository = eventRepository;
    }

    public ProfitsDto get() {
        // TODO

        return null;
    }
}
