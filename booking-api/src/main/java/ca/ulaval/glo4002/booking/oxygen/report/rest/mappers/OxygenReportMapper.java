package ca.ulaval.glo4002.booking.oxygen.report.rest.mappers;

import ca.ulaval.glo4002.booking.oxygen.history.domain.OxygenHistory;
import ca.ulaval.glo4002.booking.oxygen.history.rest.OxygenHistoryItemResponse;
import ca.ulaval.glo4002.booking.oxygen.history.rest.mappers.OxygenHistoryMapper;
import ca.ulaval.glo4002.booking.oxygen.inventory.domain.OxygenInventory;
import ca.ulaval.glo4002.booking.oxygen.inventory.rest.OxygenInventoryItemDto;
import ca.ulaval.glo4002.booking.oxygen.inventory.rest.mappers.OxygenInventoryMapper;
import ca.ulaval.glo4002.booking.oxygen.report.rest.OxygenReportResponse;

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

	public OxygenReportResponse toResponse(OxygenInventory inventory, OxygenHistory history) {
		List<OxygenInventoryItemDto> inventoryDto = oxygenInventoryMapper.toDto(inventory);
		List<OxygenHistoryItemResponse> historyDto = oxygenHistoryMapper.toResponse(history);

		return new OxygenReportResponse(inventoryDto, historyDto);
	}
}
