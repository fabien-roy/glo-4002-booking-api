package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.repositories.PassengerRepository;
import ca.ulaval.glo4002.booking.repositories.ShuttleRepository;

public class ShuttleServiceImpl implements ShuttleService {

	private final ShuttleRepository shuttleRepository;
	private final PassengerRepository passengerRepository;
	
	public ShuttleServiceImpl(ShuttleRepository shuttleRepository, PassengerRepository passengerRepository) {
		this.shuttleRepository = shuttleRepository;
		this.passengerRepository = passengerRepository;
	}
	
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