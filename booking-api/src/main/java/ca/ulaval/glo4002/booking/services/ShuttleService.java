package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.entities.ShuttleInventoryEntity;

public interface ShuttleService {

    Shuttle findById(Long id);

    Iterable<Shuttle> findAll();

    Shuttle order(ShuttleInventoryEntity inventory, Shuttle shuttle);
}
