package ca.ulaval.glo4002.booking.domainobjects.report;

import ca.ulaval.glo4002.booking.constants.OxygenConstants;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private Map<Long, Long> notInUseTanks = new HashMap<>();
    private Map<Long, Long> inUseTanks = new HashMap<>();

    public Inventory(Map<Long, Long> notInUseTanks, Map<Long, Long> inUseTanks) {
        this.notInUseTanks = notInUseTanks;
        this.inUseTanks = inUseTanks;
    }

    public Inventory() {
        notInUseTanks.put(OxygenConstants.Categories.E_ID, 0L);
        notInUseTanks.put(OxygenConstants.Categories.B_ID, 0L);
        notInUseTanks.put(OxygenConstants.Categories.A_ID, 0L);
        inUseTanks.put(OxygenConstants.Categories.E_ID, 0L);
        inUseTanks.put(OxygenConstants.Categories.B_ID, 0L);
        inUseTanks.put(OxygenConstants.Categories.A_ID, 0L);
    }

    public void replaceInUseTanks(Long categoryId, Long numberOfTanksToReplace) {
        inUseTanks.replace(categoryId, numberOfTanksToReplace);
    }

    public void replaceNotInUseTanks(Long categoryId, Long numberOfTanksToReplace) {
        notInUseTanks.replace(categoryId, numberOfTanksToReplace);
    }

    public Long getStoredTanksByCategoryId(Long categoryId) {
        return notInUseTanks.get(categoryId);
    }

    public Long getInUseTanksByCategoryId(Long categoryId) {
        return inUseTanks.get(categoryId);
    }

    public Map<Long, Long> getNotInUseTanks() {
        return notInUseTanks;
    }

    public Map<Long, Long> getInUseTanks() {
        return inUseTanks;
    }
}