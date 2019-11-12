package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenInventory;
import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTanksProducer;
import ca.ulaval.glo4002.booking.dto.oxygen.OxygenInventoryItemDto;
import ca.ulaval.glo4002.booking.enums.OxygenCategories;
import ca.ulaval.glo4002.booking.mappers.OxygenInventoryMapper;
import ca.ulaval.glo4002.booking.repositories.OxygenTankInventoryRepository;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

public class OxygenTankInventoryService {

	private final OxygenTankInventoryRepository repository;
	private final OxygenTanksProducer producer;
	private final OxygenInventoryMapper mapper;

	@Inject
	public OxygenTankInventoryService(OxygenTankInventoryRepository repository, OxygenTanksProducer producer, OxygenInventoryMapper mapper) {
		this.repository = repository;
		this.producer = producer;
		this.mapper = mapper;
	}

	// TODO : OxygenTankInventoryService.order(...)
	public List<OxygenInventoryItemDto> order(OxygenCategories category, LocalDate requestDate, Integer numberOfDays) {
		producer.produceOxygenForOrder(category, requestDate, numberOfDays);

		OxygenInventory inventory = repository.getInventory();

		return mapper.toDto(inventory);
	}
}
