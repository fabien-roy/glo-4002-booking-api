package ca.ulaval.glo4002.booking.domainObjects.shuttles;
import java.util.List;

import javax.persistence.Entity;

import org.springframework.data.annotation.Id;

import ca.ulaval.glo4002.booking.domainObjects.orders.OrderItem;
import ca.ulaval.glo4002.booking.domainObjects.shuttles.categories.ShuttleCategory;
import ca.ulaval.glo4002.booking.domainObjects.shuttles.types.ShuttleType;
import ca.ulaval.glo4002.booking.exceptions.shuttles.ShuttleFullException;

@Entity
public class Shuttle extends OrderItem {
	
	@Id
	protected Long id;

	private Double price;
	private ShuttleCategory category;
    private ShuttleType type;
	private List<Passenger> passengers;
    // TODO : private <?> date;

    @Override
    public Double getPrice() {
        return price;
    }
    
    public void reservePlace(Passenger passenger) {
    	if(passengers.size() < category.getMaxCapacity()) {
    		passengers.add(passenger);
    		
    	} else throw new ShuttleFullException();
    }
}
