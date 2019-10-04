package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.entities.ShuttleEntity;
import ca.ulaval.glo4002.booking.entities.ShuttleInventoryEntity;
import ca.ulaval.glo4002.booking.exceptions.shuttles.ShuttleNotFoundException;
import ca.ulaval.glo4002.booking.parsers.ShuttleParser;
import ca.ulaval.glo4002.booking.repositories.ShuttleRepository;

import java.util.ArrayList;
import java.util.List;

public class ShuttleServiceImpl implements ShuttleService {

    private final ShuttleRepository shuttleRepository;
    private final PassengerService passengerService;
    private final ShuttleParser shuttleParser;

    public ShuttleServiceImpl(ShuttleRepository shuttleRepository, PassengerService passengerService) {
        this.shuttleRepository = shuttleRepository;
        this.passengerService = passengerService;
        this.shuttleParser = new ShuttleParser();
    }

    // TODO : TRANS : Is ShuttleService.findById(Long) necessary?
    @Override
    public Shuttle findById(Long id) {
        ShuttleEntity shuttleEntity = shuttleRepository.findById(id).orElseThrow(ShuttleNotFoundException::new);

        return shuttleParser.parseEntity(shuttleEntity);
    }

    // TODO : TRANS : ShuttleService.findAll tests
    @Override
    public Iterable<Shuttle> findAll() {
        List<Shuttle> shuttles = new ArrayList<>();

        shuttleRepository.findAll().forEach(shuttleEntity -> shuttles.add(shuttleParser.parseEntity(shuttleEntity)));

        return shuttles;
    }

    // TODO : TRANS : ShuttleService.orderArrival tests
    @Override
    public Shuttle orderArrival(ShuttleInventoryEntity inventory, Shuttle shuttle) {
        ShuttleEntity savedShuttle = order(inventory, shuttle);

        inventory.addArrivalShuttle(savedShuttle);

        return shuttleParser.parseEntity(savedShuttle);
    }

    // TODO : TRANS : ShuttleService.orderDeparture tests
    @Override
    public Shuttle orderDeparture(ShuttleInventoryEntity inventory, Shuttle shuttle) {
        ShuttleEntity savedShuttle = order(inventory, shuttle);

        inventory.addDepartureShuttle(savedShuttle);

        return shuttleParser.parseEntity(savedShuttle);
    }

    private ShuttleEntity order(ShuttleInventoryEntity inventory, Shuttle shuttle) {
        ShuttleEntity savedShuttle = shuttleParser.toEntity(shuttle);

        if (shuttle.getId() == null) {
            savedShuttle.setInventory(inventory);
            savedShuttle = shuttleRepository.save(savedShuttle);
        } else {
            savedShuttle = shuttleRepository.findById(shuttle.getId()).get();
        }

        passengerService.order(savedShuttle);

        return savedShuttle;
    }
}