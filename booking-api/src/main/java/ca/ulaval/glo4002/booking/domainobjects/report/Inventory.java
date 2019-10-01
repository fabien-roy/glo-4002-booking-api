package ca.ulaval.glo4002.booking.domainobjects.report;

import ca.ulaval.glo4002.booking.constants.OxygenConstants;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private Map<Long, Long> storedTanks = new HashMap<>();
    private Map<Long, Long> inUseTanks = new HashMap<>();

    public Inventory() {
        storedTanks.put(OxygenConstants.Categories.E_ID, 0L);
        storedTanks.put(OxygenConstants.Categories.B_ID, 0L);
        storedTanks.put(OxygenConstants.Categories.A_ID, 0L);
        inUseTanks.put(OxygenConstants.Categories.E_ID, 0L);
        inUseTanks.put(OxygenConstants.Categories.B_ID, 0L);
        inUseTanks.put(OxygenConstants.Categories.A_ID, 0L);
    }

    public Inventory(Map<Long, Long> storedTanks, Map<Long, Long> inUseTanks) {
        this.storedTanks = storedTanks;
        this.inUseTanks = inUseTanks;
    }

    public void replaceStoredTanks(Long categoryId, Long numberOfTanksToReplace) {
        storedTanks.replace(categoryId, numberOfTanksToReplace);
    }

    public void replaceInUseTanks(Long categoryId, Long numberOfTanksToReplace) {
        inUseTanks.replace(categoryId, numberOfTanksToReplace);
    }

    public Long getStoredTanksByCategoryId(Long categoryId) {
        return storedTanks.get(categoryId);
    }

    public Long getInUseTanksByCategoryId(Long categoryId) {
        return inUseTanks.get(categoryId);
    }

    public Map<Long, Long> getStoredTanks() {
        return storedTanks;
    }

    public Map<Long, Long> getInUseTanks() {
        return inUseTanks;
    }
}
