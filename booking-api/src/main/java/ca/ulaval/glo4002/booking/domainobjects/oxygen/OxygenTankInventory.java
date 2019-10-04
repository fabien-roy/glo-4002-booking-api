package ca.ulaval.glo4002.booking.domainobjects.report;

import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTank;

import java.util.List;
import java.util.Map;

public class Inventory {

    private List<OxygenTank> notInUseTanks;
    private List<OxygenTank> inUseTanks;

    public Inventory(List<OxygenTank> notInUseTanks, List<OxygenTank> inUseTanks) {
        this.notInUseTanks = notInUseTanks;
        this.inUseTanks = inUseTanks;
    }

    public void replaceInUseTanks(Long categoryId, Long numberOfTanksToReplace) {
        inUseTanks.replace(categoryId, numberOfTanksToReplace);
    }

    public void replaceNotInUseTanks(Long categoryId, Long numberOfTanksToReplace) {
        notInUseTanks.replace(categoryId, numberOfTanksToReplace);
    }

    public Map<Long, Long> getNotInUseTanks() {
        return notInUseTanks;
    }

    public Map<Long, Long> getInUseTanks() {
        return inUseTanks;
    }

    public Long getNotInUseTanksByCategoryId(Long categoryId) {
        return notInUseTanks.get(categoryId);
    }

    public void setNotInUseTanks(Map<Long, Long> notInUseTanks) {
        this.notInUseTanks = notInUseTanks;
    }

    public Long getInUseTanksByCategoryId(Long categoryId) {
        return inUseTanks.get(categoryId);
    }

    public void setInUseTanks(Map<Long, Long> inUseTanks) {
        this.inUseTanks = inUseTanks;
    }
}