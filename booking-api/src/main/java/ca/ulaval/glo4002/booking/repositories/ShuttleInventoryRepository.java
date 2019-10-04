package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.entities.ShuttleInventoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface ShuttleInventoryRepository extends CrudRepository<ShuttleInventoryEntity, Long> {

    ShuttleInventoryEntity update(ShuttleInventoryEntity savedInventory);
}
