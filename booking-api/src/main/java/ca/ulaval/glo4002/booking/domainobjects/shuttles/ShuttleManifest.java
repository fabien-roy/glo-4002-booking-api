package ca.ulaval.glo4002.booking.domainobjects.shuttles;

import ca.ulaval.glo4002.booking.domainobjects.trips.ArrivalTrip;
import ca.ulaval.glo4002.booking.domainobjects.trips.DepartureTrip;

import java.time.LocalDate;
import java.util.List;

public class ShuttleManifest {
	
	private LocalDate tripDate;
	private List<DepartureTrip> departures;
	private List<ArrivalTrip> arrivals;

	public ShuttleManifest(LocalDate date, List<DepartureTrip> departures, List<ArrivalTrip> arrivals) {
		this.departures = departures;
		this.arrivals = arrivals;
		this.tripDate = date;
	}
	
	public ShuttleManifest(List<DepartureTrip> departures, List<ArrivalTrip> arrivals) {
		this.departures = departures;
		this.arrivals = arrivals;
	}

	public List<DepartureTrip> getDepartures() {
		return departures;
	}

	public List<ArrivalTrip> getArrivals() {
		return arrivals;
	}
	
	public LocalDate getDate() {
		return tripDate;
	}
}
