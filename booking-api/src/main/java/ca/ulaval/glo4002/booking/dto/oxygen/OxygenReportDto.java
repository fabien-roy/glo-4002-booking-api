package ca.ulaval.glo4002.booking.dto.oxygen;

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
