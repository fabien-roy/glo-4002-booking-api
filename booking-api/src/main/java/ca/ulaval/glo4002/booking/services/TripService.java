package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domain.EventDate;
import ca.ulaval.glo4002.booking.domain.passes.Pass;
import ca.ulaval.glo4002.booking.domain.shuttles.Passenger;
import ca.ulaval.glo4002.booking.enums.PassCategories;
import ca.ulaval.glo4002.booking.enums.ShuttleCategories;
import ca.ulaval.glo4002.booking.factories.ShuttleFactory;
import ca.ulaval.glo4002.booking.repositories.TripRepository;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

public class TripService {

    private final TripRepository repository;
    private final ShuttleFactory factory;

    @Inject
    public TripService(TripRepository repository, ShuttleFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    public void orderAll(PassCategories passCategory, List<Pass> passes) {
        ShuttleCategories shuttleCategory = factory.buildCategory(passCategory);

        passes.forEach(pass -> {
            Passenger passenger = new Passenger(pass.getPassNumber());

            if (pass.getEventDate() == null) {
                orderForFullFestival(passenger, shuttleCategory);
            } else {
                orderForEventDate(passenger, shuttleCategory, pass.getEventDate().getValue());
            }
        });
    }

    private void orderForEventDate(Passenger passenger, ShuttleCategories category, LocalDate tripDate) {
        repository.addPassengerToArrivals(passenger, category, tripDate);
        repository.addPassengerToDepartures(passenger, category, tripDate);
    }

    private void orderForFullFestival(Passenger passenger, ShuttleCategories category) {
        repository.addPassengerToArrivals(passenger, category, EventDate.START_DATE);
        repository.addPassengerToDepartures(passenger, category, EventDate.END_DATE);
    }
}
