package ca.ulaval.glo4002.booking.domain.oxygen;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.enums.OxygenCategories;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class OxygenInventoryTest {

	private OxygenInventory oxygenInventory;

	private OxygenTank mockedTankCategoryA;
	private OxygenTank mockedTankCategoryB;
	private OxygenTank mockedTankCategoryE;

	private final Integer CATEGORY_A_QUANTITY = 25;
	private final Integer CATEGORY_B_QUANTITY = 12;
	private final Integer CATEGORY_E_QUANTITY = 5;

	@BeforeEach
	void setupOxygenTankInventory() {
		oxygenInventory = new OxygenInventory();

		mockedTankCategoryA = mock(OxygenTank.class);
		mockedTankCategoryB = mock(OxygenTank.class);
		mockedTankCategoryE = mock(OxygenTank.class);

		oxygenInventory.addTanksToInventory(OxygenCategories.A, Collections.nCopies(CATEGORY_A_QUANTITY, mockedTankCategoryA));
		oxygenInventory.addTanksToInventory(OxygenCategories.B, Collections.nCopies(CATEGORY_B_QUANTITY, mockedTankCategoryB));
		oxygenInventory.addTanksToInventory(OxygenCategories.E, Collections.nCopies(CATEGORY_E_QUANTITY, mockedTankCategoryE));
	}

	@Test
	void constructing_shouldSetNoNotInUseTank() {
		oxygenInventory = new OxygenInventory();

		assertEquals(0, (int) oxygenInventory.getNotInUseQuantityByCategory(OxygenCategories.A));
		assertEquals(0, (int) oxygenInventory.getNotInUseQuantityByCategory(OxygenCategories.B));
		assertEquals(0, (int) oxygenInventory.getNotInUseQuantityByCategory(OxygenCategories.E));
	}

	@Test
	void constructing_shouldSetNoInUseTank() {
		oxygenInventory = new OxygenInventory();

		assertEquals(0, (int) oxygenInventory.getInUseQuantityByCategory(OxygenCategories.A));
		assertEquals(0, (int) oxygenInventory.getInUseQuantityByCategory(OxygenCategories.B));
		assertEquals(0, (int) oxygenInventory.getInUseQuantityByCategory(OxygenCategories.E));
	}

	@Test
	void addTankToInventory_shouldUpdateNumberOfTankInNotInUse() {
		Integer addedQuantity = 5;

		oxygenInventory.addTanksToInventory(OxygenCategories.A, Collections.nCopies(addedQuantity, mockedTankCategoryA));
		Integer currentQuantity = oxygenInventory.getNotInUseQuantityByCategory(OxygenCategories.A);

		assertEquals(CATEGORY_A_QUANTITY + addedQuantity, (int) currentQuantity);
	}

	@Test
	void requestTankByCategory_shouldUpdateQuantityInUse() {
		Integer requestQuantity = 10;

		oxygenInventory.requestTankByCategory(OxygenCategories.B, OxygenCategories.B, requestQuantity);
		Integer currentQuantity = oxygenInventory.getInUseQuantityByCategory(OxygenCategories.B);

		assertSame(requestQuantity, currentQuantity);
	}

	@Test
	void requestCategoryATank_withMaxCategoryB_shouldUseCategoryBAndA(){
		Integer amountToGetFromB = 5;
		Integer requestQuantity = CATEGORY_A_QUANTITY + amountToGetFromB;

		oxygenInventory.requestTankByCategory(OxygenCategories.A, OxygenCategories.B, requestQuantity);
		Integer currentQuantityA = oxygenInventory.getNotInUseQuantityByCategory(OxygenCategories.A);
		Integer currentQuantityB = oxygenInventory.getNotInUseQuantityByCategory(OxygenCategories.B);

		assertEquals(0, currentQuantityA);
		assertEquals(CATEGORY_B_QUANTITY - amountToGetFromB, currentQuantityB);
	}

	@Test
	void requestTank_shouldUpdateQuantityNotInUse() {
		Integer requestQuantity = 10;

		oxygenInventory.requestTankByCategory(OxygenCategories.B, OxygenCategories.B, requestQuantity);
		Integer currentQuantity = oxygenInventory.getNotInUseQuantityByCategory(OxygenCategories.B);

		assertEquals(CATEGORY_B_QUANTITY - requestQuantity, (int) currentQuantity);
	}

	@Test
	void requestTank_shouldReturnNone_whenThereIsEnoughNotInUseTanks() {
		Integer requestedQuantity = 5;

		Integer quantityNeeded = oxygenInventory.requestTankByCategory(OxygenCategories.E, OxygenCategories.E, requestedQuantity);

		assertEquals(0, (int) quantityNeeded);
	}

	@Test
	void requestTank_shouldReturnTheNumberStillNeededToProduce_whenNotEnoughNotInUseTank() {
		Integer requestedQuantity = 100;

		Integer quantity = oxygenInventory.requestTankByCategory(OxygenCategories.A, OxygenCategories.B, requestedQuantity);

		assertEquals(Math.abs((CATEGORY_A_QUANTITY + CATEGORY_B_QUANTITY) - requestedQuantity), (int) quantity);
	}

	@Test
	void getNotInUseQuantityByCategory_shouldReturnRightNumber() {
		Integer numberA = oxygenInventory.getNotInUseQuantityByCategory(OxygenCategories.A);
		Integer numberB = oxygenInventory.getNotInUseQuantityByCategory(OxygenCategories.B);
		Integer numberE = oxygenInventory.getNotInUseQuantityByCategory(OxygenCategories.E);

		assertEquals(CATEGORY_A_QUANTITY, numberA);
		assertEquals(CATEGORY_B_QUANTITY, numberB);
		assertEquals(CATEGORY_E_QUANTITY, numberE);
	}

	@Test
	void getInUseQuantityByCategory_shouldReturnRightNumber() {
		Integer numberA = oxygenInventory.getInUseQuantityByCategory(OxygenCategories.A);
		Integer numberB = oxygenInventory.getInUseQuantityByCategory(OxygenCategories.B);
		Integer numberE = oxygenInventory.getInUseQuantityByCategory(OxygenCategories.E);

		assertEquals(0, numberA);
		assertEquals(0, numberB);
		assertEquals(0, numberE);
	}

	@Test
	void getInUseTanksByCategory_shouldReturnRightNumber() {
		List<OxygenTank> listA = oxygenInventory.getInUseTanksByCategory(OxygenCategories.A);
		List<OxygenTank> listB = oxygenInventory.getInUseTanksByCategory(OxygenCategories.B);
		List<OxygenTank> listE = oxygenInventory.getInUseTanksByCategory(OxygenCategories.E);

		assertEquals(0, listA.size());
		assertEquals(0, listB.size());
		assertEquals(0, listE.size());
	}

	@Test
	void getNotInUseTankByCategory_shouldReturnRightNumber() {
		List<OxygenTank> listA = oxygenInventory.getNotInUseTankByCategory(OxygenCategories.A);
		List<OxygenTank> listB = oxygenInventory.getNotInUseTankByCategory(OxygenCategories.B);
		List<OxygenTank> listE = oxygenInventory.getNotInUseTankByCategory(OxygenCategories.E);

		assertEquals(CATEGORY_A_QUANTITY, listA.size());
		assertEquals(CATEGORY_B_QUANTITY, listB.size());
		assertEquals(CATEGORY_E_QUANTITY, listE.size());
	}

	@Test
	void getAllQuantityByCategory_shouldReturnRightNumber() {
		Integer numberA = oxygenInventory.getAllQuantityByCategory(OxygenCategories.A);
		Integer numberB = oxygenInventory.getAllQuantityByCategory(OxygenCategories.B);
		Integer numberE = oxygenInventory.getAllQuantityByCategory(OxygenCategories.E);

		assertEquals(CATEGORY_A_QUANTITY, numberA);
		assertEquals(CATEGORY_B_QUANTITY, numberB);
		assertEquals(CATEGORY_E_QUANTITY, numberE);
	}

	@ParameterizedTest
	@EnumSource(OxygenCategories.class)
	void getAllTanks_shouldReturnNoTankForCategory_whenThereIsNoTank(OxygenCategories category) {
		oxygenInventory = new OxygenInventory();

		Map<OxygenCategories, List<OxygenTank>> allTanks = oxygenInventory.getAllTanks();
		List<OxygenTank> categoryATanks = allTanks.get(category);

		assertTrue(categoryATanks.isEmpty());
	}

	@Test
	void getAllTanks_shouldReturnAllTanksForCategoryA_whenThereAreSomeNotInUseCategoryATanks() {
		Integer expectedQuantity = CATEGORY_A_QUANTITY;

		Map<OxygenCategories, List<OxygenTank>> allTanks = oxygenInventory.getAllTanks();
		List<OxygenTank> categoryATanks = allTanks.get(OxygenCategories.A);

		assertEquals(expectedQuantity, categoryATanks.size());
	}

	@Test
	void getAllTanks_shouldReturnAllTanksForCategoryB_whenThereAreSomeNotInUseCategoryBTanks() {
		Integer expectedQuantity = CATEGORY_B_QUANTITY;

		Map<OxygenCategories, List<OxygenTank>> allTanks = oxygenInventory.getAllTanks();
		List<OxygenTank> categoryATanks = allTanks.get(OxygenCategories.B);

		assertEquals(expectedQuantity, categoryATanks.size());
	}

	@Test
	void getAllTanks_shouldReturnAllTanksForCategoryE_whenThereAreSomeNotInUseCategoryETanks() {
		Integer expectedQuantity = CATEGORY_E_QUANTITY;

		Map<OxygenCategories, List<OxygenTank>> allTanks = oxygenInventory.getAllTanks();
		List<OxygenTank> categoryATanks = allTanks.get(OxygenCategories.E);

		assertEquals(expectedQuantity, categoryATanks.size());
	}

	@ParameterizedTest
	@EnumSource(OxygenCategories.class)
	void getAllTanks_shouldReturnAllTanksForCategory_whenThereAreSomeNotInUseAndSomeInUseTanks(OxygenCategories category) {
		Map<OxygenCategories, List<OxygenTank>> allTanks = oxygenInventory.getAllTanks();
		List<OxygenTank> categoryATanks = allTanks.get(category);
		Integer expectedQuantity = oxygenInventory.getAllQuantityByCategory(category);

		assertEquals(expectedQuantity, categoryATanks.size());
	}
}