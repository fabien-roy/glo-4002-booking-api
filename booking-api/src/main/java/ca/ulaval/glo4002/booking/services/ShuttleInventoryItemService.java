package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.categories.ShuttleCategory;
import ca.ulaval.glo4002.booking.entities.ShuttleInventoryEntity;

import java.util.List;

public interface ShuttleInventoryItemService {

    void orderArrival(ShuttleInventoryEntity inventory, List<Shuttle> shuttles, ShuttleCategory category);

    void orderDeparture(ShuttleInventoryEntity inventory, List<Shuttle> shuttles, ShuttleCategory category);
}
