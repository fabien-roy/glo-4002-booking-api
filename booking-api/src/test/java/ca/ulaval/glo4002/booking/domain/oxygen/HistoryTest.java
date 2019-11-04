package ca.ulaval.glo4002.booking.domain.oxygen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HistoryTest {

	private History history;

	@BeforeEach
	void setupHistory() {
	}

	@Test
	void constructing_shouldSetRequestedOxygenTanks() {
		history = new History();
		assertTrue(history.getRequestedOxygenTanks().size() == 0);
	}

	@Test
	void constructing_shouldSetProducedOxygenTankss() {
		history = new History();
		assertTrue(history.getProducedOxygenTanks().size() == 0);
	}

	@Test
	void getRequestedOxygenTank_shouldReturnMapForRequestedOxygenTanks() {
	}

	@Test
	void getRequestedOxygenTank_shouldReturnMapForProducedOxygenTanks() {
	}

	@Test
	void getRequestedOxygenTanksForDate_shouldReturnListRequestedOxygenTanksForDate() {
	}

	@Test
	void getProducedOxygenTanksForDate_shouldReturnListProducedOxygenTanksForDate() {
	}

	@Test
	void addRequestedTankToHistory_shouldAddToResquestedTank() {
	}

	@Test
	void addProducedTankToHistory_shouldAddToProducedTank() {
	}
}
