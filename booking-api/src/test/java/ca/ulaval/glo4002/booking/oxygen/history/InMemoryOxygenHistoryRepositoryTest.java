package ca.ulaval.glo4002.booking.oxygen.history;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryOxygenHistoryRepositoryTest {

	private OxygenHistoryRepository repository;

	@BeforeEach
	void setUpRepository() {
		repository = new InMemoryOxygenHistoryRepository();
	}

	@Test
	void setHistory_shouldSetHistory() {
		OxygenHistory expectedHistory = new OxygenHistory();

		repository.setHistory(expectedHistory);
		OxygenHistory history = repository.getHistory();

		assertEquals(expectedHistory, history);
	}
}