package ca.ulaval.glo4002.booking.domainObjects.trips;

import ca.ulaval.glo4002.booking.domainObjects.shuttles.Passenger;
import ca.ulaval.glo4002.booking.domainObjects.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.domainObjects.trips.types.TripType;

import java.time.LocalDate;
import java.util.List;

public abstract class Trip {

	protected LocalDate date;
	protected Shuttle shuttle;
	protected List<Passenger> passengers;
	protected TripType type;

	public LocalDate getDate() {
		return date;
	}

	public TripType getType() {
		return type;
	}
}
