package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domain.passes.Pass;
import ca.ulaval.glo4002.booking.domain.trip.Passenger;
import ca.ulaval.glo4002.booking.enums.PassCategories;
import ca.ulaval.glo4002.booking.enums.ShuttleCategories;
import ca.ulaval.glo4002.booking.factories.ShuttleFactory;
import ca.ulaval.glo4002.booking.repositories.TripRepository;

import javax.inject.Inject;
import java.util.List;

public class TripService {

    private TripRepository repository;
    private ShuttleFactory factory;

    @Inject
    public TripService(TripRepository repository, ShuttleFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    public void orderAll(PassCategories passCategory, List<Pass> passes) {
        ShuttleCategories shuttleCategory = factory.buildCategory(passCategory);

        //TODO: Add condition for package (departure and arrival date are not the same)
        passes.forEach(pass -> {
            Passenger passenger = new Passenger(pass.getPassNumber());

            repository.addPassengerToDepartures(passenger, shuttleCategory, pass.getEventDate().getValue());
            repository.addPassengerToArrivals(passenger, shuttleCategory, pass.getEventDate().getValue());
        });
    }
}
