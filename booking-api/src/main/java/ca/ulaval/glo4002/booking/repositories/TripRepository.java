package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domain.shuttles.Passenger;
import ca.ulaval.glo4002.booking.domain.shuttles.Trip;
import ca.ulaval.glo4002.booking.enums.ShuttleCategories;

import java.time.LocalDate;
import java.util.List;

public interface TripRepository {

    List<Trip> getDeparturesForDate(LocalDate tripDate);

    List<Trip> getArrivalsForDate(LocalDate tripDate);
    
    List<Trip> getAllArrivals();
    
    List<Trip> getAllDepartures();

	void addPassengerToDepartures(Passenger passenger, ShuttleCategories category, LocalDate tripDate);

    void addPassengerToArrivals(Passenger passenger, ShuttleCategories category, LocalDate tripDate);
}
