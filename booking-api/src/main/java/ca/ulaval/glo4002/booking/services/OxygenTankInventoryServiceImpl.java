package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTankInventory;
import ca.ulaval.glo4002.booking.entities.OxygenTankInventoryEntity;
import ca.ulaval.glo4002.booking.exceptions.oxygen.OxygenCategoryNotFoundException;
import ca.ulaval.glo4002.booking.parsers.InventoryParser;
import ca.ulaval.glo4002.booking.repositories.InventoryRepository;

import java.util.*;

public class InventoryServiceImpl implements InventoryService {

	private OxygenTankInventory oxygenTankInventory;
	private InventoryRepository inventoryRepository;
	private final InventoryParser inventoryParser;

	public InventoryServiceImpl(InventoryRepository inventoryRepository) {
		this.inventoryRepository = inventoryRepository;
		this.inventoryParser = new InventoryParser();
	}

	@Override
	public OxygenTankInventory get() {
		List<OxygenTankInventoryEntity> inventories = new ArrayList<>();
		inventoryRepository.findAll().forEach(inventories::add);

		if (inventories.isEmpty()) {
			return new OxygenTankInventory(new HashMap<>(), new HashMap<>());
		}

		return inventoryParser.parseEntity(inventories.get(0));

	}

	@Override
	public OxygenTankInventory save(OxygenTankInventory oxygenTankInventory) {
		OxygenTankInventoryEntity oxygenTankInventoryEntity = inventoryParser.toEntity(oxygenTankInventory);

		oxygenTankInventory.getInUseTanks().forEach((categoryId, quantity) -> {
			//inventoryItemService.save(categoryId, quantity);
		});

		oxygenTankInventory.getNotInUseTanks().forEach((categoryId, quantity) -> {
			//inventoryItemService.save(categoryId, quantity);
		});

		return inventoryParser.parseEntity(inventoryRepository.save(oxygenTankInventoryEntity));
	}

	@Override
	public Iterable<OxygenTank> requestOxygenTanks(OxygenTank oxygenTank) {
		Long numberOfTanksNeeded = oxygenTank.getCategory().getProduction().getProducedTanks();

		// TODO : OXY : This was commented because it is not ready
		updateInUseTanks(oxygenTank.getCategory().getId(), numberOfTanksNeeded);

		List<OxygenTank> oxygenTanks = new ArrayList<>();
		for (int i = 0; i < numberOfTanksNeeded; i++) {
			oxygenTanks.add(oxygenTank);
		}

		return oxygenTanks;
	}

	@Override
	public void updateInUseTanks(Long categoryId, Long numberOfTanksToAdds) {
		OxygenTankInventory oxygenTankInventory = get();
		oxygenTankInventory.replaceInUseTanks(categoryId, numberOfTanksToAdds);

		save(oxygenTankInventory);
	}

	@Override
	public void updateNotInUseTanks(Long categoryId, Long numberOfTanksToAdds) {
		OxygenTankInventory oxygenTankInventory = get();

		Long currentNumberOfTanks = oxygenTankInventory.getNotInUseTanks().get(categoryId);
		oxygenTankInventory.replaceInUseTanks(categoryId, numberOfTanksToAdds + currentNumberOfTanks);

		save(oxygenTankInventory);
	}

	// TODO : fix this method (get of a invalid key on a map should return null)
	public Optional<Long> getTankInUseByCategoryID(Long categoryId) {
		Long quantity = oxygenTankInventory.getInUseTanksByCategoryId(categoryId);

		if(quantity == null){
			throw new OxygenCategoryNotFoundException();
		}

		return Optional.of(quantity);
	}
}