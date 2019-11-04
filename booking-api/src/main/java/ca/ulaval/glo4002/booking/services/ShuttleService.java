package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domain.EventDate;
import ca.ulaval.glo4002.booking.enums.PassCategories;
import ca.ulaval.glo4002.booking.enums.ShuttleCategories;
import ca.ulaval.glo4002.booking.factories.ShuttleFactory;
import ca.ulaval.glo4002.booking.repositories.TripRepository;

import javax.inject.Inject;
import java.util.List;

public class ShuttleService {

    TripRepository repository;
    ShuttleFactory factory;

    @Inject
    public ShuttleService(TripRepository repository, ShuttleFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    public void orderAll(PassCategories passCategory, List<EventDate> eventDates) {
        ShuttleCategories shuttleCategory = factory.buildCategory(passCategory);

        eventDates.forEach(eventDate -> repository.addPassenger(shuttleCategory, eventDate));
    }
}
