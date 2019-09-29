package ca.ulaval.glo4002.booking.domainObjects.trips;

import ca.ulaval.glo4002.booking.domainObjects.shuttles.Passenger;
import ca.ulaval.glo4002.booking.domainObjects.shuttles.Shuttle;

import java.time.LocalDate;
import java.util.List;

public class Departure extends Trip {
	
	public Departure(LocalDate date, List<Passenger> passengers, Shuttle shuttle) {
		this.date = date;
		this.passengers = passengers;
		this.shuttle = shuttle;
		
	}
}
