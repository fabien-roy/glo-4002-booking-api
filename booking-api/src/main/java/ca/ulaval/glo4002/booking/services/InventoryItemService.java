package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.report.InventoryItem;
import ca.ulaval.glo4002.booking.entities.InventoryEntity;

import java.util.Map;

public interface InventoryItemService extends Service<InventoryEntity> {

    InventoryItem findById(Long id);

    Iterable<InventoryItem> findAll();

    InventoryItem addTank(Long categoryId, Long numberOfTank);

}
