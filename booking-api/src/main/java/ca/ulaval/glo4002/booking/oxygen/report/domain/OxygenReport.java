package ca.ulaval.glo4002.booking.oxygen.report.domain;

import ca.ulaval.glo4002.booking.oxygen.history.domain.OxygenHistory;
import ca.ulaval.glo4002.booking.oxygen.inventory.domain.OxygenInventory;

public class OxygenReport {

	private OxygenHistory oxygenHistory;
	private OxygenInventory oxygenInventory;

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
