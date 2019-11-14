package ca.ulaval.glo4002.booking.oxygen.report;

import ca.ulaval.glo4002.booking.oxygen.history.OxygenHistoryItemDto;
import ca.ulaval.glo4002.booking.oxygen.inventory.OxygenInventoryItemDto;

import java.util.List;

public class OxygenReportDto {

	private List<OxygenInventoryItemDto> inventory;
	private List<OxygenHistoryItemDto> history;

	public OxygenReportDto(List<OxygenInventoryItemDto> inventory, List<OxygenHistoryItemDto> history) {
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
