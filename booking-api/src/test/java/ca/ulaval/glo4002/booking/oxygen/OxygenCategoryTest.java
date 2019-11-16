package ca.ulaval.glo4002.booking.oxygen;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.oxygen.history.OxygenHistory;
import ca.ulaval.glo4002.booking.profits.Money;

public class OxygenCategoryTest {

	private OxygenCategory oxygenCategoryA;
	private OxygenCategory oxygenCategoryB;
	private OxygenCategory oxygenCategoryE;

	private static final LocalDate requestDate = LocalDate.of(2050, 01, 01);

	@BeforeEach
	void setupOxygenCategory() {
		oxygenCategoryA = new OxygenCategory(OxygenCategories.A, 3, 20, 5, 15, new Money(BigDecimal.valueOf(650)));
		oxygenCategoryB = new OxygenCategory(OxygenCategories.B, 3, 10, 3, 8, new Money(BigDecimal.valueOf(600)));
		oxygenCategoryE = new OxygenCategory(OxygenCategories.E, 5, 0, 1, new Money(BigDecimal.valueOf(5000)));
	}

	@Test
	void calculatePriceForCategory_shouldReturnTheCorrectPrice_whenCategoryIsE() {
		Money price = new Money(BigDecimal.valueOf(5000));

		assertEquals(price, oxygenCategoryE.calculatePriceForCategory());
	}

	@Test
	void calculatePriceForCategory_shouldReturnTheCorrectPrice_whenCategoryIsB() {
		Money price = new Money(BigDecimal.valueOf(600 * 8 / 3));

		assertEquals(price.getValue(), oxygenCategoryB.calculatePriceForCategory().getValue());
	}

	@Test
	void calculatePriceForCategory_shouldReturnTheCorrectPrice_whenCategoryIsA() {
		Money price = new Money(BigDecimal.valueOf(650 * 15 / 5));

		assertEquals(price.getValue(), oxygenCategoryA.calculatePriceForCategory().getValue());
	}

	@Test
	void calculateReadyDateForCategory_shouldReturnTheCorrectReadyDate_whenCategoryIsE() {
		assertTrue(requestDate.isEqual(oxygenCategoryE.calculateReadyDateForCategory(requestDate).getValue()));
	}

	@Test
	void calculateReadyDateForCategory_shouldReturnTheCorrectReadyDate_whenCategoryIsB() {
		LocalDate readyDate = requestDate.plusDays(10);

		assertTrue(readyDate.isEqual(oxygenCategoryB.calculateReadyDateForCategory(requestDate).getValue()));
	}

	@Test
	void calculateReadyDateForCategory_shouldReturnTheCorrectReadyDate_whenCategoryIsA() {
		LocalDate readyDate = requestDate.plusDays(20);

		assertTrue(readyDate.isEqual(oxygenCategoryA.calculateReadyDateForCategory(requestDate).getValue()));
	}

	@Test
	void addCategoryProductionInformationToHistory_shouldAddCandleToHistory_whenCategoryIsA() {
		OxygenHistory history = new OxygenHistory();
		Integer numberOfTanks = 5;

		oxygenCategoryA.addCategoryProductionInformationToHistory(requestDate, history, numberOfTanks);

		assertEquals(history.getHistoryItems().get(requestDate).getQtyCandlesUsed(),
				OxygenTank.CATEGORY_A_NUMBER_OF_RESOURCES_NEEDED);
	}

	@Test
	void addCategoryProductionInformationToHistory_shouldAddWaterToHistory_whenCategoryIsB() {
		OxygenHistory history = new OxygenHistory();
		Integer numberOfTanks = 3;

		oxygenCategoryB.addCategoryProductionInformationToHistory(requestDate, history, numberOfTanks);

		assertEquals(history.getHistoryItems().get(requestDate).getQtyWaterUsed(),
				OxygenTank.CATEGORY_B_NUMBER_OF_RESOURCES_NEEDED);
	}
}