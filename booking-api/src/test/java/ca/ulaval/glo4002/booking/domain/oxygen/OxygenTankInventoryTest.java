package ca.ulaval.glo4002.booking.domain.oxygen;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.enums.OxygenCategory;

public class OxygenTankInventoryTest {

	private OxygenTankInventory oxygenTankInventory;

	private OxygenTank mockedTankCategoryA;
	private OxygenTank mockedTankCategoryB;
	private OxygenTank mockedTankCategoryE;

	private final Integer CATEGORY_A_QUANTITY = 25;
	private final Integer CATEGORY_B_QUANTITY = 12;
	private final Integer CATEGORY_E_QUANTITY = 5;

	@BeforeEach
	void setupOxygenTankInventory() {
		oxygenTankInventory = new OxygenTankInventory();

		mockedTankCategoryA = mock(OxygenTank.class);
		mockedTankCategoryB = mock(OxygenTank.class);
		mockedTankCategoryE = mock(OxygenTank.class);

		oxygenTankInventory.addTanksToInventory(OxygenCategory.A, Collections.nCopies(CATEGORY_A_QUANTITY, mockedTankCategoryA));
		oxygenTankInventory.addTanksToInventory(OxygenCategory.B, Collections.nCopies(CATEGORY_B_QUANTITY, mockedTankCategoryB));
		oxygenTankInventory.addTanksToInventory(OxygenCategory.E, Collections.nCopies(CATEGORY_E_QUANTITY, mockedTankCategoryE));
	}

	@Test
	void constructing_shouldSetNoNotInUseTank() {
		oxygenTankInventory = new OxygenTankInventory();

		assertEquals(0, (int) oxygenTankInventory.getNotInUseQuantityByCategory(OxygenCategory.A));
		assertEquals(0, (int) oxygenTankInventory.getNotInUseQuantityByCategory(OxygenCategory.B));
		assertEquals(0, (int) oxygenTankInventory.getNotInUseQuantityByCategory(OxygenCategory.E));
	}

	@Test
	void constructing_shouldSetNoInUseTank() {
		oxygenTankInventory = new OxygenTankInventory();

		assertEquals(0, (int) oxygenTankInventory.getInUseQuantityByCategory(OxygenCategory.A));
		assertEquals(0, (int) oxygenTankInventory.getInUseQuantityByCategory(OxygenCategory.B));
		assertEquals(0, (int) oxygenTankInventory.getInUseQuantityByCategory(OxygenCategory.E));
	}

	@Test
	void addTankToInventory_shouldUpdateNumberOfTankInNotInUse() {
		Integer addedQuantity = 5;

		oxygenTankInventory.addTanksToInventory(OxygenCategory.A, Collections.nCopies(addedQuantity, mockedTankCategoryA));
		Integer currentQuantity = oxygenTankInventory.getNotInUseQuantityByCategory(OxygenCategory.A);

		assertEquals((int) currentQuantity, CATEGORY_A_QUANTITY + addedQuantity);
	}

	@Test
	void requestTankByCategory_shouldUpdateQuantityInUse() {
		Integer requestQuantity = 10;

		oxygenTankInventory.requestTankByCategory(OxygenCategory.B, requestQuantity);
		Integer currentQuantity = oxygenTankInventory.getInUseQuantityByCategory(OxygenCategory.B);

		assertSame(currentQuantity, requestQuantity);
	}

	@Test
	void requestTank_shouldUpdateQuantityNotInUse() {
		Integer requestQuantity = 10;

		oxygenTankInventory.requestTankByCategory(OxygenCategory.B, requestQuantity);
		Integer currentQuantity = oxygenTankInventory.getNotInUseQuantityByCategory(OxygenCategory.B);

		assertEquals((int) currentQuantity, CATEGORY_B_QUANTITY - requestQuantity);
	}

	@Test
	void requestTank_shouldReturnNone_whenThereIsEnoughNotInUseTanks() {
		Integer requestedQuantity = 5;

		Integer quantityNeeded = oxygenTankInventory.requestTankByCategory(OxygenCategory.E, requestedQuantity);

		assertEquals(0, (int) quantityNeeded);
	}

	@Test
	void requestTank_shouldReturnTheNumberStillNeededToProduce_whenNotEnoughNotInUseTank() {
		Integer requestedQuantity = 30;

		Integer quantity = oxygenTankInventory.requestTankByCategory(OxygenCategory.A, requestedQuantity);

		assertEquals((int) quantity, Math.abs(CATEGORY_A_QUANTITY - requestedQuantity));
	}
}