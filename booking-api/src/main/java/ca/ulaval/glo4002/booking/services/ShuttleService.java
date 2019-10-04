package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.entities.ShuttleInventoryEntity;

public interface ShuttleService {

    Iterable<Shuttle> findAll();

    Shuttle orderArrival(ShuttleInventoryEntity savedInventory, Shuttle nextShuttle);

    Shuttle orderDeparture(ShuttleInventoryEntity savedInventory, Shuttle nextShuttle);
}
