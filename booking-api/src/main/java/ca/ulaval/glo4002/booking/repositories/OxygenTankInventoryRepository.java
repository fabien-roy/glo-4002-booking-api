package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenInventory;

public interface OxygenTankInventoryRepository {

	OxygenInventory getInventory();

	void setInventory(OxygenInventory inventory);
}
