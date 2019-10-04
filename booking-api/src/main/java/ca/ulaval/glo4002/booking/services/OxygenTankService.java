package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.categories.OxygenCategory;
import ca.ulaval.glo4002.booking.entities.OxygenTankInventoryEntity;

import java.time.LocalDate;

public interface OxygenTankService extends Service<OxygenTank> {

    Iterable<OxygenTank> findAll();

    OxygenTank order(OxygenTankInventoryEntity savedInventory, OxygenCategory availableCategory, LocalDate date);

    OxygenTank setToInUse(OxygenTankInventoryEntity savedInventory, OxygenTank oxygenTank);
}
