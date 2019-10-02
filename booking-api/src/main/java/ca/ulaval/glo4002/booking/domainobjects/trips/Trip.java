package ca.ulaval.glo4002.booking.domainobjects.trips;

import java.time.LocalDate;
import java.util.List;

import ca.ulaval.glo4002.booking.domainobjects.shuttles.Passenger;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.domainobjects.trips.types.TripType;

public abstract class Trip {

	protected Long id;
	protected LocalDate date;
	protected List<Passenger> passengers;
	protected TripType type;
	protected Shuttle shuttle;

	public Long getId() {
		return id;
	}

	public LocalDate getDate() {
		return date;
	}

	public TripType getType() {
		return type;
	}
	
	public Shuttle getShuttle() {
		return shuttle;
	}
	
	public List<Passenger> getPassengers() {
		return passengers;
	}
}
