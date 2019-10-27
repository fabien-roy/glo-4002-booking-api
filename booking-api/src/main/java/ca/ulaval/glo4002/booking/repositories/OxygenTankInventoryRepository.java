package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTankInventory;

public interface OxygenTankInventoryRepository {

	OxygenTankInventory getInventory();

	void setInventory(OxygenTankInventory inventory);
}
