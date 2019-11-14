package ca.ulaval.glo4002.booking.program;

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

        events.forEach(event -> {
            tripService.orderForArtist(event.getArtist(), event.getEventDate());

            // TODO : Order oxygen for artists
            // TODO : Order oxygen for activities
        });

        eventRepository.addAll(events);
    }
} 
