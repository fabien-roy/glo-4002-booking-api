package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domain.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.domain.trip.Passenger;
import ca.ulaval.glo4002.booking.domain.trip.Trip;
import ca.ulaval.glo4002.booking.enums.ShuttleCategories;
import ca.ulaval.glo4002.booking.factories.ShuttleFactory;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryTripRepository implements TripRepository {
	
	private List<Trip> departures;
	private List<Trip> arrivals;
	private final ShuttleFactory shuttleFactory;

	@Inject
	public InMemoryTripRepository(ShuttleFactory shuttleFactory) {
		this.departures = new ArrayList<>();
		this.arrivals = new ArrayList<>();
		this.shuttleFactory = shuttleFactory;
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
	public void addPassengerToDepartures(Passenger passenger, ShuttleCategories category, LocalDate tripDate) {
		addPassenger(passenger, departures, category, tripDate);
	}

	@Override
	public void addPassengerToArrivals(Passenger passenger, ShuttleCategories category, LocalDate tripDate) {
		addPassenger(passenger, arrivals, category, tripDate);
	}

	private void addPassenger(Passenger passenger, List<Trip> trips, ShuttleCategories shuttleCategory, LocalDate tripDate) {
		Trip trip = getNextAvailableTrip(shuttleCategory, tripDate, trips);

		trip.addPassenger(passenger);
	}

	private Trip getNextAvailableTrip(ShuttleCategories shuttleCategory, LocalDate tripDate, List<Trip> tripList) {
		Trip nextTrip;
		List<Trip> departuresOnDate = tripList
				.stream()
				.filter(trip -> trip.getTripDate().equals(tripDate))
				.filter(trip -> trip.getShuttleCategory().equals(shuttleCategory))
				.filter(trip -> !trip.isFull())
				.collect(Collectors.toList());

		if (departuresOnDate.isEmpty()) {
			Shuttle departureShuttle = shuttleFactory.build(shuttleCategory);
			nextTrip = new Trip(tripDate, departureShuttle);
			tripList.add(nextTrip);
		} else {
		    nextTrip = departuresOnDate.get(0);
		}

		return nextTrip;
	}
}
