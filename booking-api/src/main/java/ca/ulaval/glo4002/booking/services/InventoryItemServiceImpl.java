package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.report.InventoryItem;
import ca.ulaval.glo4002.booking.entities.InventoryItemEntity;
import ca.ulaval.glo4002.booking.exceptions.report.InventoryItemNotFoundException;
import ca.ulaval.glo4002.booking.parsers.InventoryItemParser;
import ca.ulaval.glo4002.booking.repositories.InventoryItemRepository;

import java.util.ArrayList;
import java.util.List;


public class InventoryItemServiceImpl implements InventoryItemService {

	private final InventoryItemRepository inventoryItemRepository;
	private final InventoryItemParser inventoryItemParser = new InventoryItemParser();

	public InventoryItemServiceImpl(InventoryItemRepository repository) {
		this.inventoryItemRepository = repository;
	}

	public InventoryItem findById(Long id) {
		InventoryItemEntity inventoryItemEntity = inventoryItemRepository.findById(id)
				.orElseThrow(InventoryItemNotFoundException::new);

		return inventoryItemParser.parseEntity(inventoryItemEntity);
	}

	@Override
	public Iterable<InventoryItem> findAll() {
		List<InventoryItem> inventoryItems = new ArrayList<>();

		inventoryItemRepository.findAll().forEach(inventoryItem -> inventoryItems.add(inventoryItemParser.parseEntity(inventoryItem)));

		return inventoryItems;
	}

	// TODO : update inventoryItem stocked in the repo;
	@Override
	public InventoryItem addTank(Long categoryId, Long numberOfTank) {
		return null;
	}

}