package ca.ulaval.glo4002.booking.oxygen.inventory;

import ca.ulaval.glo4002.booking.oxygen.OxygenTank;

import java.util.List;

public class InMemoryOxygenInventoryRepository implements OxygenInventoryRepository {

	private OxygenInventory inventory;

	public InMemoryOxygenInventoryRepository() {
		this.inventory = new OxygenInventory();
	}

	@Override
	public OxygenInventory getInventory() {
		return inventory;
	}

	@Override
	public void setInventory(OxygenInventory inventory) {
		this.inventory = inventory;
	}

	@Override
	public List<OxygenTank> findall() {
		return inventory.getAllTanks();
	}
}
