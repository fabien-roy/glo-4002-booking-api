package ca.ulaval.glo4002.booking.mappers;

import java.util.List;

import ca.ulaval.glo4002.booking.domain.oxygen.Report;
import ca.ulaval.glo4002.booking.dto.oxygen.HistoryItemDto;
import ca.ulaval.glo4002.booking.dto.oxygen.OxygenReportDto;
import ca.ulaval.glo4002.booking.dto.oxygen.OxygenTankInventoryDto;

import javax.inject.Inject;

public class OxygenReportMapper {

    private final OxygenTankInventoryMapper oxygenTankInventoryMapper;
    private final OxygenTankHistoryMapper oxygenTankHistoryMapper;

    @Inject
	public OxygenReportMapper(OxygenTankInventoryMapper oxygenTankInventoryMapper, OxygenTankHistoryMapper oxygenTankHistoryMapper) {
		this.oxygenTankInventoryMapper = oxygenTankInventoryMapper;
		this.oxygenTankHistoryMapper = oxygenTankHistoryMapper;
	}

	public OxygenReportDto toDto(Report report) {
		List<OxygenTankInventoryDto> inventory = oxygenTankInventoryMapper.toDto(report.getOxygenTankInventory());
		List<HistoryItemDto> history = oxygenTankHistoryMapper.toDto(report.getHistory());

		return new OxygenReportDto(inventory, history);
	}
}
