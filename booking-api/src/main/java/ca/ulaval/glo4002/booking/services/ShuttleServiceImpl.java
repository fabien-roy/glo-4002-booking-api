package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.shuttles.Passenger;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.domainobjects.trips.Trip;
import ca.ulaval.glo4002.booking.exceptions.shuttles.ShuttleFullException;
import ca.ulaval.glo4002.booking.repositories.ShuttleRepository;

public class ShuttleServiceImpl implements ShuttleService {

	
	
	@Override
	public Shuttle findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Shuttle> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	@Override
	public void reservePlace(Trip trip, Passenger passenger) {
    	if(trip.getPassengers().size() < trip.getShuttle().getShuttleCategory().getMaxCapacity()) {
    		trip.getPassengers().add(passenger);
    	
    	} else throw new ShuttleFullException();
    }
*/
}
