package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTankInventory;
import ca.ulaval.glo4002.booking.domainobjects.qualities.Quality;

import java.time.LocalDate;

public interface OxygenTankInventoryService extends Service<OxygenTankInventory> {

    OxygenTankInventory get();

    OxygenTankInventory order(Quality quality, LocalDate orderDate);
}
