package ca.ulaval.glo4002.booking.oxygen;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.time.LocalDate;

import ca.ulaval.glo4002.booking.configuration.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.passes.PassCategories;
import ca.ulaval.glo4002.booking.profits.Money;

class OxygenTankTest {


	private OxygenTank oxygenTank;
	private OxygenDate requestDate;
	private OxygenCategory categoryA;
	private OxygenCategory categoryB;
	private OxygenCategory categoryE;

	@BeforeEach
	void setUpRequestDate() {
		requestDate = new OxygenDate(LocalDate.of(2050, 7, 1));

		Configuration configuration = mock(Configuration.class);
		OxygenFactory factory = new OxygenFactory(configuration);

		categoryA = factory.buildCategory(PassCategories.NEBULA);
		categoryB = factory.buildCategory(PassCategories.SUPERGIANT);
		categoryE = factory.buildCategory(PassCategories.SUPERNOVA);
	}

	@Test
	void getCategory_shouldReturnCategoryA_whenOxygenCategoryA() {
		oxygenTank = new OxygenTank(categoryA, requestDate);

		assertEquals(categoryA, oxygenTank.getCategory());
	}

	@Test
	void getCategory_shouldReturnCategoryB_whenOxygenCategoryB() {
		oxygenTank = new OxygenTank(categoryB, requestDate);

		assertEquals(categoryB, oxygenTank.getCategory());
	}

	@Test
	void getCategory_shouldReturnCategoryE_whenOxygenCategory() {
		oxygenTank = new OxygenTank(categoryE, requestDate);

		assertEquals(categoryE, oxygenTank.getCategory());
	}

	@Test
	void getRequestDate_shouldReturnCorrectRequestDate() {
		oxygenTank = new OxygenTank(categoryA, requestDate);

		assertEquals(requestDate, oxygenTank.getRequestDate());
	}

	@Test
	void getReadyDate_shouldReturn20DaysLaterDate_whenOxygenCategoryA() {
		OxygenDate expectedDate = new OxygenDate(requestDate.getValue());
		expectedDate.addDays(20);

		oxygenTank = new OxygenTank(categoryA, requestDate);

		assertEquals(expectedDate.toString(), oxygenTank.getReadyDate().toString());
	}

	@Test
	void getReadyDate_shouldReturn10DaysLater_whenOxygenCategoryB() {
		OxygenDate expectedDate = new OxygenDate(requestDate.getValue());
		expectedDate.addDays(10);

		oxygenTank = new OxygenTank(categoryB, requestDate);

		assertEquals(expectedDate.toString(), oxygenTank.getReadyDate().toString());
	}

	@Test
	void getReadyDate_shouldReturnSameDay_whenOxygenCategoryE() {
		oxygenTank = new OxygenTank(categoryE, requestDate);

		assertEquals(requestDate.toString(), oxygenTank.getReadyDate().toString());
	}

	@Test
	void getMoney_shouldReturnCorrectPrice_whenOxygenCategoryA() {
		BigDecimal tankPrice = new BigDecimal(
				(OxygenTank.CATEGORY_A_NUMBER_OF_RESOURCES_NEEDED * OxygenTank.CATEGORY_A_RESOURCE_PRICE)
						/ OxygenTank.CATEGORY_A_NUMBER_OF_TANKS_CREATED);
		Money expectedPrice = new Money(tankPrice);

		oxygenTank = new OxygenTank(categoryA, requestDate);

		assertEquals(expectedPrice, oxygenTank.getPrice());
	}

	@Test
	void getMoney_shouldReturnCorrectPrice__whenOxygenCategoryB() {
		BigDecimal tankPrice = new BigDecimal(
				(OxygenTank.CATEGORY_B_NUMBER_OF_RESOURCES_NEEDED * OxygenTank.CATEGORY_B_RESOURCE_PRICE)
						/ OxygenTank.CATEGORY_B_NUMBER_OF_TANKS_CREATED);
		Money expectedPrice = new Money(tankPrice);

		oxygenTank = new OxygenTank(categoryB, requestDate);

		assertEquals(expectedPrice, oxygenTank.getPrice());
	}

	@Test
	void getMoney_shouldReturnCorrectPrice_whenOxygenCategoryE() {
		BigDecimal tankPrice = new BigDecimal(
				(OxygenTank.CATEGORY_E_NUMBER_OF_RESOURCES_NEEDED * OxygenTank.CATEGORY_E_RESOURCE_PRICE)
						/ OxygenTank.CATEGORY_E_NUMBER_OF_TANKS_CREATED);
		Money expectedPrice = new Money(tankPrice);

		OxygenTank oxygenE = new OxygenTank(categoryE, requestDate);

		assertEquals(expectedPrice, oxygenE.getPrice());
	}

}
