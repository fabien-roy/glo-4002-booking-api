package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domain.events.EventDate;
import ca.ulaval.glo4002.booking.domain.passes.Pass;
import ca.ulaval.glo4002.booking.domain.shuttles.Passenger;
import ca.ulaval.glo4002.booking.enums.PassCategories;
import ca.ulaval.glo4002.booking.enums.ShuttleCategories;
import ca.ulaval.glo4002.booking.factories.ShuttleFactory;
import ca.ulaval.glo4002.booking.repositories.TripRepository;

import javax.inject.Inject;
import java.util.List;

public class TripService {

    private final TripRepository repository;
    private final ShuttleFactory factory;

    @Inject
    public TripService(TripRepository repository, ShuttleFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    void orderAll(PassCategories passCategory, List<Pass> passes) {
        ShuttleCategories shuttleCategory = factory.buildCategory(passCategory);

        passes.forEach(pass -> {
            Passenger passenger = new Passenger(pass.getPassNumber());

            if (pass.getEventDate() == null) {
                orderForFullFestival(passenger, shuttleCategory);
            } else {
                orderForEventDate(passenger, shuttleCategory, pass.getEventDate());
            }
        });
    }

    private void orderForEventDate(Passenger passenger, ShuttleCategories category, EventDate tripDate) {
        repository.addPassengerToArrivals(passenger, category, tripDate);
        repository.addPassengerToDepartures(passenger, category, tripDate);
    }

    private void orderForFullFestival(Passenger passenger, ShuttleCategories category) {
        repository.addPassengerToArrivals(passenger, category, new EventDate(EventDate.START_DATE));
        repository.addPassengerToDepartures(passenger, category, new EventDate(EventDate.END_DATE));
    }
}
