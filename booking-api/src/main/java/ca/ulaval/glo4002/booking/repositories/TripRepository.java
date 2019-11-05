package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.trip.Trip;
import ca.ulaval.glo4002.booking.enums.ShuttleCategories;

import java.time.LocalDate;
import java.util.List;

public interface TripRepository {

    List<Trip> getDepartures();

    List<Trip> getArrivals();

	void addPassenger(ShuttleCategories shuttleCategory, LocalDate eventDate, Number passNumber);

    void addPassenger(ShuttleCategories shuttleCategory, LocalDate departureDate, LocalDate arrivalDate, Number passNumber);
}
