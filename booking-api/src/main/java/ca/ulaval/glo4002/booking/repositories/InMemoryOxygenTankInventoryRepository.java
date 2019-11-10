package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenInventory;

public class InMemoryOxygenTankInventoryRepository implements OxygenTankInventoryRepository {

	private OxygenInventory inventory;

	public InMemoryOxygenTankInventoryRepository() {
		this.inventory = new OxygenInventory();
	}

	public InMemoryOxygenTankInventoryRepository(OxygenInventory inventory) {
		this.inventory = inventory;
	}

	@Override
	public OxygenInventory getInventory() {
		return inventory;
	}

	@Override
	public void setInventory(OxygenInventory inventory) {
		this.inventory = inventory;
	}
}
