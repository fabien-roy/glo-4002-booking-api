package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenInventory;

public class InMemoryOxygenTankInventoryRepository implements OxygenTankInventoryRepository {

	private OxygenInventory inventory;

	public InMemoryOxygenTankInventoryRepository() {
		inventory = new OxygenInventory();
	}

	@Override
	public OxygenInventory getInventory() {
		if (inventory == null) {
			this.inventory = new OxygenInventory();
		}

		return inventory;
	}

	@Override
	public void setInventory(OxygenInventory inventory) {
		this.inventory = inventory;
	}
}
