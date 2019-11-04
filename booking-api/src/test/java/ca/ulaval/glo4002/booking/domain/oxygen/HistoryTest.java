package ca.ulaval.glo4002.booking.domain.oxygen;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HistoryTest {

	private History history;
	private List<OxygenTank> aOxygenTankList;
	private List<OxygenTank> anOtherOxygenTankList;
	private List<OxygenTank> aThirdOxygenTankList;
	private LocalDate aRequestDate = LocalDate.of(2050, 7, 1);
	private LocalDate anOtherRequestDate = LocalDate.of(2050, 6, 22);
	private LocalDate aProducedDate = LocalDate.of(2050, 7, 10);

	@BeforeEach
	void setupHistory() {
		setUpOxygenList();
		Map<LocalDate, List<OxygenTank>> requestedOxygenTanks = new HashMap<LocalDate, List<OxygenTank>>();
		Map<LocalDate, List<OxygenTank>> producedOxygenTanks = new HashMap<LocalDate, List<OxygenTank>>();
		requestedOxygenTanks.put(aRequestDate, aOxygenTankList);
		requestedOxygenTanks.put(anOtherRequestDate, anOtherOxygenTankList);
		producedOxygenTanks.put(aProducedDate, aThirdOxygenTankList);
		history = new History(requestedOxygenTanks, producedOxygenTanks);
	}

	private void setUpOxygenList() {
		OxygenTank tank = mock(OxygenTank.class);
		aOxygenTankList.add(tank);
		aOxygenTankList.add(tank);
		aOxygenTankList.add(tank);
		anOtherOxygenTankList.add(tank);
		anOtherOxygenTankList.add(tank);
		aThirdOxygenTankList.add(tank);
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
