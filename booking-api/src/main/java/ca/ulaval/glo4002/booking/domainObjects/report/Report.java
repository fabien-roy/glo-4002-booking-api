package ca.ulaval.glo4002.booking.domainObjects.report;

public class Report {

    private History history;
    private Inventory inventory;

    public Report(History history, Inventory inventory) {
        this.history = history;
        this.inventory = inventory;
    }

    public History getHistory() {
        return history;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
