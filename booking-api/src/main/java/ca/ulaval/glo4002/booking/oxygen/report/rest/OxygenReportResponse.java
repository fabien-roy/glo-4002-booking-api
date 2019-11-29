package ca.ulaval.glo4002.booking.oxygen.report.rest;

import ca.ulaval.glo4002.booking.oxygen.history.rest.OxygenHistoryItemDto;
import ca.ulaval.glo4002.booking.oxygen.inventory.rest.OxygenInventoryItemDto;

import java.util.List;

public class OxygenReportResponse {

	private List<OxygenInventoryItemDto> inventory;
	private List<OxygenHistoryItemDto> history;

	public OxygenReportResponse(List<OxygenInventoryItemDto> inventory, List<OxygenHistoryItemDto> history) {
		this.inventory = inventory;
		this.history = history;
	}

	public List<OxygenInventoryItemDto> getInventory() {
		return inventory;
	}

	public List<OxygenHistoryItemDto> getHistory() {
		return history;
	}
}
