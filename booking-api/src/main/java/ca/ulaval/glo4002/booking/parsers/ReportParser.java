package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.domainobjects.report.History;
import ca.ulaval.glo4002.booking.domainobjects.report.Inventory;
import ca.ulaval.glo4002.booking.domainobjects.report.Report;
import ca.ulaval.glo4002.booking.dto.ReportDto;

public class ReportParser implements DtoParser<Report, ReportDto> {
	
    HistoryParser historyParser = new HistoryParser();
    InventoryParser inventoryParser = new InventoryParser();
	

    @Override
    public Report parseDto(ReportDto dto) {
        History history = historyParser.parseDto(dto.history);
        Inventory inventory = inventoryParser.parseDto(dto.inventory);

        return new Report(history, inventory);
    }

    @Override
    public ReportDto toDto(Report report) {
        ReportDto dto = new ReportDto();

        dto.history = historyParser.toDto(report.getHistory());
        dto.inventory = inventoryParser.toDto(report.getInventory());

        return dto;
    }
}
