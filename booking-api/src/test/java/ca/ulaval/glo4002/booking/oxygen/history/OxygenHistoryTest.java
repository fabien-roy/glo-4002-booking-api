package ca.ulaval.glo4002.booking.oxygen.history;

import ca.ulaval.glo4002.booking.oxygen.OxygenTank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;

class OxygenHistoryTest {

	private OxygenHistory oxygenHistory;
	private List<OxygenTank> anOxygenTankList;
	private List<OxygenTank> anOtherOxygenTankList;
	private List<OxygenTank> aThirdOxygenTankList;
	private LocalDate aRequestDate = LocalDate.of(2050, 7, 1);
	private LocalDate anOtherRequestDate = LocalDate.of(2050, 6, 22);
	private LocalDate aProducedDate = LocalDate.of(2050, 7, 10);
	private Map<LocalDate, List<OxygenTank>> requestedOxygenTanks;
	private Map<LocalDate, List<OxygenTank>> producedOxygenTanks;

	@BeforeEach
	void setupHistory() {
		setUpOxygenList();
		requestedOxygenTanks = new HashMap<>();
		producedOxygenTanks = new HashMap<>();
		requestedOxygenTanks.put(aRequestDate, anOxygenTankList);
		requestedOxygenTanks.put(anOtherRequestDate, anOtherOxygenTankList);
		producedOxygenTanks.put(aProducedDate, aThirdOxygenTankList);
		//oxygenHistory = new OxygenHistory(requestedOxygenTanks, producedOxygenTanks);
	}

	private void setUpOxygenList() {
		anOxygenTankList = new ArrayList<>();
		anOtherOxygenTankList = new ArrayList<>();
		aThirdOxygenTankList = new ArrayList<>();
		OxygenTank tank = mock(OxygenTank.class);
		anOxygenTankList.add(tank);
		anOxygenTankList.add(tank);
		anOxygenTankList.add(tank);
		anOtherOxygenTankList.add(tank);
		anOtherOxygenTankList.add(tank);
		aThirdOxygenTankList.add(tank);
	}

	@Test
	void constructing_shouldSetNoHistoryItems_whenThereIsNoOxygenTank() {
		// TODO
	}

	@Test
	void constructing_shouldSetHistoryItemsForDate() {
		// TODO
	}

	@Test
	void constructing_shouldSetHistoryItemsForDates_whenThereAreMultipleDates() {
		// TODO
	}

/*	@Test
	void constructing_shouldSetRequestedOxygenTanks() {
		oxygenHistory = new OxygenHistory();

		assertEquals(0, oxygenHistory.getRequestedOxygenTanks().size());
	}

	@Test
	void constructing_shouldSetProducedOxygenTanks() {
		oxygenHistory = new OxygenHistory();

		assertEquals(0, oxygenHistory.getProducedOxygenTanks().size());
	}

	@Test
	void getRequestedOxygenTank_shouldReturnMapForRequestedOxygenTanks() {
		assertEquals(oxygenHistory.getRequestedOxygenTanks(), requestedOxygenTanks);
	}

	@Test
	void getProducedOxygenTank_shouldReturnMapForProducedOxygenTanks() {
		assertEquals(oxygenHistory.getProducedOxygenTanks(), producedOxygenTanks);
	}

	@Test
	void getRequestedOxygenTanksForDate_shouldReturnListRequestedOxygenTanksForDate() {
		assertEquals(oxygenHistory.getRequestedOxygenTanksForDate(aRequestDate), requestedOxygenTanks.get(aRequestDate));
	}

	@Test
	void getProducedOxygenTanksForDate_shouldReturnListProducedOxygenTanksForDate() {
		assertEquals(oxygenHistory.getProducedOxygenTanksForDate(aProducedDate), producedOxygenTanks.get(aProducedDate));
	}

	@Test
	void addRequestedTankToHistory_shouldAddToResquestedTank() {
		oxygenHistory.addRequestedTankToHistory(aProducedDate, aThirdOxygenTankList);

		assertEquals(aThirdOxygenTankList, oxygenHistory.getRequestedOxygenTanksForDate(aProducedDate));
	}

	@Test
	void addProducedTankToHistory_shouldAddToProducedTank() {
		oxygenHistory.addProducedTankToHistory(aRequestDate, anOxygenTankList);

		assertEquals(anOxygenTankList, oxygenHistory.getRequestedOxygenTanksForDate(aRequestDate));
	}*/
}
