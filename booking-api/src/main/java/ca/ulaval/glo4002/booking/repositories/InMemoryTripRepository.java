package ca.ulaval.glo4002.booking.repositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.trip.Trip;
import ca.ulaval.glo4002.booking.enums.ShuttleCategories;

public class InMemoryTripRepository implements TripRepository {
	
	private List<Trip> departures;
	private List<Trip> arrivals;

	public InMemoryTripRepository() {
		departures = new ArrayList<>();
		arrivals = new ArrayList<>();
	}

	@Override
	public List<Trip> getDepartures() {
	    return departures;
	}

	@Override
	public List<Trip> getArrivals() {
	    return arrivals;
	}

	@Override
	public void addPassenger(ShuttleCategories shuttleCategory, LocalDate eventDate, Number passNumber) {
		// TODO : addPassenger
	}
}
