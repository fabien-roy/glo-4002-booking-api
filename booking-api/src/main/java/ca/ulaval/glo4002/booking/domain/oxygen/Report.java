package ca.ulaval.glo4002.booking.domain.oxygen;

public class Report {
	private History history;
	private OxygenTankInventory oxygenTankInventory;

	public Report() {
		history = new History();
		oxygenTankInventory = new OxygenTankInventory();
	}

	public Report(History history, OxygenTankInventory oxygenTankInventory) {
		super();
		this.history = history;
		this.oxygenTankInventory = oxygenTankInventory;
	}

	public History getHistory() {
		return history;
	}

	public OxygenTankInventory getOxygenTankInventory() {
		return oxygenTankInventory;
	}
}
