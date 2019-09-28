package ca.ulaval.glo4002.booking.entities.shuttles;

import java.time.LocalDate;
import java.util.List;

import ca.ulaval.glo4002.booking.entities.trips.Arrival;
import ca.ulaval.glo4002.booking.entities.trips.Departure;

public class ShuttleManifest {
	
	private LocalDate date;
	private List<Arrival> arrivals;
	private List<Departure> departures;
	
	
	public ShuttleManifest(LocalDate date, List<Arrival> arrivals, List<Departure> departures) {
		this.date = date;
		this.arrivals = arrivals;
		this.departures = departures;
	}

	
}
