package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domain.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.domain.shuttles.Passenger;
import ca.ulaval.glo4002.booking.domain.shuttles.Trip;
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
	public List<Trip> getDeparturesForDate(LocalDate tripDate) {
		return getTripsForDate(departures, tripDate);
	}

	@Override
	public List<Trip> getArrivalsForDate(LocalDate tripDate) {
	    return getTripsForDate(arrivals, tripDate);
	}

	private List<Trip> getTripsForDate(List<Trip> trips, LocalDate tripDate) {
		return trips.stream().filter(trip -> trip.getTripDate().equals(tripDate)).collect(Collectors.toList());
	}
	
	public List<Trip> getAllArrivals() {
		return arrivals;
	}
	
	public List<Trip> getAllDepartures() {
		return departures;
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
		Trip trip = getNextAvailableTrip(trips, shuttleCategory, tripDate);

		trip.addPassenger(passenger);
	}

	private Trip getNextAvailableTrip(List<Trip> trips, ShuttleCategories shuttleCategory, LocalDate tripDate) {
		Trip nextTrip;
		List<Trip> departuresOnDate = getAvailableTrips(trips, shuttleCategory, tripDate);

		if (departuresOnDate.isEmpty()) {
			Shuttle departureShuttle = shuttleFactory.build(shuttleCategory);
			nextTrip = new Trip(tripDate, departureShuttle);
			trips.add(nextTrip);
		} else {
		    nextTrip = departuresOnDate.get(0);
		}

		return nextTrip;
	}

	private List<Trip> getAvailableTrips(List<Trip> trips, ShuttleCategories category, LocalDate tripDate) {
		return trips
				.stream()
				.filter(trip -> trip.getTripDate().equals(tripDate))
				.filter(trip -> trip.getShuttleCategory().equals(category))
				.filter(trip -> !trip.isFull())
				.collect(Collectors.toList());
	}
}
