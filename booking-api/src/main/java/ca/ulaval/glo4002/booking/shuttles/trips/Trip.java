package ca.ulaval.glo4002.booking.shuttles.trips;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.booking.events.EventDate;
import ca.ulaval.glo4002.booking.numbers.Number;
import ca.ulaval.glo4002.booking.shuttles.Passenger;
import ca.ulaval.glo4002.booking.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.shuttles.ShuttleCategories;

public class Trip {
	
	private EventDate date;
	private Shuttle shuttle;
	private List<Passenger> passengers;
	
	public Trip(EventDate date, Shuttle shuttle) {
		this.date = date;
		this.shuttle = shuttle;
		this.passengers = new ArrayList<>();
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
		passengers.add(passenger);
	}

	public void addPassengers(List<Passenger> passengers) {
		this.passengers.addAll(passengers);
	}

	public boolean isFull() {
		return shuttle.getMaxCapacity() == passengers.size();
	}
}
