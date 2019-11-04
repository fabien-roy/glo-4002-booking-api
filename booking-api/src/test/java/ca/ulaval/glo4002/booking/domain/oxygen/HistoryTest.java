package ca.ulaval.glo4002.booking.domain.oxygen;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HistoryTest {

	private History history;
	private List<OxygenTank> anOxygenTankList;
	private List<OxygenTank> anOtherOxygenTankList;
	private List<OxygenTank> aThirdOxygenTankList;
	private LocalDate aRequestDate = LocalDate.of(2050, 7, 1);
	private LocalDate anOtherRequestDate = LocalDate.of(2050, 6, 22);
	private LocalDate aProducedDate = LocalDate.of(2050, 7, 10);
	Map<LocalDate, List<OxygenTank>> requestedOxygenTanks;
	Map<LocalDate, List<OxygenTank>> producedOxygenTanks;

	@BeforeEach
	void setupHistory() {
		setUpOxygenList();
		requestedOxygenTanks = new HashMap<LocalDate, List<OxygenTank>>();
		producedOxygenTanks = new HashMap<LocalDate, List<OxygenTank>>();
		requestedOxygenTanks.put(aRequestDate, anOxygenTankList);
		requestedOxygenTanks.put(anOtherRequestDate, anOtherOxygenTankList);
		producedOxygenTanks.put(aProducedDate, aThirdOxygenTankList);
		history = new History(requestedOxygenTanks, producedOxygenTanks);
	}

	private void setUpOxygenList() {
		anOxygenTankList = new ArrayList<OxygenTank>();
		anOtherOxygenTankList = new ArrayList<OxygenTank>();
		aThirdOxygenTankList = new ArrayList<OxygenTank>();
		OxygenTank tank = mock(OxygenTank.class);
		anOxygenTankList.add(tank);
		anOxygenTankList.add(tank);
		anOxygenTankList.add(tank);
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
		assertEquals(history.getRequestedOxygenTanks(), requestedOxygenTanks);
	}

	@Test
	void getProducedOxygenTank_shouldReturnMapForProducedOxygenTanks() {
		assertEquals(history.getProducedOxygenTanks(), producedOxygenTanks);
	}

	@Test
	void getRequestedOxygenTanksForDate_shouldReturnListRequestedOxygenTanksForDate() {
		assertEquals(history.getRequestedOxygenTanksForDate(aRequestDate), requestedOxygenTanks.get(aRequestDate));
	}

	@Test
	void getProducedOxygenTanksForDate_shouldReturnListProducedOxygenTanksForDate() {
		assertEquals(history.getProducedOxygenTanksForDate(aProducedDate), producedOxygenTanks.get(aProducedDate));
	}

	@Test
	void addRequestedTankToHistory_shouldAddToResquestedTank() {
		history.addRequestedTankToHistory(aProducedDate, aThirdOxygenTankList);
		assertEquals(aThirdOxygenTankList, history.getRequestedOxygenTanksForDate(aProducedDate));
	}

	@Test
	void addProducedTankToHistory_shouldAddToProducedTank() {
		history.addProducedTankToHistory(aRequestDate, anOxygenTankList);
		assertEquals(anOxygenTankList, history.getRequestedOxygenTanksForDate(aRequestDate));
	}
}
