package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenInventory;
import ca.ulaval.glo4002.booking.producers.OxygenTankProducer;
import ca.ulaval.glo4002.booking.dto.oxygen.OxygenInventoryItemDto;
import ca.ulaval.glo4002.booking.enums.OxygenCategories;
import ca.ulaval.glo4002.booking.mappers.OxygenInventoryMapper;
import ca.ulaval.glo4002.booking.repositories.OxygenTankInventoryRepository;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

public class OxygenInventoryService {

	private final OxygenTankInventoryRepository repository;
	private final OxygenTankProducer producer;
	private final OxygenInventoryMapper mapper;

	@Inject
	public OxygenInventoryService(OxygenTankInventoryRepository repository, OxygenTankProducer producer, OxygenInventoryMapper mapper) {
		this.repository = repository;
		this.producer = producer;
		this.mapper = mapper;
	}

	// TODO : OxygenInventoryService.order(...)
	public List<OxygenInventoryItemDto> order(OxygenCategories category, LocalDate requestDate, Integer numberOfDays) {
		producer.produceOxygenForOrder(category, requestDate, numberOfDays);

		OxygenInventory inventory = repository.getInventory();

		return mapper.toDto(inventory);
	}
}
