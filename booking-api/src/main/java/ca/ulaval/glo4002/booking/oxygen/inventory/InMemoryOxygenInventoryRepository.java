package ca.ulaval.glo4002.booking.oxygen.inventory;

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
}
