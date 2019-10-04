package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.shuttles.Passenger;
import ca.ulaval.glo4002.booking.entities.PassengerEntity;
import ca.ulaval.glo4002.booking.entities.ShuttleEntity;
import ca.ulaval.glo4002.booking.repositories.PassengerRepository;

public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository repository;

    public PassengerServiceImpl(PassengerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Passenger order(ShuttleEntity shuttle) {
        PassengerEntity savedPassenger = new PassengerEntity();

        savedPassenger.setShuttle(shuttle);

        return new Passenger(repository.save(savedPassenger).getId());
    }
}