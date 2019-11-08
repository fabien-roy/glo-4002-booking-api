package ca.ulaval.glo4002.booking.domain.oxygen;

public class OxygenReport {

	private OxygenHistory oxygenHistory;
	private OxygenInventory oxygenInventory;

	public OxygenReport() {
		oxygenHistory = new OxygenHistory();
		oxygenInventory = new OxygenInventory();
	}

	public OxygenReport(OxygenHistory oxygenHistory, OxygenInventory oxygenInventory) {
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
