package ca.ulaval.glo4002.booking.domain.shuttles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.booking.domain.EventDate;
import ca.ulaval.glo4002.booking.domain.trip.Trip;

public class ShuttleManifest {
	
	private List<Trip> departures = new ArrayList<>();
	private List<Trip> arrivals = new ArrayList<>();
	
	public ShuttleManifest(List<Shuttle> shuttles) {
		for (Shuttle shuttle : shuttles) {
			arrivals.addAll(shuttle.getArrivals());
			departures.addAll(shuttle.getDepartures());
		}
	}
	
	public ShuttleManifest(LocalDate date, List<Shuttle> shuttles) {
		for (Shuttle shuttle : shuttles) {
			arrivals.addAll(shuttle.getArrivalsByDate(date));
			departures.addAll(shuttle.getDeparturesByDate(date));
		}
	}

	public List<Trip> getDepartures() {
		return departures;
	}

	public List<Trip> getArrivals() {
		return arrivals;
	}
}
