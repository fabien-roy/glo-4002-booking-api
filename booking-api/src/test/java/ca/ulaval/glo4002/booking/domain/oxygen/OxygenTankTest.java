package ca.ulaval.glo4002.booking.domain.oxygen;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.enums.OxygenCategory;

class OxygenTankTest {

    private OxygenTank subject;
	private OxygenDate requestDate;

	@BeforeEach
	void setUpRequestDate() {
		requestDate = new OxygenDate(LocalDate.of(2050, 7, 1));
	}

	@Test
	void getCategoryOxygenCategoryA_shouldReturnCategoryA() {
		subject = new OxygenTank(OxygenCategory.A, requestDate);

		assertEquals(OxygenCategory.A, subject.getCategory());
	}

	@Test
	void getCategoryOxygenCategoryB_shouldReturnCategoryB() {
		subject = new OxygenTank(OxygenCategory.B, requestDate);

		assertEquals(OxygenCategory.B, subject.getCategory());
	}

	@Test
	void getCategoryOxygenCategoryE_shouldReturnCategoryE() {
		subject = new OxygenTank(OxygenCategory.E, requestDate);

		assertEquals(OxygenCategory.E, subject.getCategory());
	}

	@Test
	void getReadyDateOxygenCategoryA_shouldReturn20DaysLater() {
		OxygenDate expectedDate = requestDate;
		expectedDate.addDays(20);

		subject = new OxygenTank(OxygenCategory.A, requestDate);

		assertEquals(expectedDate, subject.getReadyDate());
	}

	@Test
	void getReadyDateOxygenCategoryB_shouldReturn10DaysLater() {
		OxygenDate expectedDate = requestDate;
		expectedDate.addDays(10);

		subject = new OxygenTank(OxygenCategory.B, requestDate);

		assertEquals(expectedDate, subject.getReadyDate());
	}

	@Test
	void getReadyDateOxygenCategoryE_shouldReturnSameDay() {
		subject = new OxygenTank(OxygenCategory.E, requestDate);

		assertEquals(requestDate, subject.getReadyDate());
	}

	@Test
	void getMoneyOfOxygenCategoryA_shouldReturnCorrectPrice() {
		BigDecimal tankPrice = new BigDecimal((OxygenTank.CATEGORY_A_NUMBER_OF_RESOURCES_NEEDED * OxygenTank.CATEGORY_A_RESOURCE_PRICE) / OxygenTank.CATEGORY_A_NUMBER_OF_TANKS_CREATED);
		Money expectedPrice = new Money(tankPrice);

		subject = new OxygenTank(OxygenCategory.A, requestDate);

		assertEquals(expectedPrice, subject.getPrice());
	}

	@Test
	void getMoneyOfOxygenCategoryB_shouldReturnCorrectPrice() {
		BigDecimal tankPrice = new BigDecimal((OxygenTank.CATEGORY_B_NUMBER_OF_RESOURCES_NEEDED * OxygenTank.CATEGORY_B_RESOURCE_PRICE) / OxygenTank.CATEGORY_B_NUMBER_OF_TANKS_CREATED);
		Money expectedPrice = new Money(tankPrice);

		subject = new OxygenTank(OxygenCategory.B, requestDate);

		assertEquals(expectedPrice, subject.getPrice());
	}

	@Test
	void getMoneyOfOxygenCategoryE_shouldReturnCorrectPrice() {
		BigDecimal tankPrice = new BigDecimal((OxygenTank.CATEGORY_E_NUMBER_OF_RESOURCES_NEEDED * OxygenTank.CATEGORY_E_RESOURCE_PRICE) / OxygenTank.CATEGORY_E_NUMBER_OF_TANKS_CREATED);
		Money expectedPrice = new Money(tankPrice);

		OxygenTank oxygenE = new OxygenTank(OxygenCategory.E, requestDate);

		assertEquals(expectedPrice, oxygenE.getPrice());
	}

}
