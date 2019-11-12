package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenInventory;

public class InMemoryOxygenInventoryRepository implements OxygenInventoryRepository {

	private OxygenInventory inventory;

	public InMemoryOxygenInventoryRepository() {
		this.inventory = new OxygenInventory();
	}

	public InMemoryOxygenInventoryRepository(OxygenInventory inventory) {
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
