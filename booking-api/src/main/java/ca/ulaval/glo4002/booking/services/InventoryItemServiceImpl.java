package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.entities.InventoryEntity;
import ca.ulaval.glo4002.booking.entities.InventoryItemEntity;
import ca.ulaval.glo4002.booking.repositories.InventoryItemRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InventoryItemServiceImpl implements InventoryItemService {

	private InventoryItemRepository repository;

	public InventoryItemServiceImpl(InventoryItemRepository repository) {
		this.repository = repository;
	}

	@Override
	public Map<Long, Long> save(InventoryEntity inventory, Map<Long, Long> oxygenTanks) {
		List<InventoryItemEntity> inventoryItemEntities = new ArrayList<>();

		oxygenTanks.forEach((categoryId, quantity) -> inventoryItemEntities.add(new InventoryItemEntity(categoryId, quantity)));

		repository.saveAll(inventoryItemEntities);

		inventory.clearInventoryItems();
		inventory.addInventoryItems(inventoryItemEntities);

		return oxygenTanks;
	}
}
