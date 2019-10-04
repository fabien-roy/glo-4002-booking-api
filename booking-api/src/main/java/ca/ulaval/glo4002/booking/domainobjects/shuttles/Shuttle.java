package ca.ulaval.glo4002.booking.domainobjects.shuttles;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.categories.ShuttleCategory;
import ca.ulaval.glo4002.booking.domainobjects.trips.Trip;

import java.util.List;

public class Shuttle {

	private Long id;
	private Double price;
	private ShuttleCategory category;
	private List<Trip> trips;

    public Shuttle(Long id, Double price, ShuttleCategory category, List<Trip> trips) {
        this.id = id;
        this.price = price;
        this.category = category;
        this.trips = trips;
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

    // TODO : TRANS : Check if Shuttle.reservePlace is used
    /*
    public void reservePlace(Passenger passenger) {
    	if(passengers.size() < category.getMaxCapacity()) {
    		passengers.add(passenger);
    		
    	} else throw new ShuttleFullException();
    }
    */
}
