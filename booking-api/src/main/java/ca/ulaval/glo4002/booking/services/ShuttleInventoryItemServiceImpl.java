package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.shuttles.Passenger;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.categories.ShuttleCategory;
import ca.ulaval.glo4002.booking.entities.ShuttleInventoryEntity;
import ca.ulaval.glo4002.booking.parsers.ShuttleParser;

import java.util.List;

public class ShuttleInventoryItemServiceImpl implements ShuttleInventoryItemService {

    private final ShuttleService shuttleService;
    private final ShuttleParser shuttleParser;

    public ShuttleInventoryItemServiceImpl(ShuttleService shuttleService) {
        this.shuttleService = shuttleService;
        this.shuttleParser = new ShuttleParser();
    }

    // TODO : TRANS : ShuttleInventoryItemService.orderArrival tests
    @Override
    public void orderArrival(ShuttleInventoryEntity inventory, List<Shuttle> shuttles, ShuttleCategory category) {
        inventory.addArrivalShuttle(shuttleParser.toEntity(shuttleService.order(inventory, getNextShuttle(shuttles, category))));
    }

    // TODO : TRANS : ShuttleInventoryItemService.orderDeparture tests
    @Override
    public void orderDeparture(ShuttleInventoryEntity inventory, List<Shuttle> shuttles, ShuttleCategory category) {
        inventory.addDepartureShuttle(shuttleParser.toEntity(shuttleService.order(inventory, getNextShuttle(shuttles, category))));
    }

    private Shuttle getNextShuttle(List<Shuttle> shuttles, ShuttleCategory category) {
        Shuttle dateShuttle;

        if (shuttles.isEmpty() || shuttles.get(shuttles.size() - 1).isFull()) {
            dateShuttle = new Shuttle(category);
            shuttles.add(dateShuttle);
        } else {
            dateShuttle = shuttles.get(shuttles.size() - 1);
            dateShuttle.addPassenger(new Passenger());
        }

        return dateShuttle;
    }
}