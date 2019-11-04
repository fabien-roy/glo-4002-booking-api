package ca.ulaval.glo4002.booking.repositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.domain.trip.Passenger;
import ca.ulaval.glo4002.booking.domain.trip.Trip;
import ca.ulaval.glo4002.booking.enums.ShuttleCategories;
import ca.ulaval.glo4002.booking.factories.ShuttleFactory;

import javax.inject.Inject;

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
	public void addPassenger(ShuttleCategories shuttleCategory, LocalDate tripDate, Number passNumber) {
	    Trip departure = getNextAvailableDeparture(shuttleCategory, tripDate);

		Passenger passenger = new Passenger(passNumber);

		departure.addPassenger(passenger);
	}

	private Trip getNextAvailableDeparture(ShuttleCategories shuttleCategory, LocalDate tripDate) {
		Trip nextDeparture;
	    List<Trip> departuresOnDate = departures
				.stream()
				.filter(trip -> trip.getTripDate().equals(tripDate))
				.filter(trip -> trip.getShuttleCategory().equals(shuttleCategory))
				.filter(trip -> !trip.isFull())
				.collect(Collectors.toList());

		if (departuresOnDate.isEmpty()) {
			Shuttle departureShuttle = shuttleFactory.build(shuttleCategory);
			nextDeparture = new Trip(tripDate, departureShuttle);
			departures.add(nextDeparture);
		} else {
		    nextDeparture = departuresOnDate.get(0);
		}

		return nextDeparture;
	}
}
