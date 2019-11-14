package ca.ulaval.glo4002.booking.oxygen.report;

import ca.ulaval.glo4002.booking.oxygen.history.OxygenHistory;
import ca.ulaval.glo4002.booking.oxygen.history.OxygenHistoryItemDto;
import ca.ulaval.glo4002.booking.oxygen.history.OxygenHistoryMapper;
import ca.ulaval.glo4002.booking.oxygen.inventory.OxygenInventory;
import ca.ulaval.glo4002.booking.oxygen.inventory.OxygenInventoryItemDto;
import ca.ulaval.glo4002.booking.oxygen.inventory.OxygenInventoryMapper;

import java.util.List;

import javax.inject.Inject;

public class OxygenReportMapper {

    private final OxygenInventoryMapper oxygenInventoryMapper;
    private final OxygenHistoryMapper oxygenHistoryMapper;

    @Inject
	public OxygenReportMapper(OxygenInventoryMapper oxygenInventoryMapper, OxygenHistoryMapper oxygenHistoryMapper) {
		this.oxygenInventoryMapper = oxygenInventoryMapper;
		this.oxygenHistoryMapper = oxygenHistoryMapper;
	}

	public OxygenReportDto toDto(OxygenInventory inventory, OxygenHistory history) {
		List<OxygenInventoryItemDto> inventoryDto = oxygenInventoryMapper.toDto(inventory);
		List<OxygenHistoryItemDto> historyDto = oxygenHistoryMapper.toDto(history);

		return new OxygenReportDto(inventoryDto, historyDto);
	}
}
