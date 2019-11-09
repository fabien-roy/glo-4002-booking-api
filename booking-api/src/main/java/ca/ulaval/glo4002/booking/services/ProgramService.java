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
    private final OxygenTankInventoryService oxygenTankInventoryService;

    @Inject
    public ProgramService(EventRepository eventRepository, EventFactory eventFactory, TripService tripService, OxygenTankInventoryService oxygenTankInventoryService) {
        this.eventRepository = eventRepository;
        this.eventFactory = eventFactory;
        this.tripService = tripService;
        this.oxygenTankInventoryService = oxygenTankInventoryService;
    }

    public void add(ProgramDto programDto) {
        List<Event> events = eventFactory.build(programDto.getProgram());

        events.forEach(event -> {
            // TODO : Order shuttles and oxygenTanks
        });

        eventRepository.addAll(events);
    }
}
