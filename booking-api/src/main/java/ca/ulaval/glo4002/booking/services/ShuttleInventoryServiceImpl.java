package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.builders.shuttles.ShuttleCategoryBuilder;
import ca.ulaval.glo4002.booking.domainobjects.qualities.Quality;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.Passenger;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.ShuttleInventory;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.categories.ShuttleCategory;
import ca.ulaval.glo4002.booking.entities.ShuttleInventoryEntity;
import ca.ulaval.glo4002.booking.parsers.ShuttleInventoryParser;
import ca.ulaval.glo4002.booking.repositories.ShuttleInventoryRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ShuttleInventoryServiceImpl implements ShuttleInventoryService {

    private final ShuttleInventoryRepository repository;
    private final ShuttleInventoryItemService itemService;
    private final ShuttleInventoryParser parser;

    public ShuttleInventoryServiceImpl(ShuttleInventoryRepository repository, ShuttleInventoryItemService itemService) {
        this.repository = repository;
        this.itemService = itemService;
        this.parser = new ShuttleInventoryParser();
    }

    // TODO : TRANS : ShuttleInventoryService.order tests
    @Override
    public ShuttleInventory order(Quality quality, LocalDate arrivalDate, LocalDate departureDate) {
        ShuttleInventory inventory = get();
        ShuttleCategory requestedCategory = new ShuttleCategoryBuilder().buildById(quality.getId());

        ShuttleInventoryEntity savedInventory = repository.save(parser.toEntity(inventory));

        inventory.getArrivalShuttles().get(quality.getId()).getArrivalShuttles().get(arrivalDate);
        inventory.getArrivalShuttles().get(arrivalDate).stream().filter(shuttleInventoryItem -> shuttleInventoryItem.getArrivalShuttles().);
        itemService.orderArrival(savedInventory, inventory.getArrivalShuttles().get(q), requestedCategory);
        itemService.orderDeparture(savedInventory, inventory.getDepartureShuttles().get(quality.getId()), requestedCategory);

        repository.update(savedInventory);

        return parser.parseEntity(savedInventory);
    }

    private ShuttleInventory get() {
        List<ShuttleInventoryEntity> inventories = new ArrayList<>();
        repository.findAll().forEach(inventories::add);

        if (inventories.isEmpty()) {
            return new ShuttleInventory();
        }

        return parser.parseEntity(inventories.get(0));
    }
}