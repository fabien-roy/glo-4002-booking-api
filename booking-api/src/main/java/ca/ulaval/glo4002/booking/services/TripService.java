package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domain.BookingArtist;
import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.events.EventDate;
import ca.ulaval.glo4002.booking.domain.passes.Pass;
import ca.ulaval.glo4002.booking.domain.shuttles.Passenger;
import ca.ulaval.glo4002.booking.enums.PassCategories;
import ca.ulaval.glo4002.booking.enums.ShuttleCategories;
import ca.ulaval.glo4002.booking.factories.ShuttleFactory;
import ca.ulaval.glo4002.booking.repositories.TripRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TripService {

    private final TripRepository repository;
    private final ShuttleFactory factory;

    @Inject
    public TripService(TripRepository repository, ShuttleFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    public void orderForArtist(BookingArtist artist, EventDate tripDate) {
        ShuttleCategories shuttleCategory;

        if (artist.getNumberOfPeople() > 1) {
            shuttleCategory = ShuttleCategories.MILLENNIUM_FALCON;
        } else {
            shuttleCategory = ShuttleCategories.ET_SPACESHIP;
        }

        List<Passenger> passengers = Collections.nCopies(artist.getNumberOfPeople(), new Passenger(artist.getId()));

        repository.addPassengersToNewArrival(passengers, shuttleCategory, tripDate);
        repository.addPassengersToNewDeparture(passengers, shuttleCategory, tripDate);
    }

    void orderForPasses(PassCategories passCategory, List<Pass> passes) {
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
