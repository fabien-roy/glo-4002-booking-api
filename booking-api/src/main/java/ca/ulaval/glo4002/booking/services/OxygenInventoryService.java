package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenInventory;
import ca.ulaval.glo4002.booking.domain.passes.Pass;
import ca.ulaval.glo4002.booking.enums.PassCategories;
import ca.ulaval.glo4002.booking.producers.OxygenTankProducer;
import ca.ulaval.glo4002.booking.dto.oxygen.OxygenInventoryItemDto;
import ca.ulaval.glo4002.booking.enums.OxygenCategories;
import ca.ulaval.glo4002.booking.mappers.OxygenInventoryMapper;
import ca.ulaval.glo4002.booking.repositories.OxygenInventoryRepository;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

public class OxygenInventoryService {

	private final OxygenInventoryRepository repository;
	private final OxygenTankProducer producer;
	private final OxygenInventoryMapper mapper;

	@Inject
	public OxygenInventoryService(OxygenInventoryRepository repository, OxygenTankProducer producer, OxygenInventoryMapper mapper) {
		this.repository = repository;
		this.producer = producer;
		this.mapper = mapper;
	}

	// TODO : OrderInventoryService.orderForPasses()
	void orderForPasses(PassCategories passCategory, List<Pass> passes) {
		/*
		OxygenCategories oxygenCategory = factory.buildCategory(passCategory);

		passes.forEach(pass -> {
		    // TODO : Copied from TripService
			Passenger passenger = new Passenger(pass.getPassNumber());

			if (pass.getEventDate() == null) {
				orderForFullFestival(passenger, shuttleCategory);
			} else {
				orderForEventDate(passenger, shuttleCategory, pass.getEventDate());
			}
		});
		*/
	}

	// TODO : OxygenInventoryService.order(...) is probably useless
	public List<OxygenInventoryItemDto> order(OxygenCategories category, LocalDate requestDate, Integer numberOfDays) {
		producer.produceOxygenForOrder(category, requestDate, numberOfDays);

		OxygenInventory inventory = repository.getInventory();

		return mapper.toDto(inventory);
	}
}
