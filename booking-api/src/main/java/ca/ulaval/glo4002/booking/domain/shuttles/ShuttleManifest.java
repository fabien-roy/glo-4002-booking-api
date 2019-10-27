package ca.ulaval.glo4002.booking.domain.shuttles;

import java.util.List;

import ca.ulaval.glo4002.booking.domain.EventDate;
import ca.ulaval.glo4002.booking.domain.trip.Trip;

public class ShuttleManifest {
	
	public List<Trip> departures;
	public List<Trip> arrivals;
	
	public ShuttleManifest(EventDate date) {
		// TODO : implement a way to get arrivals and departures of this date
		//this.arrivals = getTripsByDate
		// this.departures = getTripsByDate
	}

	public List<Trip> getDepartures() {
		return departures;
	}

	public List<Trip> getArrivals() {
		return arrivals;
	}
	
	

}
