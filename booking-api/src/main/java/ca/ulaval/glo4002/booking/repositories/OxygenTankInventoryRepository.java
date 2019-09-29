package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domainObjects.oxygen.OxygenTankInventory;
import org.springframework.data.repository.CrudRepository;

public interface OxygenTankInventoryRepository extends CrudRepository<OxygenTankInventory, Long> {

}
