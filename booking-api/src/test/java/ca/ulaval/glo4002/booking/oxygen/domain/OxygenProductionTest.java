package ca.ulaval.glo4002.booking.oxygen.domain;

import ca.ulaval.glo4002.booking.profits.domain.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
		BigDecimal expectedPriceValue = BigDecimal.valueOf(5000);
		Money expectedPrice = new Money(expectedPriceValue);

		Money price = oxygenProductionE.calculatePriceForCategory();

		assertEquals(expectedPrice, price);
	}

	@Test
	void calculatePriceForCategory_shouldReturnTheCorrectPrice_whenCategoryIsB() {
		BigDecimal expectedPriceValue = BigDecimal.valueOf(1600); // 600 * 8 / 3
		Money expectedPrice = new Money(expectedPriceValue);

		Money price = oxygenProductionB.calculatePriceForCategory();

		assertEquals(expectedPrice, price);
	}

	@Test
	void calculatePriceForCategory_shouldReturnTheCorrectPrice_whenCategoryIsA() {
		BigDecimal expectedPriceValue = BigDecimal.valueOf(1950); // 650 * 15 / 5
		Money expectedPrice = new Money(expectedPriceValue);

		Money price = oxygenProductionA.calculatePriceForCategory();

		assertEquals(expectedPrice, price);
	}

	@Test
	void calculateReadyDateForCategory_shouldReturnTheCorrectReadyDate_whenCategoryIsE() {
		int expectedNumberOfDaysToProduce = 0;
		LocalDate expectedReadyDate = requestDate.plusDays(expectedNumberOfDaysToProduce);

		LocalDate readyDate = oxygenProductionE.calculateReadyDateForCategory(requestDate);

		assertEquals(expectedReadyDate, readyDate);
	}

	@Test
	void calculateReadyDateForCategory_shouldReturnTheCorrectReadyDate_whenCategoryIsB() {
		int expectedNumberOfDaysToProduce = 10;
		LocalDate expectedReadyDate = requestDate.plusDays(expectedNumberOfDaysToProduce);

		LocalDate readyDate = oxygenProductionB.calculateReadyDateForCategory(requestDate);

		assertEquals(expectedReadyDate, readyDate);
	}

	@Test
	void calculateReadyDateForCategory_shouldReturnTheCorrectReadyDate_whenCategoryIsA() {
		int expectedNumberOfDaysToProduce = 20;
		LocalDate expectedReadyDate = requestDate.plusDays(expectedNumberOfDaysToProduce);

		LocalDate readyDate = oxygenProductionA.calculateReadyDateForCategory(requestDate);

		assertEquals(expectedReadyDate, readyDate);
	}
}