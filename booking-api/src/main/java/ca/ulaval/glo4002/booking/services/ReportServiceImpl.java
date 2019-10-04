package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.report.History;
import ca.ulaval.glo4002.booking.domainobjects.report.Inventory;
import ca.ulaval.glo4002.booking.domainobjects.report.Report;


public class ReportServiceImpl implements ReportService {

    private InventoryService inventoryService;
    private HistoryService historyService; 
    
    public ReportServiceImpl(InventoryService inventoryService, HistoryService historyService) {
        this.inventoryService = inventoryService;
        this.historyService = historyService;
    }

    @Override
    public Report getReport() {
    	History history =  historyService.get();
    	Inventory inventory = inventoryService.get();
    	
    	return new Report(history, inventory);
    }
}
