package ca.ulaval.glo4002.booking.mappers;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenHistory;
import ca.ulaval.glo4002.booking.domain.oxygen.OxygenInventory;
import ca.ulaval.glo4002.booking.domain.oxygen.OxygenReport;
import ca.ulaval.glo4002.booking.dto.oxygen.OxygenHistoryItemDto;
import ca.ulaval.glo4002.booking.dto.oxygen.OxygenReportDto;
import ca.ulaval.glo4002.booking.dto.oxygen.OxygenInventoryItemDto;

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
