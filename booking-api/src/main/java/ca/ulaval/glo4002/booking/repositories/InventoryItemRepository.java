package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.entities.InventoryItemEntity;
import org.springframework.data.repository.CrudRepository;

public interface InventoryItemRepository extends CrudRepository<InventoryItemEntity, Long> {

    <S extends InventoryItemEntity> S update(S inventoryItem);
}
