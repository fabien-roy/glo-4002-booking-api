package ca.ulaval.glo4002.booking.dto.oxygen;

import java.util.List;

public class OxygenReportDto {

	public List<OxygenTankInventoryDto> inventory;
	public List<HistoryItemDto> history;

	public OxygenReportDto(List<OxygenTankInventoryDto> inventory, List<HistoryItemDto> history) {
		this.inventory = inventory;
		this.history = history;
	}

	public List<OxygenTankInventoryDto> getInventory() {
		return inventory;
	}

	public List<HistoryItemDto> getHistory() {
		return history;
	}
}
