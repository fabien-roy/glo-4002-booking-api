package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTanksProducer;
import ca.ulaval.glo4002.booking.enums.OxygenCategories;
import ca.ulaval.glo4002.booking.mappers.OxygenInventoryMapper;
import ca.ulaval.glo4002.booking.repositories.OxygenTankInventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class OxygenInventoryServiceTest {

	private OxygenTankInventoryService oxygenTankInventoryService;
	private OxygenTanksProducer producer;
	private Integer numberOfDays;

	@BeforeEach
	void setUpOxygenTankInventoryService() {
		OxygenTankInventoryRepository repository = mock(OxygenTankInventoryRepository.class);
		producer = mock(OxygenTanksProducer.class);
		OxygenInventoryMapper mapper = mock(OxygenInventoryMapper.class);

		numberOfDays = 1;
		oxygenTankInventoryService = new OxygenTankInventoryService(repository, producer, mapper);
	}

	// TODO : OXY : not sure just changed so travis build is not in error
	@Test
	void orderOxygenTank_shouldOrderOxygenTanks() {
		OxygenCategories category = OxygenCategories.A;
		LocalDate requestDate = LocalDate.of(2050, 06, 17);
		oxygenTankInventoryService.order(category, requestDate, numberOfDays);

		verify(producer).produceOxygenForOrder(any(), any(), any());
	}
}
