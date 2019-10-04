package ca.ulaval.glo4002.booking.domainobjects.shuttles;

import java.time.LocalDate;
import java.util.List;

public class ShuttleManifest {
	
	private LocalDate tripDate;
	private List<Shuttle> departures;
	private List<Shuttle> arrivals;

	public ShuttleManifest(LocalDate date, List<Shuttle> departures, List<Shuttle> arrivals) {
		this.departures = departures;
		this.arrivals = arrivals;
		this.tripDate = date;
	}
	
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
	
	public LocalDate getDate() {
		return tripDate;
	}
}
