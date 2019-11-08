package ca.ulaval.glo4002.booking.mappers;

import java.util.List;

import ca.ulaval.glo4002.booking.domain.oxygen.Report;
import ca.ulaval.glo4002.booking.dto.oxygen.HistoryItemDto;
import ca.ulaval.glo4002.booking.dto.oxygen.OxygenReportDto;
import ca.ulaval.glo4002.booking.dto.oxygen.OxygenTankInventoryDto;

public class OxygenReportMapper {
	public OxygenReportDto toDto(Report report) {
		OxygenTankInventoryMapper inventoryMapper = new OxygenTankInventoryMapper();
		List<OxygenTankInventoryDto> inventory = inventoryMapper.toDto(report.getOxygenTankInventory());
		HistoryItemMapper historyMapper = new HistoryItemMapper();
		List<HistoryItemDto> history = historyMapper.toDto(report.getHistory());
		return new OxygenReportDto(inventory, history);
	}
}
