package ca.ulaval.glo4002.booking.domain.oxygen;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.enums.OxygenTankCategory;

class OxygenTankTest {

	private OxygenTank oxygenA;
	private OxygenTank oxygenB;
	private OxygenTank oxygenE;
	private OxygenDate requestDate;

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
		BigDecimal tankPrice = new BigDecimal(1950);
		Money expectedMoney = new Money(tankPrice);

		assertEquals(expectedMoney, this.oxygenA.getMoney());
	}

	@Test
	void getMoneyOfOxygenCategoryB_shouldReturnMoneyBigDecimal1600() {
		BigDecimal tankPrice = new BigDecimal(1600);
		Money expectedMoney = new Money(tankPrice);

		assertEquals(expectedMoney, this.oxygenB.getMoney());
	}

	@Test
	void getMoneyOfOxygenCategoryE_shouldReturnMoneyBigDecimal5000() {
		BigDecimal tankPrice = new BigDecimal(5000);
		Money expectedMoney = new Money(tankPrice);

		assertEquals(expectedMoney, this.oxygenE.getMoney());
	}

}
