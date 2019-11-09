package ca.ulaval.glo4002.booking.services;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import ca.ulaval.glo4002.booking.domain.BookingArtist;
import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.events.Event;
import ca.ulaval.glo4002.booking.dto.events.ProgramDto;
import ca.ulaval.glo4002.booking.enums.ShuttleCategories;
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
            BookingArtist artist = event.getArtist();
            List<Number> passengerNumbers = Collections.nCopies(artist.getNumberOfPeople(), artist.getId());
            ShuttleCategories shuttleCategory;

            if (artist.getNumberOfPeople() > 1) {
                shuttleCategory = ShuttleCategories.MILLENNIUM_FALCON;
            } else {
                shuttleCategory = ShuttleCategories.ET_SPACESHIP;
            }

            tripService.order(shuttleCategory, event.getEventDate(), passengerNumbers);

            // TODO : Order oxygen for artists
            // TODO : Order oxygen for activities
        });

        eventRepository.addAll(events);
    }
}
