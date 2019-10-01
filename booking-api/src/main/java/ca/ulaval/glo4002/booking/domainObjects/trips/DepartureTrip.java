package ca.ulaval.glo4002.booking.domainObjects.trips;

import ca.ulaval.glo4002.booking.domainObjects.shuttles.Passenger;
import ca.ulaval.glo4002.booking.domainObjects.trips.types.DepartureTripType;

import java.time.LocalDate;
import java.util.List;

public class DepartureTrip extends Trip {

	public DepartureTrip(LocalDate date, List<Passenger> passengers) {
		this.date = date;
		this.passengers = passengers;
		this.type = new DepartureTripType();
	}

	public DepartureTrip(Long id, LocalDate date, List<Passenger> passengers) {
		this.id = id;
		this.date = date;
		this.passengers = passengers;
		this.type = new DepartureTripType();
	}
}
