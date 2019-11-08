package ca.ulaval.glo4002.booking.domain.oxygen;

public class Report {

	private OxygenHistory oxygenHistory;
	private OxygenTankInventory oxygenTankInventory;

	public Report() {
		oxygenHistory = new OxygenHistory();
		oxygenTankInventory = new OxygenTankInventory();
	}

	public Report(OxygenHistory oxygenHistory, OxygenTankInventory oxygenTankInventory) {
		this.oxygenHistory = oxygenHistory;
		this.oxygenTankInventory = oxygenTankInventory;
	}

	public OxygenHistory getOxygenHistory() {
		return oxygenHistory;
	}

	public OxygenTankInventory getOxygenTankInventory() {
		return oxygenTankInventory;
	}
}
