package ca.ulaval.glo4002.booking.domain.oxygen;

public class Report {

	private OxygenHistory oxygenHistory;
	private OxygenInventory oxygenInventory;

	public Report() {
		oxygenHistory = new OxygenHistory();
		oxygenInventory = new OxygenInventory();
	}

	public Report(OxygenHistory oxygenHistory, OxygenInventory oxygenInventory) {
		this.oxygenHistory = oxygenHistory;
		this.oxygenInventory = oxygenInventory;
	}

	public OxygenHistory getOxygenHistory() {
		return oxygenHistory;
	}

	public OxygenInventory getOxygenInventory() {
		return oxygenInventory;
	}
}
