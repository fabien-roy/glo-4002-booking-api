package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.domainobjects.report.Inventory;
import ca.ulaval.glo4002.booking.entities.InventoryEntity;
import ca.ulaval.glo4002.booking.parsers.InventoryParser;
import ca.ulaval.glo4002.booking.repositories.InventoryRepository;

import java.util.ArrayList;
import java.util.List;

public class InventoryServiceImpl implements InventoryService {

	private InventoryRepository repository;
	private final InventoryParser parser;

	public InventoryServiceImpl(InventoryRepository repository) {
		this.repository = repository;
		this.parser = new InventoryParser();
	}

	// TODO : Test
	@Override
	public Inventory get() {
	    List<InventoryEntity> inventories = new ArrayList<>();
	    repository.findAll().forEach(inventories::add);

		return parser.parseEntity(inventories.get(0));
	}

	// TODO : Test
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

	// Copied from Inventory.java
	// TODO : to refactor completly.
	// Si on part d'un order (vraiment pas une bonne idée) train de getter de la mort
	//Long numberOfTankNeededPerDays = (order.getOrderItems().at(0).getCategory().getQuality().getOxygenTanksNeededPerDay())
	// Si package
	//Long quantityRequested = (END_DATE - START_DATE) * numberOfTankNeededPerDays
	// Si SinglePass
	//Long quantityRequested = order.getOrderItems().size() * numberOfTankNeededPerDays
	// Si on est capable d'avoir la category d'oxygène à partir d'un order (pas le cas présentement)
	//numberOfTankProducePerDays = order.getOrderItems.at(0).getOxygenCategory().getProduction().getProducedUnit()
	// Multiple de (1,3,5) sinon + 1
	//numberOfExpectedConstructorCall = (quantityRequested % numberOfTankProducePerDays == 0 ? quantityRequested / numberOfTankProducePerDays : quantityRequested / numberOfTankProducePerDays + 1)
	// Il faut tenir compte de l'inventaire non utilisé donc
	//realQuantityNeeded = abs(quantityStored - (quantityInUse + quantityRequested))
	//numberOfRealConstructorCall = (realQuantityNeeded % numberOfTankProducePerDays == 0 ? realQuantityNeeded / numberOfTankProducePerDays : realQuantityNeeded / numberOfTankProducePerDays + 1)
	// Il faut vraiment calculer quantityRequested avant d'envoyer à la fct
	// il va aussi manquer LocalDate timeRequested pour les call au constructeur Oxygentank ainsi que le save dans le repo.
	// TODO : Test
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

	// TODO : Test
    @Override
	public void addTank(Long categoryId, Long numberOfTanksNeeded) {
		Inventory inventory = get();

		Long quantityStored = inventory.getStoredTanksByCategoryId(categoryId);

		inventory.replaceStoredTanks(categoryId, quantityStored + numberOfTanksNeeded);

		save(inventory);
	}
}
