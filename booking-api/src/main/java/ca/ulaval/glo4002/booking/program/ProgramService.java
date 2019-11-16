package ca.ulaval.glo4002.booking.program;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

import ca.ulaval.glo4002.booking.events.Event;
import ca.ulaval.glo4002.booking.oxygen.inventory.OxygenInventoryService;
import ca.ulaval.glo4002.booking.events.EventFactory;
import ca.ulaval.glo4002.booking.events.EventRepository;
import ca.ulaval.glo4002.booking.shuttles.trips.TripService;

public class ProgramService {

    private final EventRepository eventRepository;
    private final EventFactory eventFactory;
    private final TripService tripService;
    private final OxygenInventoryService oxygenInventoryService;

    @Inject
    public ProgramService(EventRepository eventRepository, EventFactory eventFactory, TripService tripService, OxygenInventoryService oxygenInventoryService) {
        this.eventRepository = eventRepository;
        this.eventFactory = eventFactory;
        this.tripService = tripService;
        this.oxygenInventoryService = oxygenInventoryService;
    }

    public void add(ProgramDto programDto) {
        List<Event> events = eventFactory.build(programDto.getProgram());
        LocalDate orderDate = LocalDate.now();

        events.forEach(event -> {
            tripService.orderForArtist(event.getArtist(), event.getEventDate());

            oxygenInventoryService.orderForArtist(event.getArtist(), orderDate);
            oxygenInventoryService.orderForActivity(); // TODO : Send correct values to oxygenTankInventoryService.orderForActivity()
        });

        eventRepository.addAll(events);
    }
} 
