package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.builders.oxygen.OxygenCategoryBuilder;
import ca.ulaval.glo4002.booking.constants.DateConstants;
import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTankInventory;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.categories.OxygenCategory;
import ca.ulaval.glo4002.booking.domainobjects.qualities.Quality;
import ca.ulaval.glo4002.booking.entities.OxygenTankEntity;
import ca.ulaval.glo4002.booking.entities.OxygenTankInventoryEntity;
import ca.ulaval.glo4002.booking.exceptions.dates.InvalidDateException;
import ca.ulaval.glo4002.booking.parsers.OxygenTankInventoryParser;
import ca.ulaval.glo4002.booking.parsers.OxygenTankParser;
import ca.ulaval.glo4002.booking.repositories.OxygenTankInventoryRepository;
import ca.ulaval.glo4002.booking.util.FestivalDateUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OxygenTankInventoryServiceImpl implements OxygenTankInventoryService {

	private OxygenTankInventoryRepository repository;
	private OxygenTankService oxygenTankService;
	private final OxygenTankInventoryParser parser;
	private final OxygenTankParser oxygenTankParser;
	private final OxygenCategoryBuilder categoryBuilder;

	public OxygenTankInventoryServiceImpl(OxygenTankInventoryRepository repository, OxygenTankService oxygenTankService) {
		this.repository = repository;
		this.oxygenTankService = oxygenTankService;
		this.parser = new OxygenTankInventoryParser();
		this.oxygenTankParser = new OxygenTankParser();
		this.categoryBuilder = new OxygenCategoryBuilder();
	}

	// TODO : OXY : Test
	@Override
	public OxygenTankInventory order(Quality requestedQuality, LocalDate orderDate) {
		if (FestivalDateUtil.isAfterFestivalStart(orderDate)) {
			throw new InvalidDateException();
		}

		OxygenTankInventory inventory = get();
		OxygenCategory requestedCategory = categoryBuilder.buildByQualityId(requestedQuality.getId());

		List<OxygenTank> requestedCategoryNotInUseTanks = inventory.getNotInUseTanks().stream().filter(oxygenTank -> oxygenTank.getCategory().getQuality().getId().equals(requestedCategory.getId())).collect(Collectors.toList());

		OxygenTankInventoryEntity savedInventory = repository.save(parser.toEntity(inventory));

		List<OxygenTank> notInUseTanks;
		List<OxygenTank> inUseTanks;
		List<OxygenTank> orderedTanks = new ArrayList<>();

		if (requestedCategoryNotInUseTanks.size() >= requestedCategory.getProduction().getProducedTanks()) {
			notInUseTanks = inventory.getNotInUseTanks().stream().filter(oxygenTank -> oxygenTank.getCategory().getQuality().getId().equals(requestedCategory.getId())).collect(Collectors.toList());
			inUseTanks = inventory.getInUseTanks().stream().filter(oxygenTank -> oxygenTank.getCategory().getQuality().getId().equals(requestedCategory.getId())).collect(Collectors.toList());

			for (int i = 0; i < requestedCategory.getProduction().getProducedTanks(); i++) {
				orderedTanks.add(notInUseTanks.remove(notInUseTanks.size() - 1));
				inUseTanks.add(orderedTanks.get(orderedTanks.size() - 1));
			}
		} else {
			LocalDate readyDate = getReadyDate(requestedCategory, orderDate);
			OxygenCategory availableCategory = getCategoryForTimeToProduce(requestedCategory, orderDate, readyDate);

			notInUseTanks = inventory.getNotInUseTanks().stream().filter(oxygenTank -> oxygenTank.getCategory().getQuality().getId().equals(availableCategory.getId())).collect(Collectors.toList());
			inUseTanks = inventory.getInUseTanks().stream().filter(oxygenTank -> oxygenTank.getCategory().getQuality().getId().equals(availableCategory.getId())).collect(Collectors.toList());

			if (notInUseTanks.size() >= availableCategory.getProduction().getProducedTanks()) {
				for (int i = 0; i < availableCategory.getProduction().getProducedTanks(); i++) {
					orderedTanks.add(notInUseTanks.remove(notInUseTanks.size() - 1));
					inUseTanks.add(orderedTanks.get(orderedTanks.size() - 1));
				}
			} else {
				for (int i = 0; i < notInUseTanks.size(); i++) {
					orderedTanks.add(notInUseTanks.remove(notInUseTanks.size() - 1));
					inUseTanks.add(orderedTanks.get(orderedTanks.size() - 1));
				}

				for (int i = 0; i < availableCategory.getProduction().getProducedTanks() - orderedTanks.size(); i++) {
					orderedTanks.add(oxygenTankService.order(savedInventory, availableCategory, orderDate));
					inUseTanks.add(orderedTanks.get(orderedTanks.size() - 1));
				}
			}
		}

		List<OxygenTankEntity> inUseTankEntities = new ArrayList<>();
		List<OxygenTankEntity> notInUseTankEntities = new ArrayList<>();

		inUseTanks.forEach(tank -> inUseTankEntities.add(oxygenTankParser.toEntity(tank)));
		notInUseTanks.forEach(tank -> notInUseTankEntities.add(oxygenTankParser.toEntity(tank)));

		savedInventory.setInUseTanks(inUseTankEntities);
		savedInventory.setNotInUseTanks(notInUseTankEntities);

		savedInventory = repository.save(savedInventory);

		return parser.parseEntity(savedInventory);
	}

	@Override
	public OxygenTankInventory get() {
		List<OxygenTankInventoryEntity> inventories = new ArrayList<>();
		repository.findAll().forEach(inventories::add);

		if (inventories.isEmpty()) {
			return new OxygenTankInventory();
		}

		return parser.parseEntity(inventories.get(0));

	}

	private LocalDate getReadyDate(OxygenCategory category, LocalDate orderDate) {
		return orderDate.plusDays(category.getProduction().getProductionTime().toDays());
	}

	private OxygenCategory getCategoryForTimeToProduce(OxygenCategory requestedCategory, LocalDate requestDate, LocalDate readyDate) {
		if (readyDate.isBefore(DateConstants.START_DATE)) {
			return requestedCategory;
		} else if (requestDate.plusDays(OxygenConstants.Productions.ELECTROLYTES_PRODUCTION_TIME.toDays()).isBefore(DateConstants.START_DATE)) {
			return categoryBuilder.buildById(OxygenConstants.Categories.B_ID);
		} else {
			return categoryBuilder.buildById(OxygenConstants.Categories.E_ID);
		}
	}
}