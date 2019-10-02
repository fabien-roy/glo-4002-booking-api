package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.domainobjects.report.Inventory;
import ca.ulaval.glo4002.booking.entities.InventoryEntity;
import ca.ulaval.glo4002.booking.parsers.InventoryParser;
import ca.ulaval.glo4002.booking.repositories.InventoryRepository;

import java.util.ArrayList;
import java.util.List;

public class InventoryServiceImpl implements InventoryService {

	private final InventoryParser parser;
	private InventoryRepository repository;

	public InventoryServiceImpl(InventoryRepository repository) {
		this.repository = repository;
		this.parser = new InventoryParser();
	}

	@Override
	public Inventory get() {
		List<InventoryEntity> inventories = new ArrayList<>();
		repository.findAll().forEach(inventories::add);

		return parser.parseEntity(inventories.get(0));
	}

	@Override
	public Inventory save(Inventory inventory) {
		return parser.parseEntity(repository.save(parser.toEntity(inventory)));
	}

	@Override
	public Iterable<OxygenTank> requestOxygenTanks(OxygenTank oxygenTank) {
		Long numberOfTanksNeeded = oxygenTank.getOxygenTankCategory().getProduction().getProducedTanks();
		updateInUseTanks(oxygenTank.getOxygenTankCategory().getId(), numberOfTanksNeeded);

		List<OxygenTank> oxygenTanks = new ArrayList<>();
		for (int i = 0; i < numberOfTanksNeeded; i++) {
			oxygenTanks.add(oxygenTank);
		}

		return oxygenTanks;
	}

	// TODO : OXY : Make sure this works
	@Override
	public void updateInUseTanks(Long categoryId, Long numberOfTanksNeeded) {
		Inventory inventory = get();

		Long quantityStored = inventory.getStoredTanksByCategoryId(categoryId);
		Long quantityInUse = inventory.getInUseTanksByCategoryId(categoryId);

		Long numberOfTanksToReplace = quantityStored - (quantityInUse + numberOfTanksNeeded);
		if (numberOfTanksToReplace < 0) {
			numberOfTanksToReplace = quantityStored;
		} else {
			numberOfTanksToReplace = quantityInUse + numberOfTanksToReplace;
		}

		inventory.replaceInUseTanks(categoryId, numberOfTanksToReplace);

		save(inventory);
	}

	@Override
	public void addTank(Long categoryId, Long numberOfTanksNeeded) {
		Inventory inventory = get();

		Long quantityStored = inventory.getStoredTanksByCategoryId(categoryId);

		inventory.replaceStoredTanks(categoryId, quantityStored + numberOfTanksNeeded);

		save(inventory);
	}
}
