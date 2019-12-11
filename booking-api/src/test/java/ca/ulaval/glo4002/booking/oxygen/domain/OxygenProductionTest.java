package ca.ulaval.glo4002.booking.oxygen.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.oxygen.history.domain.OxygenHistory;
import ca.ulaval.glo4002.booking.profits.domain.Money;

public class OxygenProductionTest {

	private OxygenProduction oxygenProductionA;
	private OxygenProduction oxygenProductionB;
	private OxygenProduction oxygenProductionE;

	private static final LocalDate requestDate = LocalDate.of(2050, 01, 01);

	@BeforeEach
	void setupOxygenCategory() {
		oxygenProductionA = new OxygenProduction(OxygenCategories.A, 3, 20, 5, 15, new Money(BigDecimal.valueOf(650)));
		oxygenProductionB = new OxygenProduction(OxygenCategories.B, 3, 10, 3, 8, new Money(BigDecimal.valueOf(600)));
		oxygenProductionE = new OxygenProduction(OxygenCategories.E, 5, 0, 1, new Money(BigDecimal.valueOf(5000)));
	}

	@Test
	void calculatePriceForCategory_shouldReturnTheCorrectPrice_whenCategoryIsE() {
		Money expectedPrice = new Money(BigDecimal.valueOf(5000));

		Money price = oxygenProductionE.calculatePriceForCategory();

		assertEquals(expectedPrice, price);
	}

	@Test
	void calculatePriceForCategory_shouldReturnTheCorrectPrice_whenCategoryIsB() {
		Money expectedPrice = new Money(BigDecimal.valueOf(600 * 8 / 3));

		Money price = oxygenProductionB.calculatePriceForCategory();

		assertEquals(expectedPrice, price);
	}

	@Test
	void calculatePriceForCategory_shouldReturnTheCorrectPrice_whenCategoryIsA() {
		Money expectedPrice = new Money(BigDecimal.valueOf(650 * 15 / 5));

		Money price = oxygenProductionA.calculatePriceForCategory();

		assertEquals(expectedPrice, price);
	}

	@Test
	void calculateReadyDateForCategory_shouldReturnTheCorrectReadyDate_whenCategoryIsE() {
		LocalDate ReadyDate = oxygenProductionE.calculateReadyDateForCategory(requestDate).getValue();

		assertTrue(requestDate.isEqual(ReadyDate));
	}

	@Test
	void calculateReadyDateForCategory_shouldReturnTheCorrectReadyDate_whenCategoryIsB() {
		LocalDate expectedReadyDate = requestDate.plusDays(10);

		LocalDate ReadyDate = oxygenProductionB.calculateReadyDateForCategory(requestDate).getValue();

		assertTrue(expectedReadyDate.isEqual(ReadyDate));
	}

	@Test
	void calculateReadyDateForCategory_shouldReturnTheCorrectReadyDate_whenCategoryIsA() {
		LocalDate expectedReadyDate = requestDate.plusDays(20);

		LocalDate ReadyDate = oxygenProductionA.calculateReadyDateForCategory(requestDate).getValue();

		assertTrue(expectedReadyDate.isEqual(ReadyDate));
	}

	@Test
	void addCategoryProductionInformationToHistory_shouldAddCandleToHistory_whenCategoryIsA() {
		OxygenHistory history = new OxygenHistory();
		Integer numberOfTanks = 5;

		oxygenProductionA.addCategoryProductionInformationToHistory(requestDate, history, numberOfTanks);

		assertEquals(history.getHistoryItems().get(requestDate).getQtyCandlesUsed(),
				OxygenTank.CATEGORY_A_NUMBER_OF_RESOURCES_NEEDED);
	}

	@Test
	void addCategoryProductionInformationToHistory_shouldAddWaterToHistory_whenCategoryIsB() {
		OxygenHistory history = new OxygenHistory();
		Integer numberOfTanks = 3;

		oxygenProductionB.addCategoryProductionInformationToHistory(requestDate, history, numberOfTanks);

		assertEquals(history.getHistoryItems().get(requestDate).getQtyWaterUsed(),
				OxygenTank.CATEGORY_B_NUMBER_OF_RESOURCES_NEEDED);
	}
}