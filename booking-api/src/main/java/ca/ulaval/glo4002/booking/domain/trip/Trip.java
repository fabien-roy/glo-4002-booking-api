package ca.ulaval.glo4002.booking.domain.trip;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.enums.ShuttleCategories;
import ca.ulaval.glo4002.booking.exceptions.shuttles.ShuttleFullException;

public class Trip {
	
	private LocalDate date;
	private Shuttle shuttle;
	private List<Passenger> passengers;
	
	public Trip(LocalDate date, Shuttle shuttle) {
		this.date = date;
		this.shuttle = shuttle;
		this.passengers = new ArrayList<>();
	}

	public LocalDate getTripDate() {
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
	
	public boolean isFull() {
		return shuttle.getMaxCapacity() == passengers.size();
	}
}
