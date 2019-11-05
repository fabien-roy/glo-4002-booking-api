package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.dto.events.ArtistListDto;
import ca.ulaval.glo4002.booking.repositories.EventRepository;

import javax.inject.Inject;

public class ArtistService {

    private final EventRepository eventRepository;

    @Inject
    public ArtistService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public ArtistListDto getAll(String orderBy) {
        // TODO

        return null;
    }
}
