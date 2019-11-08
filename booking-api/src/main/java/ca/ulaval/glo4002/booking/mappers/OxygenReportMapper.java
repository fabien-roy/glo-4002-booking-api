package ca.ulaval.glo4002.booking.mappers;

import java.util.List;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenReport;
import ca.ulaval.glo4002.booking.dto.oxygen.OxygenHistoryItemDto;
import ca.ulaval.glo4002.booking.dto.oxygen.OxygenReportDto;
import ca.ulaval.glo4002.booking.dto.oxygen.OxygenInventoryItemDto;

import javax.inject.Inject;

public class OxygenReportMapper {

    private final OxygenTankInventoryMapper oxygenTankInventoryMapper;
    private final OxygenTankHistoryMapper oxygenTankHistoryMapper;

    @Inject
	public OxygenReportMapper(OxygenTankInventoryMapper oxygenTankInventoryMapper, OxygenTankHistoryMapper oxygenTankHistoryMapper) {
		this.oxygenTankInventoryMapper = oxygenTankInventoryMapper;
		this.oxygenTankHistoryMapper = oxygenTankHistoryMapper;
	}

	public OxygenReportDto toDto(OxygenReport oxygenReport) {
		List<OxygenInventoryItemDto> inventory = oxygenTankInventoryMapper.toDto(oxygenReport.getOxygenInventory());
		List<OxygenHistoryItemDto> history = oxygenTankHistoryMapper.toDto(oxygenReport.getOxygenHistory());

		return new OxygenReportDto(inventory, history);
	}
}
