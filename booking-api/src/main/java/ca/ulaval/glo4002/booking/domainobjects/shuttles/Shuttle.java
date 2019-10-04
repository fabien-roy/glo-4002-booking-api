package ca.ulaval.glo4002.booking.domainobjects.shuttles;

import ca.ulaval.glo4002.booking.domainobjects.shuttles.categories.ShuttleCategory;
import ca.ulaval.glo4002.booking.domainobjects.trips.Trip;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Shuttle {

	private Long id;
	private Double price; // TODO : TRANS : Remove? (in category)
    protected LocalDate date;
	private ShuttleCategory category;
	private List<Trip> trips; // TODO : TRANS : Remove?
    private List<Passenger> passengers;

    public Shuttle(Long id, Double price, ShuttleCategory category, List<Trip> trips, List<Passenger> passengers) {
        this.id = id;
        this.price = price;
        this.category = category;
        this.trips = trips;
        this.passengers = passengers;
    }

    public Shuttle(Long id, Double price, ShuttleCategory category, List<Passenger> passengers) {
        this.id = id;
        this.price = price;
        this.category = category;
        this.passengers = passengers;
    }

    public Shuttle(ShuttleCategory category) {
        this.category = category;
        this.passengers = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public ShuttleCategory getShuttleCategory() {
    	return category;
    }

    public List<Trip> getTrips() {
    	return trips;
    }

    public Double getPrice() {
        return this.price;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    public boolean isFull() {
        return passengers.size() == category.getMaxCapacity();
    }

    // TODO : TRANS : Check if Shuttle.reservePlace is used
    /*
    public void reservePlace(Passenger passenger) {
    	if(passengers.size() < category.getMaxCapacity()) {
    		passengers.add(passenger);
    		
    	} else throw new ShuttleFullException();
    }
    */
}
