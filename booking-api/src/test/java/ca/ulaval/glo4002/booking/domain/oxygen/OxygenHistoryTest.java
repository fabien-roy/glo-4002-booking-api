package ca.ulaval.glo4002.booking.domain.oxygen;

import ca.ulaval.glo4002.booking.enums.OxygenCategories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
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

	// TODO : To delete only used to map the process in my head
	@Test
	void randomTest() {
		OxygenDate firstRequestDate = new OxygenDate(aRequestDate);
		OxygenDate secondRequestDate = new OxygenDate(anOtherRequestDate);
		OxygenDate thirdRequestDate = new OxygenDate(aProducedDate);

		OxygenTank tankA1 = new OxygenTank(OxygenCategories.A, firstRequestDate);
		OxygenTank tankB1 = new OxygenTank(OxygenCategories.B, firstRequestDate);
		OxygenTank tankE1 = new OxygenTank(OxygenCategories.E, firstRequestDate);
		OxygenTank tankA2 = new OxygenTank(OxygenCategories.A, secondRequestDate);
		OxygenTank tankB2 = new OxygenTank(OxygenCategories.B, secondRequestDate);
		OxygenTank tankE2 = new OxygenTank(OxygenCategories.E, secondRequestDate);
		OxygenTank tankA3 = new OxygenTank(OxygenCategories.A, thirdRequestDate);
		OxygenTank tankB3 = new OxygenTank(OxygenCategories.B, thirdRequestDate);
		OxygenTank tankE3 = new OxygenTank(OxygenCategories.E, thirdRequestDate);

		OxygenInventory inventory = new OxygenInventory();

		inventory.addTanksToInventory(OxygenCategories.A, Collections.nCopies(10, tankA1));
		inventory.addTanksToInventory(OxygenCategories.B, Collections.nCopies(9, tankB1));
		inventory.addTanksToInventory(OxygenCategories.E, Collections.nCopies(30, tankE1));
		inventory.addTanksToInventory(OxygenCategories.A, Collections.nCopies(15, tankA2));
		inventory.addTanksToInventory(OxygenCategories.B, Collections.nCopies(6, tankB2));
		inventory.addTanksToInventory(OxygenCategories.E, Collections.nCopies(20, tankE2));
		inventory.addTanksToInventory(OxygenCategories.A, Collections.nCopies(25, tankA3));
		inventory.addTanksToInventory(OxygenCategories.B, Collections.nCopies(18, tankB3));
		inventory.addTanksToInventory(OxygenCategories.E, Collections.nCopies(5, tankE3));

		OxygenHistory history = new OxygenHistory(inventory.getAllTanks());

		System.out.println(history);
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
