package ca.ulaval.glo4002.booking.entities.trips;

import java.time.LocalDate;
import java.util.List;

import ca.ulaval.glo4002.booking.entities.shuttles.Passenger;
import ca.ulaval.glo4002.booking.entities.shuttles.Shuttle;

public class Arrival extends Trip {

	public Arrival(LocalDate date, List<Passenger> passengers, Shuttle shuttle) {
		this.date = date;
		this.passengers = passengers;
		this.shuttle = shuttle;
	}
}
