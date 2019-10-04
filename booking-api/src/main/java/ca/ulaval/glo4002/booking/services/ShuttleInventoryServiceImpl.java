package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.builders.shuttles.ShuttleCategoryBuilder;
import ca.ulaval.glo4002.booking.domainobjects.qualities.Quality;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.Passenger;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.ShuttleInventory;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.categories.ShuttleCategory;
import ca.ulaval.glo4002.booking.entities.ShuttleInventoryEntity;
import ca.ulaval.glo4002.booking.parsers.ShuttleInventoryParser;
import ca.ulaval.glo4002.booking.parsers.ShuttleParser;
import ca.ulaval.glo4002.booking.repositories.ShuttleInventoryRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShuttleInventoryServiceImpl implements ShuttleInventoryService {

    private final ShuttleInventoryRepository repository;
    private final ShuttleService shuttleService;
    private final ShuttleInventoryParser parser;
    private final ShuttleParser shuttleParser;

    public ShuttleInventoryServiceImpl(ShuttleInventoryRepository repository, ShuttleService shuttleService) {
        this.repository = repository;
        this.shuttleService = shuttleService;
        this.parser = new ShuttleInventoryParser();
        this.shuttleParser = new ShuttleParser();
    }

    @Override
    public ShuttleInventory get() {
        List<ShuttleInventoryEntity> inventories = new ArrayList<>();
        repository.findAll().forEach(inventories::add);

        if (inventories.isEmpty()) {
            return new ShuttleInventory();
        }

        return parser.parseEntity(inventories.get(0));
    }

    // TODO : TRANS : ShuttleInventoryService.order tests
    @Override
    public ShuttleInventory order(Quality quality, LocalDate arrivalDate, LocalDate departureDate) {
        ShuttleInventory inventory = get();
        ShuttleCategory requestedCategory = new ShuttleCategoryBuilder().buildById(quality.getId());

        ShuttleInventoryEntity savedInventory = repository.save(parser.toEntity(inventory));

        List<Shuttle> arrivalShuttles = inventory.getArrivalShuttles().stream().filter(shuttle -> shuttle.getDate().equals(arrivalDate)).collect(Collectors.toList());
        List<Shuttle> departureShuttles = inventory.getDepartureShuttles().stream().filter(shuttle -> shuttle.getDate().equals(departureDate)).collect(Collectors.toList());

        shuttleService.orderArrival(savedInventory, getNextShuttle(arrivalShuttles, requestedCategory, arrivalDate));
        shuttleService.orderDeparture(savedInventory, getNextShuttle(departureShuttles, requestedCategory, departureDate));

        savedInventory = repository.save(savedInventory);

        return parser.parseEntity(savedInventory);
    }

    private Shuttle getNextShuttle(List<Shuttle> shuttles, ShuttleCategory category, LocalDate date) {
        Shuttle dateShuttle;

        if (shuttles.isEmpty() || shuttles.get(shuttles.size() - 1).isFull()) {
            dateShuttle = new Shuttle(category, date);
            shuttles.add(dateShuttle);
        } else {
            dateShuttle = shuttles.get(shuttles.size() - 1);
            dateShuttle.addPassenger(new Passenger());
        }

        return dateShuttle;
    }
}