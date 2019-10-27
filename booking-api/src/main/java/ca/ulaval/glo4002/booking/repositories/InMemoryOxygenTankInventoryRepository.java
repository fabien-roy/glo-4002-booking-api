package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTankInventory;

public class InMemoryOxygenTankInventoryRepository implements OxygenTankInventoryRepository {

	private OxygenTankInventory inventory;

	public InMemoryOxygenTankInventoryRepository() {
		inventory = new OxygenTankInventory();
	}

	@Override
	// TODO : Not sur if optional is needed in that case
	public OxygenTankInventory getInventory() {
		if (inventory == null) {
			this.inventory = new OxygenTankInventory();
		}

		return inventory;
	}

	@Override
	public void setInventory(OxygenTankInventory inventory) {
		this.inventory = inventory;
	}
}
