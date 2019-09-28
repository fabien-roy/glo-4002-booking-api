package ca.ulaval.glo4002.booking.entities.trips;

import java.time.LocalDate;
import java.util.List;

import ca.ulaval.glo4002.booking.entities.shuttles.Passenger;
import ca.ulaval.glo4002.booking.entities.shuttles.categories.ShuttleCategory;

public class Departure extends Trip {
	
	public Departure(LocalDate date, List<Passenger> passengers, ShuttleCategory shuttleCategory) {
		this.date = date;
		this.passengers = passengers;
		this.shuttleCategory = shuttleCategory;
		
	}
}
