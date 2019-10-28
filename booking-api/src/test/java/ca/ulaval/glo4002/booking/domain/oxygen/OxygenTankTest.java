package ca.ulaval.glo4002.booking.domain.oxygen;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.enums.OxygenCategory;

class OxygenTankTest {

	private OxygenDate requestDate;

	@BeforeEach
	void initTest() {
		requestDate = new OxygenDate(LocalDate.of(2050, 7, 1));
	}

	@Test
	void getId_shouldReturnId() {
		OxygenTank oxygen = new OxygenTank(1L, OxygenCategory.A, requestDate);

		assertEquals(1L, oxygen.getId());
	}

	@Test
	void getCategoryOxygenCategoryA_shouldReturnCategoryA() {
		OxygenTank oxygenA = new OxygenTank(OxygenCategory.A, requestDate);

		assertEquals(OxygenCategory.A, oxygenA.getCategory());
	}

	@Test
	void getCategoryOxygenCategoryB_shouldReturnCategoryB() {
		OxygenTank oxygenB = new OxygenTank(OxygenCategory.B, requestDate);

		assertEquals(OxygenCategory.B, oxygenB.getCategory());
	}

	@Test
	void getCategoryOxygenCategoryE_shouldReturnCategoryE() {
		OxygenTank oxygenE = new OxygenTank(OxygenCategory.E, requestDate);

		assertEquals(OxygenCategory.E, oxygenE.getCategory());
	}

	@Test
	void getReadyDateOxygenCategoryA_shouldReturn20DaysLater() {
		OxygenDate expectedDate = this.requestDate;
		expectedDate.addDays(20L);

		OxygenTank oxygenA = new OxygenTank(OxygenCategory.A, requestDate);

		assertEquals(expectedDate, oxygenA.getReadyDate());
	}

	@Test
	void getReadyDateOxygenCategoryB_shouldReturn10DaysLater() {
		OxygenDate expectedDate = this.requestDate;
		expectedDate.addDays(10L);

		OxygenTank oxygenB = new OxygenTank(OxygenCategory.B, requestDate);

		assertEquals(expectedDate, oxygenB.getReadyDate());
	}

	@Test
	void getReadyDateOxygenCategoryE_shouldReturnSameDay() {
		OxygenTank oxygenE = new OxygenTank(OxygenCategory.E, requestDate);

		assertEquals(oxygenE.getReadyDate(), this.requestDate);
	}

	@Test
	void getMoneyOfOxygenCategoryA_shouldReturnMoneyBigDecimal1950() {
		BigDecimal tankPrice = new BigDecimal(1950);
		Money expectedMoney = new Money(tankPrice);

		OxygenTank oxygenA = new OxygenTank(OxygenCategory.A, requestDate);

		assertEquals(expectedMoney, oxygenA.getMoney());
	}

	@Test
	void getMoneyOfOxygenCategoryB_shouldReturnMoneyBigDecimal1600() {
		BigDecimal tankPrice = new BigDecimal(1600);
		Money expectedMoney = new Money(tankPrice);

		OxygenTank oxygenB = new OxygenTank(OxygenCategory.B, requestDate);

		assertEquals(expectedMoney, oxygenB.getMoney());
	}

	@Test
	void getMoneyOfOxygenCategoryE_shouldReturnMoneyBigDecimal5000() {
		BigDecimal tankPrice = new BigDecimal(5000);
		Money expectedMoney = new Money(tankPrice);

		OxygenTank oxygenE = new OxygenTank(OxygenCategory.E, requestDate);

		assertEquals(expectedMoney, oxygenE.getMoney());
	}

}
