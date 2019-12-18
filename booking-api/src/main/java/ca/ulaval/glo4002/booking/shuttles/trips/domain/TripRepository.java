package ca.ulaval.glo4002.booking.shuttles.trips.domain;

import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.shuttles.domain.Passenger;
import ca.ulaval.glo4002.booking.shuttles.domain.ShuttleCategories;
import ca.ulaval.glo4002.booking.shuttles.trips.domain.Trip;

import java.util.List;

public interface TripRepository {

    List<Trip> getArrivals();
    
    List<Trip> getDepartures();

    List<Trip> getDeparturesForDate(EventDate tripDate);

    List<Trip> getArrivalsForDate(EventDate tripDate);

	void addPassengerToDepartures(Passenger passenger, ShuttleCategories category, EventDate tripDate);

    void addPassengerToArrivals(Passenger passenger, ShuttleCategories category, EventDate tripDate);

    void addPassengersToNewArrival(List<Passenger> passengers, ShuttleCategories shuttleCategory, EventDate tripDate);

    void addPassengersToNewDeparture(List<Passenger> passengers, ShuttleCategories shuttleCategory, EventDate tripDate);
}
