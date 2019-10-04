package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.domainobjects.report.Report;
import ca.ulaval.glo4002.booking.dto.ReportDto;

public class ReportParser implements ToDtoParser<Report, ReportDto> {
	
    HistoryParser historyParser = new HistoryParser();
    OxygenTankInventoryParser oxygenTankInventoryParser = new OxygenTankInventoryParser();
	
    @Override
    public ReportDto toDto(Report report) {
        ReportDto dto = new ReportDto();

        dto.history = historyParser.toDto(report.getHistory());
        dto.inventory = oxygenTankInventoryParser.toDto(report.getOxygenTankInventory());

        return dto;
    }

}