package ca.ulaval.glo4002.booking.mappers;

import java.util.List;

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

	// TODO : OxygenHistory should be built using the inventory
	public OxygenReportDto toDto(OxygenReport oxygenReport) {
		List<OxygenInventoryItemDto> inventory = oxygenInventoryMapper.toDto(oxygenReport.getOxygenInventory());
		List<OxygenHistoryItemDto> history = oxygenHistoryMapper.toDto(oxygenReport.getOxygenHistory());

		return new OxygenReportDto(inventory, history);
	}
}
