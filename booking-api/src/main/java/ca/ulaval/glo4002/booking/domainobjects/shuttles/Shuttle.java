package ca.ulaval.glo4002.booking.domainobjects.shuttles;

import ca.ulaval.glo4002.booking.domainobjects.shuttles.categories.ShuttleCategory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Shuttle {

	private Long id;
    protected LocalDate date;
	private ShuttleCategory category;
    private List<Passenger> passengers;

    public Shuttle(Long id, ShuttleCategory category, LocalDate date, List<Passenger> passengers) {
        this.id = id;
        this.category = category;
        this.date = date;
        this.passengers = passengers;
    }

    public Shuttle(ShuttleCategory category, LocalDate date) {
        this.category = category;
        this.date = date;
        this.passengers = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public ShuttleCategory getShuttleCategory() {
    	return category;
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
}
