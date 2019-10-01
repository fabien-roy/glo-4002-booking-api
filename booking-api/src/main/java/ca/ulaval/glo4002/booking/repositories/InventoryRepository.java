package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.entities.InventoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface InventoryRepository extends CrudRepository<InventoryEntity, Long> {

}
