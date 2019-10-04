package ca.ulaval.glo4002.booking.domainobjects.report;

import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTankInventory;

public class Report {

    private History history;
    private OxygenTankInventory oxygenTankInventory;

    public Report(History history, OxygenTankInventory oxygenTankInventory) {
        this.history = history;
        this.oxygenTankInventory = oxygenTankInventory;
    }

    public History getHistory() {
        return history;
    }

    public OxygenTankInventory getOxygenTankInventory() {
        return oxygenTankInventory;
    }
}
