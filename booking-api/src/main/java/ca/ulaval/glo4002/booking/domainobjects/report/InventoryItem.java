package ca.ulaval.glo4002.booking.domainobjects.report;

import ca.ulaval.glo4002.booking.constants.OxygenConstants;

import java.util.HashMap;
import java.util.Map;

public class InventoryItem {

    private Map<Long, Long> oxygenTanks = new HashMap<>();

    public InventoryItem() {
        oxygenTanks.put(OxygenConstants.Categories.E_ID, 0L);
        oxygenTanks.put(OxygenConstants.Categories.B_ID, 0L);
        oxygenTanks.put(OxygenConstants.Categories.A_ID, 0L);
    }

    public InventoryItem(Map<Long, Long> oxygenTanks) {
        this.oxygenTanks = oxygenTanks;
    }

    public void addOxygenTanks(Long categoryId, Long quantity) {
        oxygenTanks.put(categoryId, oxygenTanks.get(categoryId) + quantity);
    }

    public Map<Long, Long> getOxygenTanks() {
        return oxygenTanks;
    }
}