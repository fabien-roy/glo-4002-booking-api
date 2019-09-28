package ca.ulaval.glo4002.booking.entities.trips;

import java.time.LocalDate;
import java.util.List;

import ca.ulaval.glo4002.booking.entities.shuttles.Passenger;
import ca.ulaval.glo4002.booking.entities.shuttles.Shuttle;

public abstract class Trip {

	protected LocalDate date;
	protected Shuttle shuttle;
	protected List<Passenger> passengers;
}
