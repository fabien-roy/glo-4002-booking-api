package ca.ulaval.glo4002.booking.services;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.enums.OxygenCategories;
import ca.ulaval.glo4002.booking.factories.OxygenTankFactory;
import ca.ulaval.glo4002.booking.mappers.OxygenTankInventoryMapper;
import ca.ulaval.glo4002.booking.repositories.OxygenTankInventoryRepository;

class OxygenTankInventoryServiceTest {

	private OxygenTankInventoryService oxygenTankInventoryService;
	private OxygenTankInventoryRepository repository;

	@BeforeEach
	void setUpSubject() {
		repository = mock(OxygenTankInventoryRepository.class);
		OxygenTankFactory factory = mock(OxygenTankFactory.class);
		OxygenTankInventoryMapper mapper = mock(OxygenTankInventoryMapper.class);

		oxygenTankInventoryService = new OxygenTankInventoryService(repository, factory, mapper);
	}

	@Test
	void orderOxygenTank_shouldOrderOxygenTanks() {
		OxygenCategories category = OxygenCategories.A;
		LocalDate requestDate = LocalDate.of(2050, 06, 17);
		Long numberOfDays = 1L;
		oxygenTankInventoryService.orderOxygenTanks(category, requestDate, numberOfDays);
		// TODO not sure about this test, should maybe refactor
		verify(repository).setInventory(any());
	}
}
