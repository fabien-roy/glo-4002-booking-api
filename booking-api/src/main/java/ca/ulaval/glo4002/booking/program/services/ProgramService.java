package ca.ulaval.glo4002.booking.program.services;

import java.util.List;

import javax.inject.Inject;

import ca.ulaval.glo4002.booking.program.events.domain.Event;
import ca.ulaval.glo4002.booking.oxygen.inventory.services.OxygenInventoryService;
import ca.ulaval.glo4002.booking.program.events.rest.mappers.EventMapper;
import ca.ulaval.glo4002.booking.program.events.domain.EventRepository;
import ca.ulaval.glo4002.booking.program.rest.ProgramRequest;
import ca.ulaval.glo4002.booking.shuttles.trips.services.TripService;

public class ProgramService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final TripService tripService;
    private final OxygenInventoryService oxygenInventoryService;

    @Inject
    public ProgramService(EventRepository eventRepository, EventMapper eventMapper, TripService tripService, OxygenInventoryService oxygenInventoryService) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.tripService = tripService;
        this.oxygenInventoryService = oxygenInventoryService;
    }

    public void add(ProgramRequest programRequest) {
        List<Event> events = eventMapper.fromRequests(programRequest.getProgram());

        events.forEach(event -> {
            tripService.orderForArtist(event.getArtist(), event.getEventDate());
            oxygenInventoryService.orderForArtist(event.getArtist(), event.getEventDate());
        });

        eventRepository.addAll(events);
    }
} 
