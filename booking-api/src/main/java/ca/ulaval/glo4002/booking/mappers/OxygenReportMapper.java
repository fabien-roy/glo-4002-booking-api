package ca.ulaval.glo4002.booking.mappers;

import java.util.List;

import ca.ulaval.glo4002.booking.domain.oxygen.Report;
import ca.ulaval.glo4002.booking.dto.oxygen.HistoryItemDto;
import ca.ulaval.glo4002.booking.dto.oxygen.OxygenReportDto;
import ca.ulaval.glo4002.booking.dto.oxygen.OxygenTankInventoryDto;

import javax.inject.Inject;

public class OxygenReportMapper {

    private final OxygenTankInventoryMapper oxygenTankInventoryMapper;
    private final HistoryItemMapper historyItemMapper;

    @Inject
	public OxygenReportMapper(OxygenTankInventoryMapper oxygenTankInventoryMapper, HistoryItemMapper historyItemMapper) {
		this.oxygenTankInventoryMapper = oxygenTankInventoryMapper;
		this.historyItemMapper = historyItemMapper;
	}

	public OxygenReportDto toDto(Report report) {
		List<OxygenTankInventoryDto> inventory = oxygenTankInventoryMapper.toDto(report.getOxygenTankInventory());
		List<HistoryItemDto> history = historyItemMapper.toDto(report.getHistory());

		return new OxygenReportDto(inventory, history);
	}
}
