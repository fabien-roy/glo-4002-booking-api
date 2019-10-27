package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domain.OxygenTankInventory;

import java.util.Optional;

public interface OxygenTankInventoryRepository {

    OxygenTankInventory getInventory();

    void setInventory(OxygenTankInventory inventory);
}
