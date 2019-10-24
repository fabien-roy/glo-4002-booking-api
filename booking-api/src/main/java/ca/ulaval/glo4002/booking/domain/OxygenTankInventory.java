package ca.ulaval.glo4002.booking.domain;

import ca.ulaval.glo4002.booking.enums.OxygenTankCategory;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

// TODO : modify Map<OxygenTankCategory, Long> notInUseTanks; AND Map<OxygenTankCategory, Long> inUseTanks;
// to Map<OxygenTankCategory, List<OxygenTank>> notInUseTanks AND Map<OxygenTankCategory, List<OxygenTank>> inUseTanks;
public class OxygenTankInventory {

    private Map<OxygenTankCategory, Long> notInUseTanks;
    private Map<OxygenTankCategory, Long> inUseTanks;

    public OxygenTankInventory() {
        this.notInUseTanks = new EnumMap<>(OxygenTankCategory.class);
        this.inUseTanks = new EnumMap<>(OxygenTankCategory.class);

        notInUseTanks.put(OxygenTankCategory.CATEGORY_A, 0L);
        notInUseTanks.put(OxygenTankCategory.CATEGORY_B, 0L);
        notInUseTanks.put(OxygenTankCategory.CATEGORY_E, 0L);

        inUseTanks.put(OxygenTankCategory.CATEGORY_A, 0L);
        inUseTanks.put(OxygenTankCategory.CATEGORY_B, 0L);
        inUseTanks.put(OxygenTankCategory.CATEGORY_E, 0L);
    }

    public Long getNotInUseQuantityByCategory(OxygenTankCategory category) {
        return notInUseTanks.get(category);
    }

    public Long getInUseQuantityByCategory(OxygenTankCategory category) {
        return inUseTanks.get(category);
    }

    public void addTankToInventory(OxygenTankCategory category, Long quantity) {
        Long currentQuantity = notInUseTanks.get(category);

        notInUseTanks.replace(category, currentQuantity + quantity);
    }

    public Long requestTankByCategory(OxygenTankCategory category, Long quantity) {
        Long currentNotInUseQuantity = notInUseTanks.get(category);
        Long currentInUseQuantity = inUseTanks.get(category);
        Long quantityStillNeeded = 0L;

        if(currentNotInUseQuantity >= quantity) {
            notInUseTanks.replace(category, currentNotInUseQuantity - quantity);
            inUseTanks.replace(category, currentInUseQuantity + quantity);
        } else {
            notInUseTanks.replace(category, 0L);
            inUseTanks.replace(category, currentInUseQuantity + currentNotInUseQuantity);
            quantityStillNeeded = Math.abs(currentNotInUseQuantity - quantity);
        }

        return quantityStillNeeded;
    }
}
