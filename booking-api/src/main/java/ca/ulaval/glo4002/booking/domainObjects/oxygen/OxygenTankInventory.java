package ca.ulaval.glo4002.booking.domainObjects.oxygen;

import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.exceptions.oxygen.OxygenCategoryNotFoundException;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.abs;
import static java.util.Optional.ofNullable;

public class OxygenTankInventory {
    private Map<Long, Long> inventory = new HashMap<>();
    private Map<Long, Long> tankInUse = new HashMap<>();

    public OxygenTankInventory() {
        inventory.put(OxygenConstants.Categories.E_ID, 0L);
        tankInUse.put(OxygenConstants.Categories.E_ID, 0L);
        inventory.put(OxygenConstants.Categories.B_ID, 0L);
        tankInUse.put(OxygenConstants.Categories.B_ID, 0L);
        tankInUse.put(OxygenConstants.Categories.A_ID, 0L);
        inventory.put(OxygenConstants.Categories.A_ID, 0L);
    }

    public void addTankInInventory(Long categoryID, Long quantityAdded) {
        Long quantityStored = ofNullable(inventory.get(categoryID))
                .orElseThrow(OxygenCategoryNotFoundException::new);

        inventory.replace(categoryID, quantityStored + quantityAdded);
    }

    public Long requestOxygenTank(Long categoryID, Long quantityRequested) {
        Long quantityStored = ofNullable(inventory.get(categoryID))
                .orElseThrow(OxygenCategoryNotFoundException::new);
        Long quantityInUse = ofNullable(tankInUse.get(categoryID))
                .orElseThrow(OxygenCategoryNotFoundException::new);

        Long numberOfTankNeeded = quantityStored - (quantityInUse + quantityRequested);
        if(numberOfTankNeeded < 0) {
            tankInUse.replace(categoryID, quantityStored);
            return abs(numberOfTankNeeded);
        } else {
            tankInUse.replace(categoryID, quantityInUse + quantityRequested);
            return 0L;
        }
    }

    public Long getInventoryByCategoryID(Long categoryID) {
        return ofNullable(inventory.get(categoryID))
                .orElseThrow(OxygenCategoryNotFoundException::new);
    }

    public long getTankInUseByCategoryID(Long categoryID) {
        return ofNullable(tankInUse.get(categoryID))
                .orElseThrow(OxygenCategoryNotFoundException::new);
    }
}
