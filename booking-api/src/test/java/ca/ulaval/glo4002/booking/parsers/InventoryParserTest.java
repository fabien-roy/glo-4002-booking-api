package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.domainobjects.report.Inventory;
import ca.ulaval.glo4002.booking.dto.InventoryItemDto;
import ca.ulaval.glo4002.booking.entities.InventoryEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventoryParserTest {

	private InventoryParser subject;
	private Inventory inventory;
	private Inventory inventoryOneEmptyCategory;
	private Inventory anEmptyInventory;
	private static final Long A_VALID_NUMBER_OF_TANK_CATEGORY_A_STORED = 1L;
	private static final Long A_VALID_NUMBER_OF_TANK_CATEGORY_B_STORED = 2L;
	private static final Long A_VALID_NUMBER_OF_TANK_CATEGORY_E_STORED = 3L;

	@BeforeEach
	void setup() {
		Map<Long, Long> storedTank = new HashMap<>();
		Map<Long, Long> storedTankOneEmptyCategory = new HashMap<>();

		storedTank.put(OxygenConstants.Categories.A_ID, A_VALID_NUMBER_OF_TANK_CATEGORY_A_STORED);
		storedTank.put(OxygenConstants.Categories.B_ID, A_VALID_NUMBER_OF_TANK_CATEGORY_B_STORED);
		storedTank.put(OxygenConstants.Categories.E_ID, A_VALID_NUMBER_OF_TANK_CATEGORY_E_STORED);

		anEmptyInventory = new Inventory();
		inventory = new Inventory(storedTank);
		inventoryOneEmptyCategory = new Inventory(storedTankOneEmptyCategory);
		subject = new InventoryParser();
	}

	@Test
	void whenParsingEntity_InventoryShouldBeValid() {
		InventoryEntity entity = subject.toEntity(inventory);

		Inventory parsedInventory = subject.parseEntity(entity);

		assertEquals(entity.getInUseTanks().get(0).getQuantity(), parsedInventory.getOxygenTanks().get(OxygenConstants.Categories.E_ID));
		assertEquals(entity.getInUseTanks().get(1).getQuantity(), parsedInventory.getOxygenTanks().get(OxygenConstants.Categories.B_ID));
		assertEquals(entity.getInUseTanks().get(2).getQuantity(), parsedInventory.getOxygenTanks().get(OxygenConstants.Categories.A_ID));
	}

	@Test
	void whenParsingToEntity_entityShouldBeValid() {
		InventoryEntity entity = subject.toEntity(inventory);

		assertEquals(A_VALID_NUMBER_OF_TANK_CATEGORY_E_STORED, entity.getInUseTanks().get(0).getQuantity());
		assertEquals(A_VALID_NUMBER_OF_TANK_CATEGORY_B_STORED, entity.getInUseTanks().get(1).getQuantity());
		assertEquals(A_VALID_NUMBER_OF_TANK_CATEGORY_A_STORED, entity.getInUseTanks().get(2).getQuantity());
	}

	@Test
	void whenParsingToDto_dtoShouldBeValid() {
		List<InventoryItemDto> dtos = subject.toDto(inventory);

		assertEquals("E", dtos.get(0).gradeTankOxygen);
		assertEquals(inventory.getOxygenTanks().get(OxygenConstants.Categories.E_ID), dtos.get(0).quantity);
		assertEquals("B", dtos.get(1).gradeTankOxygen);
		assertEquals(inventory.getOxygenTanks().get(OxygenConstants.Categories.B_ID), dtos.get(1).quantity);
		assertEquals("A", dtos.get(2).gradeTankOxygen);
		assertEquals(inventory.getOxygenTanks().get(OxygenConstants.Categories.A_ID), dtos.get(2).quantity);
	}

	@Test
	void whenParsingToDto_andAllCategoryInInventoryIsEmpty_thenItShouldNotBeAddedToTheDto() {
		List<InventoryItemDto> dtos = subject.toDto(anEmptyInventory);

		assertEquals(0, dtos.size());
	}

	@Test
	void whenParsingToDto_andOneCategoryInTheInventoryIsEmpty_thenItShouldNotBeAddedToTheDto() {
		List<InventoryItemDto> dtos = subject.toDto(inventoryOneEmptyCategory);

		assertEquals(2, dtos.size());
	}
}
