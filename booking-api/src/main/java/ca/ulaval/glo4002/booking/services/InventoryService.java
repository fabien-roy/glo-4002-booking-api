package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainObjects.report.Inventory;

public interface InventoryService extends Service<Inventory> {

    Inventory getInventory();
}
