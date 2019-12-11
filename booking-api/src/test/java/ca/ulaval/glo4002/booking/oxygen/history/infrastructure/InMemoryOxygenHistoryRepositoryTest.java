package ca.ulaval.glo4002.booking.oxygen.history.infrastructure;

import ca.ulaval.glo4002.booking.oxygen.history.domain.OxygenHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryOxygenHistoryRepositoryTest {

	private OxygenHistoryRepository repository;

	@BeforeEach
	void setUpRepository() {
		repository = new InMemoryOxygenHistoryRepository();
	}

	@Test
	void updateHistory_shouldUpdateHistory() {
		OxygenHistory expectedHistory = new OxygenHistory();

		repository.updateHistory(expectedHistory);
		OxygenHistory history = repository.getHistory();

		assertEquals(expectedHistory, history);
	}
}