package ca.ulaval.glo4002.booking.domain.trip;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.booking.domain.EventDate;
import ca.ulaval.glo4002.booking.domain.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.enums.ShuttleCategories;

public class Trip {
	
	private EventDate date;
	private Shuttle shuttle;
	private List<Passenger> passengers;

	public Trip(EventDate date, Shuttle shuttle, List<Passenger> passengers) {
		this.date = date;
		this.shuttle = shuttle;
		this.passengers = passengers;
	}
	
	public EventDate getTripDate() {
		return date;
	}
	
	public ShuttleCategories getShuttleCategory() {
		return shuttle.getCategory();
	}
	
	public List<Number> getPassengersPassNumbers() {
		List<Number> passNumbers = new ArrayList<>();
		
		passengers.stream().map(Passenger::getPassNumber).forEach(passNumbers::add);
		
		return passNumbers;
	}
	
	public void addPassenger(Passenger passenger) {
		if (!shuttleIsFull()) {
			passengers.add(passenger);
		} else {
			//TODO what do we do else
		}
	}
	
	private boolean shuttleIsFull() {
		return shuttle.getMaxCapacity() > passengers.size();
	}
}
