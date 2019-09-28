package ca.ulaval.glo4002.booking.entities.trips;

import java.time.LocalDate;
import java.util.List;

import ca.ulaval.glo4002.booking.entities.shuttles.Passenger;
import ca.ulaval.glo4002.booking.entities.shuttles.categories.ShuttleCategory;

public class Arrival extends Trip {

	public Arrival(LocalDate date, List<Passenger> passengers, ShuttleCategory shuttleName) {
		this.date = date;
		this.passengers = passengers;
		this.shuttleName = shuttleName;
	}
	
	

}
