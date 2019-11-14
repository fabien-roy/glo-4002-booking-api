package ca.ulaval.glo4002.booking.oxygen.inventory;

public class InMemoryOxygenInventoryRepository implements OxygenInventoryRepository {

	// TODO : OxygenInventory should actually be a OxygenTankRepository

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
