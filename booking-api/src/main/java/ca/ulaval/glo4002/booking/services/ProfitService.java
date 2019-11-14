package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.dto.ProfitsDto;
import ca.ulaval.glo4002.booking.repositories.EventRepository;
import ca.ulaval.glo4002.booking.repositories.OrderRepository;
import ca.ulaval.glo4002.booking.repositories.OxygenInventoryRepository;
import ca.ulaval.glo4002.booking.repositories.TripRepository;

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
