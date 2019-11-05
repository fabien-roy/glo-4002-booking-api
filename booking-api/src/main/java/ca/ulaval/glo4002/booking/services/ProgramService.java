package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.dto.events.ProgramDto;
import ca.ulaval.glo4002.booking.repositories.EventRepository;

import javax.inject.Inject;

public class ProgramService {

    private final EventRepository eventRepository;

    @Inject
    public ProgramService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void addProgram(ProgramDto programDto) {
        // TODO
    }
}
