package ca.ulaval.glo4002.booking.domainObjects.shuttles;
import java.util.List;

import ca.ulaval.glo4002.booking.domainObjects.orders.OrderItem;
import ca.ulaval.glo4002.booking.domainObjects.shuttles.categories.ShuttleCategory;
import ca.ulaval.glo4002.booking.domainObjects.trips.Trip;

import java.util.List;

public class Shuttle extends OrderItem {

	protected Long id;
	private Double price;
	private ShuttleCategory category;
	private List<Trip> trips;
    // TODO : private <?> date;

    public Shuttle(Long id, Double price, ShuttleCategory category, List<Trip> trip) {

    }

    @Override
    public Double getPrice() {
        return price;
    }

    /*
    public void reservePlace(Passenger passenger) {
    	if(passengers.size() < category.getMaxCapacity()) {
    		passengers.add(passenger);
    		
    	} else throw new ShuttleFullException();
    }
    */
}
