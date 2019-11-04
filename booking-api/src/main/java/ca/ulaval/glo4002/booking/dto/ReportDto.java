package ca.ulaval.glo4002.booking.dto;

import java.util.ArrayList;
import java.util.List;

public class ReportDto {
	public List<OxygenTankInventoryDto> inventory = new ArrayList<>();
	public List<HistoryItemDto> history = new ArrayList<>();

	public ReportDto(List<OxygenTankInventoryDto> inventory, List<HistoryItemDto> history) {
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
