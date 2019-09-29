package ca.ulaval.glo4002.booking.entities.shuttles;

import ca.ulaval.glo4002.booking.entities.orders.OrderItem;
import ca.ulaval.glo4002.booking.entities.shuttles.categories.ShuttleCategory;
import ca.ulaval.glo4002.booking.entities.shuttles.types.ShuttleType;
import ca.ulaval.glo4002.booking.exceptions.shuttles.ShuttleFullException;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import java.util.List;

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
