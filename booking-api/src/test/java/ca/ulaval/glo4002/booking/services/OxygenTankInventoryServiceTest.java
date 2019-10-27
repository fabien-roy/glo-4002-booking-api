package ca.ulaval.glo4002.booking.services;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.factories.OxygenTankFactory;
import ca.ulaval.glo4002.booking.repositories.OxygenTankInventoryRepository;

class OxygenTankInventoryServiceTest {

	private OxygenTankInventoryService subject;

	@BeforeEach
	void setUpSubject() {
		OxygenTankInventoryRepository repository = mock(OxygenTankInventoryRepository.class);
		OxygenTankFactory factory = mock(OxygenTankFactory.class);

		subject = new OxygenTankInventoryService(repository, factory);
	}

	@Test
	void buildOxygenTank_shouldBuildTheOxygenTank() {
		// TODO Fail this test then the the method and pass the test
	}
}
