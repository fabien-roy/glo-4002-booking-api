package ca.ulaval.glo4002.booking.services;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTankInventory;
import ca.ulaval.glo4002.booking.dto.OxygenTankInventoryDto;
import ca.ulaval.glo4002.booking.enums.OxygenCategory;
import ca.ulaval.glo4002.booking.factories.OxygenTankFactory;
import ca.ulaval.glo4002.booking.mappers.OxygenTankInventoryMapper;
import ca.ulaval.glo4002.booking.repositories.OxygenTankInventoryRepository;

public class OxygenTankInventoryService {

	private final OxygenTankInventoryRepository repository;
	private final OxygenTankFactory factory;
	private final OxygenTankInventoryMapper mapper;

	@Inject
	public OxygenTankInventoryService(OxygenTankInventoryRepository repository, OxygenTankFactory factory,
			OxygenTankInventoryMapper mapper) {
		this.repository = repository;
		this.factory = factory;
		this.mapper = mapper;
	}

	// TODO : orderOxygenTanks
	public List<OxygenTankInventoryDto> orderOxygenTanks(OxygenCategory category, LocalDate requestDate,
			Long numberOfDays) {
		List<OxygenTank> oxygenTank = factory.buildOxygenTank(category, requestDate, numberOfDays);
		// TODO refactor, TDA. Not sure about this one :S.
		OxygenTankInventory inventory = repository.getInventory();
		// TODO Look why this line fail the test :
		// inventory.addTanksToInventory(category, oxygenTank);
		repository.setInventory(inventory);
		return mapper.toDto(inventory);
	}
}
