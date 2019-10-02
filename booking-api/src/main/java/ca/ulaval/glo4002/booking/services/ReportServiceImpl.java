package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.report.History;
import ca.ulaval.glo4002.booking.domainobjects.report.Inventory;
import ca.ulaval.glo4002.booking.domainobjects.report.Report;


public class ReportServiceImpl implements ReportService {

    private OxygenTankService oxygenTankService;
    private InventoryService inventoryService;
    private HistoryService historyService; 
    
    public ReportServiceImpl(OxygenTankService oxygenTankService) {
        this.oxygenTankService = oxygenTankService;
    }

    @Override
    public Report getReport() {
    	History history =  historyService.get();
    	Inventory inventory = inventoryService.get();
    	
    	return new Report(history, inventory);
    }
}
