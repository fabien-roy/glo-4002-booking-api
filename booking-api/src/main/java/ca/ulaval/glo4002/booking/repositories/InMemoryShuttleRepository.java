package ca.ulaval.glo4002.booking.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ca.ulaval.glo4002.booking.domain.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.exceptions.shuttles.ShuttleAlreadyCreatedException;
import ca.ulaval.glo4002.booking.exceptions.shuttles.ShuttleNotFoundException;

public class InMemoryShuttleRepository implements ShuttleRepository {
	
	private List<Shuttle> shuttles;
	
	public InMemoryShuttleRepository() {
		shuttles = new ArrayList<>();
	}
	
	@Override
	public Optional<Shuttle> findByShuttleNumber(Number shuttleNumber) {
        Optional<Shuttle> foundShuttle = shuttles.
        		stream().
        		filter(shuttle -> shuttle.getShuttleNumber().equals(shuttleNumber))
        		.findAny();
        
        if(!foundShuttle.isPresent()) {
        	throw new ShuttleNotFoundException(shuttleNumber.toString());        
        }
        return foundShuttle;
	}

	public void addShuttle(Shuttle shuttle) {
		if(shuttles.contains(shuttle)) {
			throw new ShuttleAlreadyCreatedException(shuttle.getShuttleNumber().toString());
		} 
		
		shuttles.add(shuttle);
	}

}
