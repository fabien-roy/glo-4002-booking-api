package ca.ulaval.glo4002.booking.services;

import java.util.List;

import javax.inject.Inject;

import ca.ulaval.glo4002.booking.domain.events.Event;
import ca.ulaval.glo4002.booking.dto.events.ProgramDto;
import ca.ulaval.glo4002.booking.factories.EventFactory;
import ca.ulaval.glo4002.booking.repositories.EventRepository;

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
