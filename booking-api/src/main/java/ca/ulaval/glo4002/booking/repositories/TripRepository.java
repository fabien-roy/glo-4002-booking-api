package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domain.EventDate;
import ca.ulaval.glo4002.booking.domain.shuttles.Passenger;
import ca.ulaval.glo4002.booking.domain.shuttles.Trip;
import ca.ulaval.glo4002.booking.enums.ShuttleCategories;

import java.util.List;

public interface TripRepository {

    List<Trip> getArrivals();
    
    List<Trip> getDepartures();

    List<Trip> getDeparturesForDate(EventDate tripDate);

    List<Trip> getArrivalsForDate(EventDate tripDate);

	void addPassengerToDepartures(Passenger passenger, ShuttleCategories category, EventDate tripDate);

    void addPassengerToArrivals(Passenger passenger, ShuttleCategories category, EventDate tripDate);
}
