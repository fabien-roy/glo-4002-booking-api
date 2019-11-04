package ca.ulaval.glo4002.booking.repositories;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.booking.domain.EventDate;
import ca.ulaval.glo4002.booking.domain.trip.Trip;
import ca.ulaval.glo4002.booking.enums.ShuttleCategories;

public class InMemoryTripRepository implements TripRepository {
	
	private List<Trip> departureTrips;
	private List<Trip> arrivalTrips;

	public InMemoryTripRepository() {
		departureTrips = new ArrayList<>();
		arrivalTrips = new ArrayList<>();
	}

	@Override
	public void addPassenger(ShuttleCategories shuttleCategory, EventDate eventDate) {
		// TODO : addPassenger
	}
}
