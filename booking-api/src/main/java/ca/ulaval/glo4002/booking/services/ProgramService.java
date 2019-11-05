package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domain.events.Event;
import ca.ulaval.glo4002.booking.dto.events.ProgramDto;
import ca.ulaval.glo4002.booking.factories.EventFactory;
import ca.ulaval.glo4002.booking.repositories.EventRepository;

import javax.inject.Inject;
import java.util.List;

public class ProgramService {

    private final EventRepository eventRepository;
    private final EventFactory eventFactory;

    @Inject
    public ProgramService(EventRepository eventRepository, EventFactory eventFactory) {
        this.eventRepository = eventRepository;
        this.eventFactory = eventFactory;
    }

    public void add(ProgramDto programDto) {
        List<Event> events = eventFactory.build(programDto.getProgram());

        // TODO : Order shuttles and oxygenTanks

        eventRepository.addAll(events);
    }
}
