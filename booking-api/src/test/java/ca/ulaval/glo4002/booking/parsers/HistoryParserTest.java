package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.builders.oxygen.OxygenCategoryBuilder;
import ca.ulaval.glo4002.booking.constants.DateConstants;
import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.domainobjects.report.History;
import ca.ulaval.glo4002.booking.dto.HistoryItemDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HistoryParserTest {

	private HistoryParser subject;

	private static final Long AN_ORDER_TANK_ID = 1L;
	private static final Long ANOTHER_ORDER_TANK_ID = 2L;
	private static final LocalDate FIRST_DATE = DateConstants.START_DATE.minusDays(15);
	private static final LocalDate SECOND_DATE = DateConstants.START_DATE.minusDays(10);
	private static final LocalDate THIRD_DATE = DateConstants.START_DATE.minusDays(5);
	private static final LocalDate FOURTH_DATE = DateConstants.START_DATE;

	private History history;
	private OxygenTank aFirstAndEOxygenTank;
	private OxygenTank aSecondAndBOxygenTank;
	private OxygenTank aThirdAndAOxygenTank;
	private OxygenCategoryBuilder categoryBuilder = new OxygenCategoryBuilder();

	@BeforeEach
	void setUp() {
		subject = new HistoryParser();

		aFirstAndEOxygenTank = new OxygenTank(
				AN_ORDER_TANK_ID,
				categoryBuilder.buildById(OxygenConstants.Categories.E_ID),
				FIRST_DATE
		);

		aSecondAndBOxygenTank = new OxygenTank(
				ANOTHER_ORDER_TANK_ID,
				categoryBuilder.buildById(OxygenConstants.Categories.B_ID),
				FIRST_DATE
		);

		aThirdAndAOxygenTank = new OxygenTank(
				ANOTHER_ORDER_TANK_ID,
				categoryBuilder.buildById(OxygenConstants.Categories.A_ID),
				SECOND_DATE
		);

		history = mock(History.class);

		Map<LocalDate, List<OxygenTank>> requestedOxygenTanks = new HashMap<>();
        requestedOxygenTanks.put(FIRST_DATE, new ArrayList<>(Arrays.asList(aFirstAndEOxygenTank, aSecondAndBOxygenTank)));
		requestedOxygenTanks.put(SECOND_DATE, new ArrayList<>(Collections.singletonList(aThirdAndAOxygenTank)));

		Map<LocalDate, List<OxygenTank>> producedOxygenTanks = new HashMap<>();
		producedOxygenTanks.put(SECOND_DATE, new ArrayList<>(Collections.singletonList(aFirstAndEOxygenTank)));
		producedOxygenTanks.put(THIRD_DATE, new ArrayList<>(Collections.singletonList(aSecondAndBOxygenTank)));
		producedOxygenTanks.put(FOURTH_DATE, new ArrayList<>(Collections.singletonList(aThirdAndAOxygenTank)));

		when(history.getRequestedOxygenTanks()).thenReturn(requestedOxygenTanks);
		when(history.getProducedOxygenTanks()).thenReturn(producedOxygenTanks);
	}

	@Test
	void toDto_shouldReturnValidHistory() {
		List<HistoryItemDto> historyItemDtos = subject.toDto(history);

		assertEquals(4, historyItemDtos.size());
		historyItemDtos.forEach(historyItemDto -> {
			if (FIRST_DATE.toString().equals(historyItemDto.date)) {
				assertEquals(
						aFirstAndEOxygenTank.getCategory().getProduction().getProducedTanks() + aSecondAndBOxygenTank.getCategory().getProduction().getProducedTanks(),
						(long) historyItemDto.qtyOxygenTankBought
				);
				assertEquals(0, (long) historyItemDto.qtyCandlesUsed);
				assertEquals(
						aSecondAndBOxygenTank.getCategory().getProduction().getProducedUnits(),
						historyItemDto.qtyWaterUsed
				);
				assertEquals(0, (long) historyItemDto.qtyOxygenTankMade);
			} else if (SECOND_DATE.toString().equals(historyItemDto.date)) {
				assertEquals(
						aThirdAndAOxygenTank.getCategory().getProduction().getProducedTanks(),
						historyItemDto.qtyOxygenTankBought
				);
				assertEquals(
						aThirdAndAOxygenTank.getCategory().getProduction().getProducedUnits(),
						historyItemDto.qtyCandlesUsed
				);
				assertEquals(0, (long) historyItemDto.qtyWaterUsed);
				assertEquals(aFirstAndEOxygenTank.getCategory().getProduction().getProducedTanks(), historyItemDto.qtyOxygenTankMade);
			} else if (THIRD_DATE.toString().equals(historyItemDto.date)) {
				assertEquals(0, (long) historyItemDto.qtyOxygenTankBought);
				assertEquals(0, (long) historyItemDto.qtyCandlesUsed);
				assertEquals(0, (long) historyItemDto.qtyWaterUsed);
				assertEquals(aSecondAndBOxygenTank.getCategory().getProduction().getProducedTanks(), historyItemDto.qtyOxygenTankMade);
			} else if (FOURTH_DATE.toString().equals(historyItemDto.date)) {
				assertEquals(0, (long) historyItemDto.qtyOxygenTankBought);
				assertEquals(0, (long) historyItemDto.qtyCandlesUsed);
				assertEquals(0, (long) historyItemDto.qtyWaterUsed);
				assertEquals(aThirdAndAOxygenTank.getCategory().getProduction().getProducedTanks(), historyItemDto.qtyOxygenTankMade);
			}
		});
	}

	@Test
	void toDto_shouldReturnHistoryOrderedByDate() {
		List<HistoryItemDto> historyItemDtos = subject.toDto(history);

		assertEquals(historyItemDtos.get(0).date, FIRST_DATE.toString());
		assertEquals(historyItemDtos.get(1).date, SECOND_DATE.toString());
		assertEquals(historyItemDtos.get(2).date, THIRD_DATE.toString());
		assertEquals(historyItemDtos.get(3).date, FOURTH_DATE.toString());
    }

	@Test
	void toDto_shouldReturnEmptyHistory_whenThereIsNoOxygenTank() {
		when(history.getRequestedOxygenTanks()).thenReturn(new HashMap<>());
		when(history.getProducedOxygenTanks()).thenReturn(new HashMap<>());

		List<HistoryItemDto> historyItemDtos = subject.toDto(history);

		assertEquals(0, historyItemDtos.size());
	}
}
