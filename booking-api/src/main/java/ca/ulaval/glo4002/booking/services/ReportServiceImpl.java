package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.report.History;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTankInventory;
import ca.ulaval.glo4002.booking.domainobjects.report.Report;


public class ReportServiceImpl implements ReportService {

    private OxygenTankInventoryService oxygenTankInventoryService;
    private HistoryService historyService; 
    
    public ReportServiceImpl(OxygenTankInventoryService oxygenTankInventoryService, HistoryService historyService) {
        this.oxygenTankInventoryService = oxygenTankInventoryService;
        this.historyService = historyService;
    }

    @Override
    public Report getReport() {
    	History history =  historyService.get();
    	OxygenTankInventory oxygenTankInventory = oxygenTankInventoryService.get();
    	
    	return new Report(history, oxygenTankInventory);
    }
}
