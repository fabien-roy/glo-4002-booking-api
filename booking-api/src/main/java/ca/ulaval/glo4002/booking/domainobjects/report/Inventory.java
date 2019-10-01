package ca.ulaval.glo4002.booking.domainobjects.report;

import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.exceptions.oxygen.OxygenCategoryNotFoundException;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.abs;
import static java.util.Optional.ofNullable;

public class Inventory {

    private Map<Long, Long> inventory = new HashMap<>();
    private Map<Long, Long> tankInUse = new HashMap<>();

    public Inventory() {
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

    // TODO : to refactor completly.
    // Si on part d'un order (vraiment pas une bonne idée) train de getter de la mort
    //Long numberOfTankNeededPerDays = (order.getOrderItems().at(0).getCategory().getQuality().getOxygenTanksNeededPerDay())
    // Si package
    //Long quantityRequested = (END_DATE - START_DATE) * numberOfTankNeededPerDays
    // Si SinglePass
    //Long quantityRequested = order.getOrderItems().size() * numberOfTankNeededPerDays
    // Si on est capable d'avoir la category d'oxygène à partir d'un order (pas le cas présentement)
    //numberOfTankProducePerDays = order.getOrderItems.at(0).getOxygenCategory().getProduction().getProducedUnit()
    // Multiple de (1,3,5) sinon + 1
    //numberOfExpectedConstructorCall = (quantityRequested % numberOfTankProducePerDays == 0 ? quantityRequested / numberOfTankProducePerDays : quantityRequested / numberOfTankProducePerDays + 1)
    // Il faut tenir compte de l'inventaire non utilisé donc
    //realQuantityNeeded = abs(quantityStored - (quantityInUse + quantityRequested))
    //numberOfRealConstructorCall = (realQuantityNeeded % numberOfTankProducePerDays == 0 ? realQuantityNeeded / numberOfTankProducePerDays : realQuantityNeeded / numberOfTankProducePerDays + 1)
    // Il faut vraiment calculer quantityRequested avant d'envoyer à la fct
    // il va aussi manquer LocalDate timeRequested pour les call au constructeur Oxygentank ainsi que le save dans le repo.
    public Long requestOxygenTank(Long categoryID, Long quantityRequested) {
        Long quantityStored = ofNullable(inventory.get(categoryID))
                .orElseThrow(OxygenCategoryNotFoundException::new);
        Long quantityInUse = ofNullable(tankInUse.get(categoryID))
                .orElseThrow(OxygenCategoryNotFoundException::new);

        Long numberOfTankNeeded = quantityStored - (quantityInUse + quantityRequested);
        if(numberOfTankNeeded < 0) {
            tankInUse.replace(categoryID, quantityStored);
            return abs(numberOfTankNeeded); // TODO : à refaire voir plus haut ça correspond au nombre de tanks a créer et non au nombre de call au constructeur
        } else {
            tankInUse.replace(categoryID, quantityInUse + quantityRequested);
            return 0L; // Cette partie la est bonne parcequ'on a assez de surplus pour avoir a faire aucun call
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

    public Map<Long, Long> getInventory() {
        return inventory;
    }
}
