package ca.ulaval.glo4002.booking.domainObjects.trips;

import ca.ulaval.glo4002.booking.domainObjects.shuttles.Passenger;
import ca.ulaval.glo4002.booking.domainObjects.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.domainObjects.trips.types.DepartureTripType;

import java.time.LocalDate;
import java.util.List;

public class DepartureTrip extends Trip {
	
	public DepartureTrip(LocalDate date, List<Passenger> passengers, Shuttle shuttle) {
		this.date = date;
		this.passengers = passengers;
		this.shuttle = shuttle;
		this.type = new DepartureTripType();
	}
}
