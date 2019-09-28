package ca.ulaval.glo4002.booking.entities.oxygen;

import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.exceptions.oxygen.OxygenCategoryNotFoundException;

import java.util.HashMap;
import java.util.Map;

import static java.util.Optional.ofNullable;

public class OxygenTankInventory {
    private Map<Long, Long> inventory = new HashMap<>();
    private Map<Long, Long> inUseTank = new HashMap<>();

    public OxygenTankInventory() {
        inventory.put(OxygenConstants.Categories.E_ID, 0L);
        inUseTank.put(OxygenConstants.Categories.E_ID, 0L);
        inventory.put(OxygenConstants.Categories.B_ID, 0L);
        inUseTank.put(OxygenConstants.Categories.B_ID, 0L);
        inUseTank.put(OxygenConstants.Categories.A_ID, 0L);
        inventory.put(OxygenConstants.Categories.A_ID, 0L);
    }



    public void addTankInInventory(Long category, Long quantity) {
        ofNullable(inventory.replace(category, inventory.get(category) + quantity)).orElseThrow(OxygenCategoryNotFoundException::new);
    }

    public boolean requestOxygenTank(Long categoryID, Long quantityRequested) {;
        Long quantityStored = ofNullable(inventory.get(categoryID)).orElseThrow(OxygenCategoryNotFoundException::new);
        Long quantityInUse = ofNullable(inventory.get(categoryID)).orElseThrow(OxygenCategoryNotFoundException::new);

        if(quantityStored - quantityInUse + quantityRequested < 0){
            return false;
        }
        else {
            inUseTank.replace(categoryID, quantityInUse + quantityRequested);
            return true;
        }
    }

    public Long getInventoryByCategoryID(Long categoryID) {
        return ofNullable(inventory.get(categoryID)).orElseThrow(OxygenCategoryNotFoundException::new);
    }
}
