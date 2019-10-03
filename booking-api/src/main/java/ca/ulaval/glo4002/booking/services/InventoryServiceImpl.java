package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.domainobjects.report.Inventory;
import ca.ulaval.glo4002.booking.entities.InventoryEntity;
import ca.ulaval.glo4002.booking.entities.InventoryItemEntity;
import ca.ulaval.glo4002.booking.parsers.InventoryParser;
import ca.ulaval.glo4002.booking.repositories.InventoryRepository;

import java.util.ArrayList;
import java.util.List;

public class InventoryServiceImpl implements InventoryService {

	private final InventoryParser parser;
	private InventoryRepository repository;
	private InventoryItemService inventoryItemService;

	public InventoryServiceImpl(InventoryRepository repository, InventoryItemService inventoryItemService) {
		this.repository = repository;
		this.inventoryItemService = inventoryItemService;
		this.parser = new InventoryParser();
	}

	@Override
	public Inventory get() {
		List<InventoryEntity> inventories = new ArrayList<>();
		repository.findAll().forEach(inventories::add);

		if (inventories.isEmpty()) {
			return new Inventory();
		}

		return parser.parseEntity(inventories.get(0));
	}

	@Override
	public Inventory save(Inventory inventory) {
		InventoryEntity inventoryEntity = parser.toEntity(inventory);

		inventoryItemService.save(inventoryEntity, inventory.getInUseTanks());

		return parser.parseEntity(repository.save(inventoryEntity));
	}

	@Override
	public Iterable<OxygenTank> requestOxygenTanks(OxygenTank oxygenTank) {
		Long numberOfTanksNeeded = oxygenTank.getCategory().getProduction().getProducedTanks();
		updateInUseTanks(oxygenTank.getCategory().getId(), numberOfTanksNeeded);

		List<OxygenTank> oxygenTanks = new ArrayList<>();
		for (int i = 0; i < numberOfTanksNeeded; i++) {
			oxygenTanks.add(oxygenTank);
		}

		return oxygenTanks;
	}

	@Override
	public void updateInUseTanks(Long categoryId, Long numberOfTanksToAdds) {
		Inventory inventory = get();

		Long currentNumberOfTanks = inventory.getInUseTanks().get(categoryId);
		inventory.replaceInUseTanks(categoryId, numberOfTanksToAdds + currentNumberOfTanks);

		save(inventory);
	}

	@Override
	public void updateNotInUseTanks(Long categoryId, Long numberOfTanksToAdds) {
		Inventory inventory = get();

		Long currentNumberOfTanks = inventory.getNotInUseTanks().get(categoryId);
		inventory.replaceInUseTanks(categoryId, numberOfTanksToAdds + currentNumberOfTanks);

		save(inventory);
	}
}
