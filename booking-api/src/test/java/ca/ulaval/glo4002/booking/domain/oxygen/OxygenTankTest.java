package ca.ulaval.glo4002.booking.domain.oxygen;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.enums.OxygenTankCategory;

class OxygenTankTest {

	OxygenTank oxygenA;
	OxygenTank oxygenB;
	OxygenTank oxygenE;
	OxygenDate requestDate;

	@BeforeEach
	void initTest() {
		requestDate = new OxygenDate(LocalDate.of(2050, 7, 1));
		this.oxygenA = new OxygenTank(1L, OxygenTankCategory.CATEGORY_A, requestDate);
		this.oxygenB = new OxygenTank(OxygenTankCategory.CATEGORY_B, requestDate);
		this.oxygenE = new OxygenTank(OxygenTankCategory.CATEGORY_E, requestDate);
	}

	@Test
	void getId_shouldReturnId() {
		assertEquals(1L, this.oxygenA.getId());
	}

	@Test
	void getCategoryOxygenCategoryA_shouldReturnCategoryA() {
		assertEquals(OxygenTankCategory.CATEGORY_A, this.oxygenA.getCategory());
	}

	@Test
	void getCategoryOxygenCategoryB_shouldReturnCategoryB() {
		assertEquals(OxygenTankCategory.CATEGORY_B, this.oxygenB.getCategory());
	}

	@Test
	void getCategoryOxygenCategoryE_shouldReturnCategoryE() {
		assertEquals(OxygenTankCategory.CATEGORY_E, this.oxygenE.getCategory());
	}

	@Test
	void getReadyDateOxygenCategoryA_shouldReturn20DaysLater() {
		OxygenDate expectedDate = this.requestDate;
		expectedDate.addDays(20L);

		assertEquals(expectedDate, this.oxygenA.getReadyDate());
	}

	@Test
	void getReadyDateOxygenCategoryB_shouldReturn10DaysLater() {
		OxygenDate expectedDate = this.requestDate;
		expectedDate.addDays(10L);

		assertEquals(expectedDate, this.oxygenB.getReadyDate());
	}

	@Test
	void getReadyDateOxygenCategoryE_shouldReturnSameDay() {
		assertEquals(this.oxygenE.getReadyDate(), this.requestDate);
	}

	@Test
	void getMoneyOfOxygenCategoryA_shouldReturnMoneyBigDecimal1950() {
		// TODO THIS TEST
	}

	@Test
	void getMoneyOfOxygenCategoryB_shouldReturnMoneyBigDecimal1600() {
		// TODO THIS TEST
	}

	@Test
	void getMoneyOfOxygenCategoryE_shouldReturnMoneyBigDecimal5000() {
		// TODO THIS TEST
	}

}
