package ca.ulaval.glo4002.booking.domainobjects.shuttles;

import java.util.List;

public class ShuttleManifest {
	
	private List<Shuttle> departures;
	private List<Shuttle> arrivals;

	public ShuttleManifest(List<Shuttle> departures, List<Shuttle> arrivals) {
		this.departures = departures;
		this.arrivals = arrivals;
	}

	public List<Shuttle> getDepartures() {
		return departures;
	}

	public List<Shuttle> getArrivals() {
		return arrivals;
	}
}
