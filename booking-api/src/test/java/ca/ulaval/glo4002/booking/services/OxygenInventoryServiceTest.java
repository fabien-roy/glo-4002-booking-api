package ca.ulaval.glo4002.booking.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.enums.OxygenCategories;
import ca.ulaval.glo4002.booking.factories.OxygenTankFactory;
import ca.ulaval.glo4002.booking.mappers.OxygenInventoryMapper;
import ca.ulaval.glo4002.booking.repositories.OxygenTankInventoryRepository;

class OxygenInventoryServiceTest {

	private OxygenTankInventoryService oxygenTankInventoryService;
	private OxygenTankInventoryRepository repository;

	@BeforeEach
	void setUpSubject() {
		repository = mock(OxygenTankInventoryRepository.class);
		OxygenTankFactory factory = mock(OxygenTankFactory.class);
		OxygenInventoryMapper mapper = mock(OxygenInventoryMapper.class);

		oxygenTankInventoryService = new OxygenTankInventoryService(repository, factory, mapper);
	}

	@Test
	void orderOxygenTank_shouldOrderOxygenTanks() {
		OxygenCategories category = OxygenCategories.A;
		LocalDate requestDate = LocalDate.of(2050, 06, 17);
		Integer numberOfDays = 1;
		oxygenTankInventoryService.orderOxygenTanks(category, requestDate, numberOfDays);
		// TODO not sure about this test, should maybe refactor
		verify(repository).setInventory(any());
	}
}
