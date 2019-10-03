package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.entities.ShuttleEntity;
import ca.ulaval.glo4002.booking.exceptions.shuttles.ShuttleNotFoundException;
import ca.ulaval.glo4002.booking.parsers.ShuttleParser;
import ca.ulaval.glo4002.booking.repositories.PassengerRepository;
import ca.ulaval.glo4002.booking.repositories.ShuttleRepository;

import java.util.ArrayList;
import java.util.List;

public class ShuttleServiceImpl implements ShuttleService {

    private final ShuttleRepository shuttleRepository;
    private final PassengerRepository passengerRepository;
    private final ShuttleParser shuttleParser;

    public ShuttleServiceImpl(ShuttleRepository shuttleRepository, PassengerRepository passengerRepository) {
        this.shuttleRepository = shuttleRepository;
        this.passengerRepository = passengerRepository;
        this.shuttleParser = new ShuttleParser();
    }

    @Override
    public Shuttle findById(Long id) {
        ShuttleEntity shuttleEntity = shuttleRepository.findById(id)
                .orElseThrow(() -> new ShuttleNotFoundException());

        return shuttleParser.parseEntity(shuttleEntity);
    }

    @Override
    public Iterable<Shuttle> findAll() {
        List<Shuttle> shuttles = new ArrayList<>();

        shuttleRepository.findAll().forEach(shuttleEntity -> shuttles.add(shuttleParser.parseEntity(shuttleEntity)));

        return shuttles;
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