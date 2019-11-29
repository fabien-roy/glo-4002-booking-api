package ca.ulaval.glo4002.booking.program.services;

import java.util.List;

import javax.inject.Inject;

import ca.ulaval.glo4002.booking.program.events.domain.Event;
import ca.ulaval.glo4002.booking.oxygen.inventory.services.OxygenInventoryService;
import ca.ulaval.glo4002.booking.program.events.domain.EventFactory;
import ca.ulaval.glo4002.booking.program.events.infrastructure.EventRepository;
import ca.ulaval.glo4002.booking.program.rest.ProgramDto;
import ca.ulaval.glo4002.booking.shuttles.trips.services.TripService;

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
            oxygenInventoryService.orderForArtist(event.getArtist(), event.getEventDate());
        });

        eventRepository.addAll(events);
    }
} 
