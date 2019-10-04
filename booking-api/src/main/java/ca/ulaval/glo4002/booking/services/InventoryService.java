package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.domainobjects.report.Inventory;

import java.util.Optional;

public interface InventoryService extends Service<Inventory> {

    Inventory get();

    Inventory save(Inventory inventory);

    Iterable<OxygenTank> requestOxygenTanks(OxygenTank oxygenTank);

    void updateInUseTanks(Long categoryId, Long numberOfTanksNeeded);

    void updateNotInUseTanks(Long categoryId, Long numberOfTanksToAdds);

    Optional<Long> getTankInUseByCategoryID(Long categoryId);

}
