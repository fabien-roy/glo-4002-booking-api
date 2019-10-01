package ca.ulaval.glo4002.booking.domainObjects.shuttles;

import ca.ulaval.glo4002.booking.domainObjects.trips.ArrivalTrip;
import ca.ulaval.glo4002.booking.domainObjects.trips.DepartureTrip;

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
}
